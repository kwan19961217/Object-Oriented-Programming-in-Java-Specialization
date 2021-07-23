
/**
 * Write a description of OOCeasarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OOCaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public OOCaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    public String encrypt(String input){
        String output = "";       
        for (int i = 0; i < input.length(); i++){
            char curr_char = input.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(curr_char));
            if (index != -1){
                output += convert(index, curr_char);
            }
            else{
                output += curr_char;
            }
        }
        return output;        
    }
    public String decrypt(String input){
        OOCaesarCipher d = new OOCaesarCipher(26 - mainKey);
        return d.encrypt(input);
    }
    //Generalized function
    private char convert(int index, char input){
        char encrypted_char = shiftedAlphabet.charAt(index);
        if (Character.isUpperCase(input)){
            return Character.toUpperCase(encrypted_char);
        }
        else{
            return encrypted_char;
        }        
    }
}
