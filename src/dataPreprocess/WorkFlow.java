package dataPreprocess;

public class WorkFlow {

	private String fileFolderPath;
	
	public WorkFlow(String fileFolderPath)
	{
		this.fileFolderPath = fileFolderPath;
	}
	
	public void run() {
		
		RelatedInfoList infoList = buildRelatedInfoList();
		System.out.println(infoList.getCat2dept().toString());
        OfferCustomerMap ocMap = buildOfferCustomerMap(infoList);
        
                
		buildCustomerTransactions();
		offerFeatureExtraction();
	}
        
	private RelatedInfoList buildRelatedInfoList() {
		
		RelatedInfoList infoList = new RelatedInfoList(fileFolderPath+"offers.csv", fileFolderPath+"transactions.csv");
        infoList.run();
        return infoList;
        
	}
        
    private OfferCustomerMap buildOfferCustomerMap(RelatedInfoList relatedInfo) {
            
		OfferCustomerMap ocMap = new OfferCustomerMap(fileFolderPath+"trainHistory.csv", relatedInfo);
        ocMap.run();
        return ocMap;
		
	}
        
    private void buildCustomerTransactions() {
		// TODO Auto-generated method stub
		
	}
        
	private void offerFeatureExtraction() {
		// TODO Auto-generated method stub
		//read the offer listfile
		//for each offer
		//extract offer features.
		
	}

}
