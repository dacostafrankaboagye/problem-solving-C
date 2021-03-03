
/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class FindGeneWhile {
    
    public String findGene(String dna){
        
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex+3);
        
        while(currIndex != -1){
            if((currIndex-startIndex)%3 == 0){
                return dna.substring(startIndex, currIndex+3);
            }
            else{
                currIndex = dna.indexOf("TAA", currIndex+1);
            }
            
            
        }
        return "";
        
    }
    
    public void testing(){
        
        // three dijjerent stop codone
        // TAA TGA TAG
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
	String ap = "atggggtttaaataataatag"; 
	
	System.out.println(findGene(a.toUpperCase()));
	System.out.println("completed!!!");
    }

}


