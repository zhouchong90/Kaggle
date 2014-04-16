package dataPreprocess;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OfferCustomerMap {

    public class CustomerAcceptence {

        private String customerID;
        private boolean acceptence;
        
        public String getCustomerID() {
            return customerID;
        }

        public boolean isAcceptence() {
            return acceptence;
        }

        public CustomerAcceptence(String customerID, boolean acceptence) {
            this.customerID = customerID;
            this.acceptence = acceptence;
        }
    }
    
    private String transHistoryFile;
    private RelatedInfoList relatedInfo;
    private HashMap<String, ArrayList<CustomerAcceptence>> offer2customerList;
    
    public OfferCustomerMap(String transHistoryFile, RelatedInfoList relatedInfo) {
        this.transHistoryFile = transHistoryFile;
        this.relatedInfo = relatedInfo;
    }

    public HashMap<String, ArrayList<CustomerAcceptence>> getOffer2customerList() {
        return offer2customerList;
    }

    public void run() {
        buildOfferToCustomerList();
    }

    private void buildOfferToCustomerList() {

        try {

            CSVReader reader;
            reader = new CSVReader(new FileReader(transHistoryFile));
            String[] nextLine;
            //burn the first line.
            reader.readNext();
            //initialize
            offer2customerList = new HashMap<String, ArrayList<CustomerAcceptence>>();
            for (String offers : relatedInfo.getOfferList()) {
                ArrayList<CustomerAcceptence> customerList = new ArrayList<CustomerAcceptence>();
                offer2customerList.put(offers, customerList);
            }

            while ((nextLine = reader.readNext()) != null) {
                String customerID = nextLine[0].trim();
                String offer = nextLine[2].trim();
                boolean repeater = nextLine[5].trim().equals("t") ? true : false;

                offer2customerList.get(offer).add(new CustomerAcceptence(customerID, repeater));
            }
            
            reader.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatedInfoList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
