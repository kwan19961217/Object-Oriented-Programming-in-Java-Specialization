
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encrypt(String input){
        String output = "";
        char curr_char;
        int index;
        for (int i = 0; i < input.length(); i++){
            curr_char = input.charAt(i);
            index = alphabet.indexOf(Character.toLowerCase(curr_char));
            if (index != -1){
                if (i == 0 || i % 2 == 0){
                    output += convert(shiftedAlphabet1,index, curr_char);
                }
                else if (i % 2 == 1){
                    output += convert(shiftedAlphabet2, index, curr_char);
                }
            }
            else{
                output += curr_char;
            }
        }
        return output;        
    }
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
    //Generalized function
    private char convert(String shifted, int index, char input){
        char encrypted_char = shifted.charAt(index);
        if (Character.isUpperCase(input)){
            return Character.toUpperCase(encrypted_char);
        }
        else{
            return encrypted_char;
        }        
    }
}
