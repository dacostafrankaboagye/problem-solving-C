
/**
 * Write a description of ForGhana here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 */


import edu.duke.*;
import org.apache.commons.csv.*;


public class ForGhana {
    
    
    
    public void TestGhana(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            String country = record.get("Country");
            if(country.contains("Ghana")){
                String ghanaExports = record.get("Exports");
                System.out.println(ghanaExports);
            }
        }
        
    }
    
    

}


