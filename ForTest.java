
/**
 * Using CSV
 * 
 * @author Frank Aboaye
 * @version 2.1
 */

import edu.duke.*;
import org.apache.commons.csv.*;


public class ForTest {
    
    // CSVParser parser, String country
    
    public void countryInfo( CSVParser parser,String country){
        
        for(CSVRecord record: parser){
            String listOfCountries = record.get("Country");
            if(listOfCountries .contains(country)){
                String exports = record.get("Exports");
                String value  = record.get("Value (dollars)");
                System.out.println(country + ": " + exports + ": " + value );
            }
            else{
                System.out.println("NOT FOUND");
            }
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        
        for(CSVRecord record: parser){
            String listOfExports = record.get("Exports");
            if(listOfExports.contains(exportItem1) && listOfExports.contains(exportItem2) ){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
        
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record: parser){
            String listOfExports = record.get("Exports");
            if(listOfExports.contains(exportItem)){
                String country = record.get("Country");
                count = count + 1;
            }
        }
        System.out.println(count);
        
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser){
            String listOfValues = record.get("Value (dollars)");
            if(listOfValues.length() > amount.length()){
                String itsCountry = record.get("Country");
                System.out.println(itsCountry + " " + listOfValues );
            }
        }
    }
    
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //String country = "Germany";
        //countryInfo(parser, country);
        
        //listExportersTwoProducts(parser,"cotton", "coffee");
        
        //String exportItem = "coffee";
        //numberOfExporters(parser, exportItem);
        
        String amount = "$99,999,999,999";
        bigExporters(parser, amount);
    }

}
