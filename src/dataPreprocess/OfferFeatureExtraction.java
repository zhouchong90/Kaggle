package dataPreprocess;

import java.io.Serializable;

public class OfferFeatureExtraction{
	
	private CustomerTransactions customerTransactions;
	private OfferCustomerList offerCustomer;
	private RelatedInfoList relatedInfo;
	
	public OfferFeatureExtraction(CustomerTransactions customerTransactions, 
			OfferCustomerList offerCustomer, RelatedInfoList relatedInfo)
	{
		this.customerTransactions = customerTransactions;
		this.offerCustomer = offerCustomer;
		this.relatedInfo = relatedInfo;
	}
	
	public void extractOfferFeatures (int offerIndex)
	{
		//TODO get a list of customes for that offer
		// for each customer
			extractCustomerFeature();
	}

	private void extractCustomerFeature() {
		// TODO Auto-generated method stub
		
		writeCustomerFeatureToFile();
	}

	private void writeCustomerFeatureToFile() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
