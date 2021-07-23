import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++){
            String s = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int curr = cc.getKey(s);
            key[i] = curr;
        }
        return key;
    }

    public void breakVigenere() {
        DirectoryResource dr = new DirectoryResource();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>>languages = new HashMap<String, HashSet<String>>();
        for(File f : dr.selectedFiles()){
            FileResource dictFr = new FileResource(f);
            HashSet<String> dict = readDictionary(dictFr);
            languages.put(f.getName(), dict);
        }
        String decrypted = breakForAllLangs(encrypted, languages);
        System.out.println(decrypted.substring(0,100));
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String word : fr.lines()){
            dict.add(word.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        String[] words = message.split("\\W+");
        for(String word : words){
            if(dictionary.contains(word.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String>dictionary, char mostCommonChar){
        int maxCount = 0;
        String decrypted = "";
        for (int i = 1; i <= 100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String d = vc.decrypt(encrypted);
            int count = countWords(d, dictionary);
            if(i == 38){
                System.out.println("38: " + count);
            }
            if (count > maxCount){
                maxCount = count;
                decrypted = d;
                System.out.println(keys.length);
            }            
        }
        System.out.println(maxCount);
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> countChar = new HashMap<Character, Integer>();
        for(String word : dictionary){
            for(int i = 0; i < word.length(); i++){
                char c = word.toLowerCase().charAt(i);
                if (!countChar.containsKey(c)){
                    countChar.put(c, 1);
                }
                else{
                    countChar.put(c, countChar.get(c) + 1);
                }                
            }
        }
        int max = 0;
        char maxChar = '\u0000';
        for(char c : countChar.keySet()){
            if(countChar.get(c) > max){
                max = countChar.get(c);
                maxChar = c;
            }
        }
        return maxChar;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>>languages){
        int maxCount = 0;
        String decrypted = "";    
        String corrLang = "";
        for(String lang : languages.keySet()){
            HashSet<String> dictionary = languages.get(lang);
            char mostCommonChar = mostCommonCharIn(dictionary);
            String d = breakForLanguage(encrypted, dictionary, mostCommonChar);
            int count = countWords(d, dictionary);
            if (count > maxCount){
                maxCount = count;
                decrypted = d;
                corrLang = lang;
            }   
        }
        System.out.println(corrLang);
        return decrypted;
    }
}
