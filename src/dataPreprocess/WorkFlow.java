package dataPreprocess;

public class WorkFlow {

	String fileFolderPath;
	
	public WorkFlow(String fileFolderPath)
	{
		this.fileFolderPath = fileFolderPath;
	}
	
	public void run() {
		
		buildRelatedInfoList();
		buildOfferCustomerMap();
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
	
	private void buildOfferCustomerMap() {
		// TODO Auto-generated method stub
		
	}

	private void buildCustomerTransactions() {
		// TODO Auto-generated method stub
		
	}

}
