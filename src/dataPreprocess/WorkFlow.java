package dataPreprocess;

public class WorkFlow {

	String fileFolderPath;
	
	public WorkFlow(String fileFolderPath)
	{
		this.fileFolderPath = fileFolderPath;
	}
	
	public void run() {
		
		buildRelatedInfoList();
		transactionReduction();
		buildOfferCustomerList();
		buildCustomerTransactions();
		offerFeatureExtraction();

	}

	private void offerFeatureExtraction() {
		// TODO Auto-generated method stub
		//read the offer list
		//for each offer
			//extract offer features.
		
	}

	private void buildRelatedInfoList() {
		// TODO Auto-generated method stub
		
	}


	private void transactionReduction() {
		// TODO Auto-generated method stub
		
	}
	
	private void buildOfferCustomerList() {
		// TODO Auto-generated method stub
		
	}

	private void buildCustomerTransactions() {
		// TODO Auto-generated method stub
		
	}

}
