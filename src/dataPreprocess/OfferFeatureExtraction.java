package dataPreprocess;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import dataPreprocess.CustomerTransactions.CustomerProfile;
import dataPreprocess.OfferCustomerMap.CustomerAcceptence;

public class OfferFeatureExtraction{
	
	private CustomerTransactions customerTransactions;
	private OfferCustomerMap offerCustomer;
	private RelatedInfoList relatedInfo;
	private String fileFolderPath;
	
	public OfferFeatureExtraction(String fileFolderPath,RelatedInfoList relatedInfo, OfferCustomerMap offerCustomer,
			CustomerTransactions customerTransactions)
	{
		this.fileFolderPath = fileFolderPath;
		this.customerTransactions = customerTransactions;
		this.offerCustomer = offerCustomer;
		this.relatedInfo = relatedInfo;
	}
	
	public void run()
	{
		//for each offer, extract the feature matrix separately and write to file
		ArrayList<String> offerList = relatedInfo.getOfferList();
		for (String offer:offerList)
		{
			ArrayList<CustomerProfile> customerProfiles = extractOfferMatrix(offer);
			writeFeatureMatrixToFile(offer,customerProfiles);
		}
	}

	private ArrayList<CustomerProfile> extractOfferMatrix(String offerNumber) {
		//create a new list of customerProfile(rows of the feature matrix)
		ArrayList<CustomerProfile> customerProfiles = new ArrayList<CustomerProfile>();
		
		//for every customer in that offer, extract their profiles.
		ArrayList<CustomerAcceptence> customerList = offerCustomer.getOffer2customerList().get(offerNumber);
		for (CustomerAcceptence ca:customerList)
			customerProfiles.add(getEditedCustomerProfile(ca.getCustomerID(), ca.isAcceptence()));
		
		return customerProfiles;
	}

	//get the profile, set acceptance with a value, return.
	private CustomerProfile getEditedCustomerProfile(String customerID, boolean acceptence) {
		
		CustomerProfile cp = customerTransactions.getCustomerProfiles().get(customerID);
		cp.setAcceptOffer(acceptence);
		return cp;		
	}
	
	private void writeFeatureMatrixToFile(String offer, ArrayList<CustomerProfile> customerProfiles) {
		try {
			FileWriter fw = new FileWriter(fileFolderPath+"offerFeatures/"+offer);
			PrintWriter printer = new PrintWriter(fw,true);
			//write header of the file
			writeHeader(printer);
			//for each customer, write a line of their data.
			for (CustomerProfile cp : customerProfiles)
				writeCustomerFeatures(offer,cp,printer);
				
			printer.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeHeader(PrintWriter printer) {
		printer.println("@RELATION Kaggle");
		printer.println();
		printer.println("@ATTRIBUTE TotalTransactions NUMERIC");
		printer.println("@ATTRIBUTE TotalExpense NUMERIC");
		printer.println("@ATTRIBUTE MinTime DATE \"yyyy-MM-dd\"");
		printer.println("@ATTRIBUTE MaxTime DATE \"yyyy-MM-dd\"");
		printer.println("@ATTRIBUTE CategoryCount NUMERIC");
		printer.println("@ATTRIBUTE DepartmentCount NUMERIC");
		printer.println("@ATTRIBUTE BrandCount NUMERIC");
		printer.println("@ATTRIBUTE AcceptOffer {yes,no}");
		printer.println();
		printer.println("@DATA");
	}

	private void writeCustomerFeatures(String offer, CustomerProfile cp, PrintWriter printer) {
		StringBuffer sb = new StringBuffer();
		
		//formats
		char delimiter = ',';
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		//write simple features
		sb.append(cp.getTransactionCount());
		sb.append(delimiter);
		sb.append(cp.getTotalExpenseCount());
		sb.append(delimiter);	
		sb.append(df.format(cp.getMinTime()));
		sb.append(delimiter);
		sb.append(df.format(cp.getMaxTime()));
		sb.append(delimiter);
		
		//Count categories and brands and departments.
		String thisCategory = relatedInfo.getOfferInfoList().get(offer).getOfferCategory();
		String thisDepartment = relatedInfo.getCat2dept().get(thisCategory);
		String thisBrand = relatedInfo.getOfferInfoList().get(offer).getOfferBrand();
		
		//write category count
		int categoryCount = getCount(relatedInfo.getCategory(),thisCategory,cp.getCategoryCount());
		sb.append(categoryCount);
		sb.append(delimiter);
		
		//write department count
		int departmentCount = getDepartmentCount(thisDepartment, cp.getCategoryCount());
		sb.append(departmentCount);
		sb.append(delimiter);
		
		//write brand count
		int brandCount = getCount(relatedInfo.getBrand(),thisBrand,cp.getBrandCount());
		sb.append(brandCount);
		sb.append(delimiter);
		
		//write accept offer or not
		sb.append(cp.isAcceptOffer()?"yes":"no");
		
		printer.println(sb.toString());
	}

	private int getCount(ArrayList<String> indexes, String object, int[] list)
	{
		int index = indexes.indexOf(object);
    	return list[index];
	}
	
	private int getDepartmentCount(String thisDepartment, int[] categoryCount) {
		
		int count = 0;
		ArrayList<String> relatedCategories = relatedInfo.getDept2cat().get(thisDepartment);
		for (String category:relatedCategories)
		{
			count += getCount(relatedInfo.getCategory(), category, categoryCount);
		}
		return count;
	}
	
}
