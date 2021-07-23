
/**
 * Write a description of TestOOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestOOCaesarCipher {
    private int[] countLetters(String s){
        int[] freqs = new int[26];
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < s.length(); i++){
            char curr = Character.toLowerCase(s.charAt(i));
            if(Character.isLetter(curr)){
                int j = letters.indexOf(curr);
                freqs[j]++;
            }
        }
        return freqs;
    }
    private int maxIndex(int[] values){
        int max = 0;
        int index = 0;
        for(int i=0; i < values.length; i++){
            if(values[i] > max){
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 + dkey;
        }
        System.out.println("The key is " + dkey);
        OOCaesarCipher cc = new OOCaesarCipher(dkey);
        return cc.decrypt(input);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        OOCaesarCipher cc = new OOCaesarCipher(18);
        String encrypted = cc.encrypt(fr.asString());
        System.out.println(encrypted);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println(decrypted);      
    }
    public void tests(){
        OOCaesarCipher cc = new OOCaesarCipher(15);
        String one = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(one);
    }
}
