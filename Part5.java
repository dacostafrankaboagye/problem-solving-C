
/*
Frank Aboagye
Gene finding algorithm.

*/

import edu.duke.*;
import java.io.*;

public class Part5 {
    

    
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

        int minIndex = 0;
       
        if(taaIndex == -1 || (tgaIndex !=-1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{minIndex = taaIndex;}

        if(minIndex == -1 || (tagIndex !=-1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == -1){return "";}
        
        return dna.substring(startIndex, minIndex+3);
        
    }


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
    
    public StorageResource getAllGenes(String dna){
        // create empty storage res.
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            
            if(currentGene.isEmpty()){break;}
            
            // add to the list
            geneList.add(currentGene);
            
            startIndex = dna.indexOf(currentGene,startIndex) + 
                                        currentGene.length();
        }
        
        return geneList;
        
    }
    
    public int allCal(String input, String chr){
        int index = input.indexOf(chr);
        int count = 0;
        while(true){
            if(index == -1 || index > input.length() - chr.length()){break;}
            index = input.indexOf(chr,index+chr.length());
            count++;
        }
        return count;
    }
    
    public double cgRatio(String dna){ // its already a DNA
        int Num = allCal(dna, "C") + allCal(dna, "G");
        
        return (double)Num/dna.length();

    }
    
    public int countCTG(String dna){
        String CTG = "CTG";
        return allCal(dna, CTG);
    }
    
    public void Likeplay(){
        System.out.println(cgRatio("ATGCCATAG"));
    }
 
    public void testing(){
        String a = "atgatgxcctaaatgtgaatgyyytaatgasjkatg";
        String b = "ATGCCACTGTAG";
        //printAllGenes(a.toUpperCase());
        //StorageResource genes = getAllGenes(a.toUpperCase());
        StorageResource genes = getAllGenes(b);        
        for(String i: genes.data()){
            System.out.println("DNA strand = " + i);
            System.out.println("cgRatio = " + cgRatio(i));
            System.out.println("Number of CTG = " + countCTG(i));
        }
        //cgRatio()
        genes.clear();
        

        System.out.println("completed!!!");
    }
}


