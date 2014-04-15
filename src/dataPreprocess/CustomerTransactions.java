package dataPreprocess;

import java.util.HashMap;

public class CustomerTransactions {

	private String transactionFile;
	
	private HashMap<Integer, Float[]> customer2categoryNorm;
	
	public CustomerTransactions(String transactionFile)
	{
		this.transactionFile = transactionFile;
		
	}
	
	public void run()
	{
		buildCustomer2CategoryMap();
	}

	private void buildCustomer2CategoryMap() {
		// TODO Auto-generated method stub
		
	}
	
	
}
