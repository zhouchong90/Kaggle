package dataPreprocess;

import java.util.Date;

public class WorkFlow {

	private String fileFolderPath;
	
	public WorkFlow(String fileFolderPath)
	{
		this.fileFolderPath = fileFolderPath;
	}
	
	public void run() {
		Date start = new Date();
		
		System.out.println("Starting to extract features from raw data, current time:"+start.toString());
		System.out.println("Loading basic information...");
		RelatedInfoList infoList = buildRelatedInfoList();
		
		System.out.println("Figuring out which customers accepted which offer...");
        OfferCustomerMap ocMap = buildOfferCustomerMap(infoList);
             
        System.out.println("Calculating customer profiles based on past transactions...");
		CustomerTransactions ct = buildCustomerTransactions(infoList);
		
		System.out.println("Extracting features and writing it into files...");
		offerFeatureExtraction(infoList, ocMap, ct);
		
		Long costTime = (new Date().getTime()-start.getTime())/1000;
		System.out.println("Data preprocess is finished. Total cost time: "+ costTime + "s");
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
        
    private CustomerTransactions buildCustomerTransactions(RelatedInfoList relatedInfo) {
		CustomerTransactions ct = new CustomerTransactions(fileFolderPath+"transactions.csv", relatedInfo);
		ct.run();
		return ct;
	}
        
	private void offerFeatureExtraction(RelatedInfoList infoList, OfferCustomerMap ocMap, CustomerTransactions ct) {
		
		OfferFeatureExtraction featureExtraction = new OfferFeatureExtraction(fileFolderPath,infoList,ocMap,ct);	
		featureExtraction.run();
		
	}

}
