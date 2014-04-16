package dataPreprocess;

import java.util.ArrayList;

public class WorkFlow {

	private String fileFolderPath;
	
	public WorkFlow(String fileFolderPath)
	{
		this.fileFolderPath = fileFolderPath;
	}
	
	public void run() {
		
		RelatedInfoList infoList = buildRelatedInfoList();
        OfferCustomerMap ocMap = buildOfferCustomerMap(infoList);
                
		CustomerTransactions ct = buildCustomerTransactions(infoList);
		offerFeatureExtraction(infoList, ocMap, ct);
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
		
		OfferFeatureExtraction featureExtraction = new OfferFeatureExtraction(infoList,ocMap,ct);	
		featureExtraction.run();
		
	}

}
