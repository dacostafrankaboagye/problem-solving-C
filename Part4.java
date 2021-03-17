
/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part4 {
    

    
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
    
    public String findGene(String dna, int where){
        // find the occurtance of atg ,startindex
        int startIndex = dna.indexOf("ATG", where);
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
            minIndex = tgaIndex;
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

/*
    public void testRes () {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        
        System.out.println("        The content as string      ");
        System.out.println("___________________________________");
        System.out.println(contents);
        System.out.println("___________________________________");
        
        String dex = findGene(contents.toUpperCase(), 0);
        System.out.println(dex);

        System.out.println("completed!!!");
        
        
    }
 */
    public void printAllGenes(String dna){
        // set startIndex  to 0
        int startIndex = 0;
        int count = 0; // we want to count it
        // repeat the following steps
        while(true){
            //find the next gene after start index
            String currentGene = findGene(dna, startIndex);
            // if no gene was found, leave this loop
            if(currentGene.isEmpty()){break;}
            
            //print that gene out
            System.out.println(currentGene);
            count ++; // 1 is added to the count;
            
            // set startIndex to justt past the end of the gene;
            startIndex = dna.indexOf(currentGene,startIndex) + 
                                        currentGene.length();
            
            
        }
        System.out.println("found: " + count);
      
    }
    

    
    public void testing(){
        //String a = "cccatggggtttaaataataataggagagagagagagagttt";
                  //   ^ ^     ^ ^ ^ ^
        // potential = atggggtttaaataa
        //String ap = "atggggtttaaataataatag";
        //String non = "usjaoanwiamaan";
        //String d = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        //int dex = findStopCodon(d,0, "TAA");
        //if(dex != 8){System.out.println("error occured. output is " + dex);}
        
        String a = "atgatgxxxtaaatgtgaatgyyytaatgasjkatg";
        //String dex = findGene(a.toUpperCase(),0);
        printAllGenes(a.toUpperCase());
        //System.out.println(dex);

        System.out.println("completed!!!");
    }

}


