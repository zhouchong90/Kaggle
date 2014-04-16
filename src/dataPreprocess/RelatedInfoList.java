package dataPreprocess;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RelatedInfoList {

	private String offerFile;
	private String transactionFile;
	
    //category to department mapping
    private HashMap<String, String> cat2dept;
    //the list of category and brand appear in all offers
    private ArrayList<String> offerList;
    private ArrayList<String> category;
    private ArrayList<String> brand;
	
    
	public RelatedInfoList(String offerFile, String transactionFile) {
        this.offerFile = offerFile;
        this.transactionFile = transactionFile;
    }
   
    public HashMap<String, String> getCat2dept() {
        return cat2dept;
    }

    public ArrayList<String> getOfferList() {
        return offerList;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public ArrayList<String> getBrand() {
        return brand;
    }

    public void run() {
        buildLists();
        buildCat2DeptMap();
    }
    
    //build the category and brand list from offer file
    private void buildLists() {
        try {

            CSVReader reader;
            reader = new CSVReader(new FileReader(offerFile));
            String[] nextLine;
            //burn the first line.
            reader.readNext();
            //use sets to store all values
            HashSet<String> categories = new HashSet<String>();
            HashSet<String> brands = new HashSet<String>();
            offerList = new ArrayList<String>();
            while ((nextLine = reader.readNext()) != null) {
                categories.add(nextLine[1].trim());
                brands.add(nextLine[5].trim());
                offerList.add(nextLine[0].trim());
            }
            //initialize
            category = new ArrayList<String>();
            brand = new ArrayList<String>();

            category.addAll(categories);
            brand.addAll(brands);
            
            reader.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //build the category to department mapping
    private void buildCat2DeptMap() {
        try {
            CSVReader reader;
            reader = new CSVReader(new FileReader(transactionFile));
            String[] nextLine;
            reader.readNext();
            //initialize the mapping
            cat2dept = new HashMap<String, String>();
            for (String cat : category) {
                cat2dept.put(cat, "");
            }
            int count = 0;
            while ((nextLine = reader.readNext()) != null && count < cat2dept.size()) {
                String cat = nextLine[3].trim();
                String dept = nextLine[2].trim();
                if (cat2dept.containsKey(cat)) {
                	if(cat2dept.get(cat).isEmpty())
                	{
                		cat2dept.put(cat, dept);
                		count++;
                	}
                }
            }
            
            reader.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
