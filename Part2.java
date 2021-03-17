
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

// how many times a string appears in another string
public class Part2 {
    

    
    public int howMany(String a, String b){
        
        int count = 0;
        int startIndex = b.indexOf(a);
        if (startIndex == -1){return 0;}
        while(true){
            count++;
            startIndex = b.indexOf(a, startIndex + a.length());
            if(startIndex == -1){break;}
           
            
        }
        return count;
    }
    
    public void testHowMany(){
        String b = "ATAAAA";
        String a = "AA";
        //String b = "ATGAACGAATTGAATC";
        //String a = "GAA";
        System.out.println("Testing started!!");
        int tester = howMany(a, b);
        System.out.println("There are " + tester + " occurances");
        System.out.println("Testing Completed!!");
    }

}
