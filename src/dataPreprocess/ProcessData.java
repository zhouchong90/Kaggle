package dataPreprocess;

public class ProcessData {

	public static void main(String[] args) {
		
		String fileFolderPath = "C:/Users/Chong Zhou/Desktop/Kaggle/";
		WorkFlow w= new WorkFlow(fileFolderPath);
		w.run();
	}
}
