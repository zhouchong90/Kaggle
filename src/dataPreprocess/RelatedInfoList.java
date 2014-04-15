package dataPreprocess;

import java.util.ArrayList;
import java.util.HashMap;

public class RelatedInfoList {
	
	public RelatedInfoList(String offerFile, String transactionFile)
	{
		this.offerFile = offerFile;
		this.transactionFile = transactionFile;
	}
	
	private String offerFile;
	private String transactionFile;
	
	
	private HashMap<Integer, Integer> cat2dept;
	
	private ArrayList<Integer> category;
	private ArrayList<Integer> brand;
	
	private HashMap<Integer, OfferInfo> offers;

	
	public void run()
	{
		buildLists();
		buildCat2DeptMap();
		buildOfferMap();
	}

	private class OfferInfo
	{
		public int category;
		public int department;
		public int brand;
	}

	
	
	private void buildCat2DeptMap() {
		// TODO Auto-generated method stub
		
	}

	private void buildOfferMap() {
		// TODO Auto-generated method stub
		
	}

	private void buildLists() {
		// TODO Auto-generated method stub
		
	}
	
	// TODO read offers.csv and build the lists and offers
	// TODO read transactions.csv and build cat2dept.
}
