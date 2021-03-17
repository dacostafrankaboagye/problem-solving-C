
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public int findStopCodon(String dna, 
                             int startIndex,
                             String stopCodon){
        // we have identified ATG here => startIndex;
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        
        while(currIndex != -1){
            if((currIndex-startIndex)%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
            
            
        }
        return dna.length();
        
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if (startIndex == -1){ return "";}
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if(minIndex == dna.length()){return "";}
        else{ return dna.substring(startIndex, minIndex+3);}        
        
    }
    

    
    public void printAllGenes(String dna){
        // set startIndex  to 0
        int startIndex = 0;
        int count = 0; // we want to count it
        // repeat the following steps
        while(true){
            //find the next gene after start index
            String currentGene = findGene(dna,startIndex);
            // if no gene was found, leave this loop
            if(currentGene.isEmpty()){break;}
            
            //print that gene out
            System.out.println(currentGene);
            count ++;// 1 is added to the count;
            
            // set startIndex to justt past the end of the gene;
            startIndex = dna.indexOf(currentGene,startIndex) + 
                                        currentGene.length();
            
            
        }
        System.out.println("found " + count);
      
    }
    
    public void testPrintAllGenes(){
        String dna1 = "taatagssh";
        String dna2 = "AATGCTAACTAGCTGACTAAT";
        String dna3 = "patgxxxyyytgaptaaxyxtag";
        String dna4 = "atgyyyyytay";
        String dna5 = "atgvvvtaaiiitgallltag";
        
        printAllGenes(dna2.toUpperCase());
        System.out.println("________\nTesting Done!");       

        
    }
    
}

