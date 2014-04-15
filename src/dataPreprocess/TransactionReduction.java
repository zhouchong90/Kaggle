package dataPreprocess;

public class TransactionReduction {

	private RelatedInfoList relatedInfoList;
	private String transactionFile;
	private String newTransactionFileName = "slimTransactions";
	
	public TransactionReduction(RelatedInfoList list, String transactionFile)
	{
		this.relatedInfoList = list;
		this.transactionFile = transactionFile;
	}
	
	public void run()
	{
		//TODO for each line in list, judge if retain, if yes, write to another file.
	}
	

}
