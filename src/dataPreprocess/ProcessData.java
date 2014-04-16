package dataPreprocess;

public class ProcessData {

	public static void main(String[] args) {
		
		String fileFolderPath = "/Users/test/Documents/Kaggle/datasets/";
		WorkFlow w= new WorkFlow(fileFolderPath);
		w.run();

	}

}
