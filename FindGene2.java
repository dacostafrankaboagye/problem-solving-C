
/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class FindGene2 {
    

    
    public int findStopCodon(String dna, 
                             int startIndex,
                             String stopCodon){
        
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        
        while(currIndex != -1){
            if((currIndex-startIndex)%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
            
            
        }
        return -1;
        
    }
    
    public String findGene(String dna){
        // find the occurtance of atg ,startindex
        int startIndex = dna.indexOf("ATG");
        // if the start index is -1, return ""
        if (startIndex == -1){ return "";}
        
        // store in their various index.
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        // store the smallest of these three in minIndex;
        //int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        //if(minIndex == dna.length()){return "";}
        //else{ return dna.substring(startIndex, minIndex+3);}
        
        // but we dont want to return the length, rather, -1: very complex
        // two cases here;
        
        // case 1
        // if taaIndex ==-1 OR 
        // if tgaIndex != -1 AND tgaIndex < taaIndex;
        // the set minIndex = tga
        //else set minIndex = taaIndex
        int minIndex = 0;
       
        if(taaIndex == -1 || (tgaIndex !=-1 && tgaIndex < taaIndex)){
            minIndex = tagIndex;
        }
        else{minIndex = taaIndex;}
        
        // if minIndex ==-1 OR 
        // if tagIndex != -1 AND tagIndex < minIndex;
        // the set minIndex = tag
        // if minIndex = -1 return ""
        if(minIndex == -1 || (tagIndex !=-1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == -1){return "";}
        
        return dna.substring(startIndex, minIndex+3);
        
    }    

    
    public void testing(){
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
                  //   ^ ^     ^ ^ ^ ^
        // potential = atggggtttaaataa
        //String ap = "atggggtttaaataataatag";
        
        //String d = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        //int dex = findStopCodon(d,0, "TAA");
        //if(dex != 8){System.out.println("error occured. output is " + dex);}
        
        String dex = findGene(a.toUpperCase());
        System.out.println(dex);

        System.out.println("completed!!!");
    }

}


