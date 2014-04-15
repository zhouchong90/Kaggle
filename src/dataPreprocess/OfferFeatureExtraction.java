package dataPreprocess;

import java.io.Serializable;

public class OfferFeatureExtraction{
	
	private CustomerTransactions customerTransactions;
	private OfferCustomerMap offerCustomer;
	private RelatedInfoList relatedInfo;
	
	public OfferFeatureExtraction(CustomerTransactions customerTransactions, 
			OfferCustomerMap offerCustomer, RelatedInfoList relatedInfo)
	{
		this.customerTransactions = customerTransactions;
		this.offerCustomer = offerCustomer;
		this.relatedInfo = relatedInfo;
	}
	
	public void extractOfferFeatures ()
	{
		//TODO get a list of customes for that offer
		//for each offer
			extractOfferMatrix();
			writeFeatureMatrixToFile();
	}

	private void extractOfferMatrix() {
		// for each customer
			extractCustomerFeature();
	}

	private void extractCustomerFeature() {
		// TODO Auto-generated method stub
		
		
	}
	
	private void writeFeatureMatrixToFile() {
		// TODO Auto-generated method stub
		
	}

	
}
