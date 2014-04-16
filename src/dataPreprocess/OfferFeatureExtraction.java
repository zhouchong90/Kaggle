package dataPreprocess;

import java.io.Serializable;
import java.util.ArrayList;

public class OfferFeatureExtraction{
	
	private CustomerTransactions customerTransactions;
	private OfferCustomerMap offerCustomer;
	private RelatedInfoList relatedInfo;
	
	public OfferFeatureExtraction(RelatedInfoList relatedInfo, OfferCustomerMap offerCustomer,
			CustomerTransactions customerTransactions)
	{
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
			extractOfferMatrix(offer);
			writeFeatureMatrixToFile();
		}
	}

	private void extractOfferMatrix(String offerNumber) {
		String customerID = null;
			// for each customer
		
			extractCustomerFeature(customerID);
	}

	private void extractCustomerFeature(String customerID) {
		// TODO Auto-generated method stub
		
		
	}
	
	private void writeFeatureMatrixToFile() {
		// TODO Auto-generated method stub
		
	}

	
}
