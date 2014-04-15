package dataPreprocess;

import java.util.ArrayList;
import java.util.HashMap;

public class OfferCustomerList {

	public class CustomerAcceptence
	{
		int customerID;
		boolean acceptence;
	}
	public String transHistoryFile;
	
	public OfferCustomerList(String transaHistoryFile)
	{
		this.transHistoryFile = transHistoryFile;
	}
	private HashMap<Integer, ArrayList<CustomerAcceptence>> offer2customerList;
	
	public void run()
	{
		buildOfferToCustomerList();
	}

	private void buildOfferToCustomerList() {
		// TODO Auto-generated method stub
		
	}
	
}
