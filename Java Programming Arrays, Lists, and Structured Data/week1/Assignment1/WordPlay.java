
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.Arrays;

public class WordPlay {
    public boolean isVowel(char ch){
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
        if (vowels.contains(Character.toLowerCase(ch))){
            return true;
        }
        return false;
    }
    public String replaceVowels(String phrase, char ch){
        String new_string = "";
        for (int i = 0; i < phrase.length(); i++){
            if(isVowel(phrase.charAt(i))){
                new_string += ch;
            }
            else{
                new_string += phrase.charAt(i);
            }        
        }        
        return new_string;
    }
    public String emphasize(String phrase, char ch){
        String new_string = "";
        ch = Character.toLowerCase(ch);
        for (int i = 0; i < phrase.length(); i++){
            if(Character.toLowerCase(phrase.charAt(i)) == ch){
                if(i == 0 || i % 2 == 0){
                    new_string += '*';
                }
                else{
                    new_string += '+';
                }
            }
            else{
                new_string += phrase.charAt(i);
            }        
        }        
        return new_string;
    }    
    //tests
    public void testIsVowel(){
        boolean test1 = isVowel('a');
        boolean test2 = isVowel('A');
        boolean test3 = isVowel('u');
        boolean test4 = isVowel('U');
        boolean test5 = isVowel('5');
        boolean test6 = isVowel('G');
        boolean[] tests = {test1, test2, test3, test4, test5, test6};
        for(boolean test : tests){
            System.out.println(test);
        }
    }
    public void testReplaceVowels(){
        String test1a = "AbcDe";
        String test1b = replaceVowels(test1a, '*');
        System.out.println(test1a);
        System.out.println(test1b);
    }
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }    
}
