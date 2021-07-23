
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 + dkey;
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }
    public int[] countLetters(String s){
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
    public int maxIndex(int[] values){
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
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 + dkey;
        }
        return dkey;
    }
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        int key1 = getKey(first);
        int key2 = getKey(second);
        System.out.println("First Key is " + key1 + "\nSecond Key is " + key2);
        String output = cc.encryptTwoKeys(encrypted,26 - key1, 26 - key2);
        return output;
    }
    
    //tests
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String message = decrypt(fr.asString());
        System.out.println(message);
    }
    public void testHalfOfString(){
        FileResource fr = new FileResource();
        String message = halfOfString(fr.asString(), 0);
        System.out.println(message);        
    }
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String message = decryptTwoKeys(fr.asString());
        System.out.println(message);
        String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        CaesarCipher cc = new CaesarCipher();
        String e1 = cc.encrypt(first, 26 - 2);
        String e2 = cc.encrypt(second, 26 - 20);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < first.length(); i++){
            sb.append(e1.charAt(i));
            if(i == second.length()){
                continue;
            }
            sb.append(e2.charAt(i));
        }
        System.out.println(sb.toString());        
        message = decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
        System.out.println(message);        
    }    
}
