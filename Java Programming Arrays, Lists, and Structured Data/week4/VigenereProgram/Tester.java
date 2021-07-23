
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class Tester {
    public void testTryKeyLength(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();        
        int[]keys = vb.tryKeyLength(fr.asString(), 4, 'e');
        for(int key : keys){
            System.out.print(key+ " ");
        }
    }    
}
