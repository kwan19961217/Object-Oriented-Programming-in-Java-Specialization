
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
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
    private String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    private int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 + dkey;
        }
        return dkey;
    }
    public String breakCaesarCipher(String input){
        String first = halfOfString(input, 0);
        String second = halfOfString(input, 1);
        int key1 = getKey(first);
        int key2 = getKey(second);
        System.out.println("First Key is " + key1 + "\nSecond Key is " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        return cc.decrypt(input);
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String e = cc.encrypt(input);
        String d = cc.decrypt(e);
        System.out.println(e);
        System.out.println(d);
        String s = breakCaesarCipher(e);
        System.out.println(s);
    }
    public void tests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println(breakCaesarCipher(input));
        
    }
}
