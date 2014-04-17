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
import java.util.Map.Entry;

public class RelatedInfoList {

	public class OfferInfo{
		private String offerCategory;
		private String offerBrand;
		
		public OfferInfo(String offerCategory, String offerBrand)
		{
			this.offerCategory = offerCategory;
			this.offerBrand = offerBrand;
		}

		public String getOfferCategory() {
			return offerCategory;
		}

		public String getOfferBrand() {
			return offerBrand;
		}
	}
	
	private String offerFile;
	private String transactionFile;
	
    //category to department mapping
    private HashMap<String, String> cat2dept;
    private HashMap<String, ArrayList<String>> dept2cat;

	//a list of all offers and the their category brand
    private HashMap<String,OfferInfo> offerInfoList;
	private ArrayList<String> offerList;
	
    //the list of category and brand appear in all offers
    private ArrayList<String> category;
    private ArrayList<String> brand;
	
	public RelatedInfoList(String offerFile, String transactionFile) {
        this.offerFile = offerFile;
        this.transactionFile = transactionFile;
    }
	
    public HashMap<String, ArrayList<String>> getDept2cat() {
		return dept2cat;
	}
    
    public HashMap<String, OfferInfo> getOfferInfoList() {
		return offerInfoList;
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
        buildDept2CatMap();
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
            offerInfoList = new HashMap<String,OfferInfo>();
            
            while ((nextLine = reader.readNext()) != null) {
            	String thisCategory = nextLine[1].trim();
            	String thisBrand = nextLine[5].trim();
            	String thisOffer = nextLine[0].trim();
            	
                categories.add(thisCategory);
                brands.add(thisBrand);
                offerList.add(thisOffer);
                offerInfoList.put(thisOffer, new OfferInfo(thisCategory,thisBrand));
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
    
    private void buildDept2CatMap() {
		
    	dept2cat = new HashMap<String,ArrayList<String>>();
    	
    	for (Entry<String, String> entry : cat2dept.entrySet())
    	{
    		if (dept2cat.containsKey(entry.getValue()))//if contains that dept
    		{
    			dept2cat.get(entry.getValue()).add(entry.getKey());
    		}
    		else
    		{
    			ArrayList<String> categories = new ArrayList<String>();
    			categories.add(entry.getKey());
    			dept2cat.put(entry.getValue(), categories);
    		}
    	}
	}
}
