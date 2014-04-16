package dataPreprocess;

import java.util.Date;
import java.util.HashMap;

public class CustomerTransactions {

	private class CustomerProfile
	{
		int transactionCount;
		int totalExpenseCount;
		Date minTime;
		Date maxTime;
		int[] catetoryCount;
		int[] brandCount;
	}
	
	
	private String transactionFile;
	private RelatedInfoList relatedInfo;
	
	private HashMap<Integer, CustomerProfile> customerProfiles;
	
	public CustomerTransactions(String transactionFile, RelatedInfoList relatedInfo)
	{
		this.transactionFile = transactionFile;
		this.relatedInfo = relatedInfo;
	}
	
	public void run()
	{
		buildCustomerProfiles();
	}

	private void buildCustomerProfiles() {
		// TODO Auto-generated method stub
		
	}
	
}
