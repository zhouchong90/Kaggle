package dataPreprocess;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class CustomerTransactions {

	public class CustomerProfile
	{
		private int transactionCount;
		private float totalExpenseCount;
		private Date minTime;
		private Date maxTime;
		private int[] categoryCount;
		private int[] brandCount;
		private boolean acceptOffer;
		
		public CustomerProfile(int transactionCount, float totalExpense, Date minTime, Date maxTime, int[] categoryCount, int[] brandCount)
		{
			this.transactionCount = transactionCount;
			this.totalExpenseCount = totalExpense;
			this.minTime = minTime;
			this.maxTime = maxTime;
			this.categoryCount = categoryCount;
			this.brandCount = brandCount;
		}
		
		public int getTransactionCount() {
			return transactionCount;
		}

		public float getTotalExpenseCount() {
			return totalExpenseCount;
		}

		public Date getMinTime() {
			return minTime;
		}

		public Date getMaxTime() {
			return maxTime;
		}
		
		public int[] getCategoryCount() {
			return categoryCount;
		}

		public int[] getBrandCount() {
			return brandCount;
		}
		
		public boolean isAcceptOffer() {
			return acceptOffer;
		}

		public void setAcceptOffer(boolean acceptOffer) {
			this.acceptOffer = acceptOffer;
		}

		public void addTransaction()
		{
			transactionCount++;
		}
		
		public void addExpense(float expense)
		{
			totalExpenseCount += expense;
		}
		
		public void editTime(Date thisDate)
		{
			if (thisDate.before(minTime))
				minTime = thisDate;
			if (thisDate.after(maxTime))
				maxTime = thisDate;
		}		
	}
	
	private String transactionFile;
	private RelatedInfoList relatedInfo;
	
	private HashMap<String, CustomerProfile> customerProfiles;

	public CustomerTransactions(String transactionFile, RelatedInfoList relatedInfo)
	{
		this.transactionFile = transactionFile;
		this.relatedInfo = relatedInfo;
	}
	
	public void run()
	{
		buildCustomerProfiles();
	}
	
	public HashMap<String, CustomerProfile> getCustomerProfiles() {
		return customerProfiles;
	}

	private void buildCustomerProfiles() {
		 try {

	            CSVReader reader;
	            reader = new CSVReader(new FileReader(transactionFile));
	            String[] nextLine;
	            //burn the first line.
	            reader.readNext();
	            customerProfiles = new HashMap<String, CustomerProfile>();

	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            ArrayList<String> categories = relatedInfo.getCategory();
	            ArrayList<String> brands = relatedInfo.getBrand();
	            int totalCategories = categories.size();
	            int totalBrands = brands.size();
	            
	            while ((nextLine = reader.readNext()) != null) {
	            	
	            	//extract line information
	                String customerID = nextLine[0].trim();
	                String thisCategory = nextLine[3].trim(); 
	                String thisBrand = nextLine[5].trim();
	                Date thisDate = df.parse(nextLine[6].trim());
	                float expense = Float.parseFloat(nextLine[10].trim());
	                
	                //if it has this customer records before
	                if (customerProfiles.containsKey(customerID))
	                {
	                	//modify its profile
	                	CustomerProfile cp = customerProfiles.get(customerID);
	                	
	                	cp.addTransaction();
	                	cp.addExpense(expense);
	                	cp.editTime(thisDate);
	                	incrementCount(categories,thisCategory,cp.getCategoryCount());
	                	incrementCount(brands,thisBrand,cp.getBrandCount());
	                }
	                //if this is a new customer
	                else
	                {          	
	                	//put category intoCount
	                	int[] categoryCount = new int[totalCategories];          	
	                	incrementCount(categories,thisCategory,categoryCount);
	                	
	                	//put brand intoCount
	                	int[] brandCount = new int[totalBrands];
	                	incrementCount(brands,thisBrand,brandCount);
	                	
	                	//create customer profile
	                	CustomerProfile cp = new CustomerProfile(1,expense,thisDate,thisDate,categoryCount,brandCount);
	                	//put this new customer into map
	                	
	                	customerProfiles.put(customerID, cp);
	                }
	                
	            }
	          
	            reader.close();

	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (IOException ex) {
	            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ParseException e) {
				e.printStackTrace();
			}
		
	}
	
	private void incrementCount(ArrayList<String> indexes, String object, int[] list)
	{
    	int index = indexes.indexOf(object);
    	if (index != -1)
    		list[index]++;
	}
	
}
