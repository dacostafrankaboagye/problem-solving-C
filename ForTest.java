
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
            if(listOfCountries.contains(country)){
                String exports = record.get("Exports");
                String value  = record.get("Value (dollars)");
                System.out.println(country + ": " + exports + ": " + value );
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
        
        //String country = "Nauru";
        //countryInfo(parser, country);
        
        listExportersTwoProducts(parser,"gold", "diamond");
        
        //String exportItem = "sugar";
        //numberOfExporters(parser, exportItem);
        
        //String amount = "$999,999,999,999";
        //bigExporters(parser, amount);
    }

}
