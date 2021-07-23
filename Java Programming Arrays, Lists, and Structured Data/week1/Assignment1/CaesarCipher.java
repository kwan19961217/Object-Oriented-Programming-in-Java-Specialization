
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String encrypted_alphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String output = "";
        char curr_char;
        int index;        
        for (int i = 0; i < input.length(); i++){
            curr_char = input.charAt(i);
            index = alphabet.indexOf(Character.toLowerCase(curr_char));
            if (index != -1){
                output += convert(encrypted_alphabet, index, curr_char);
            }
            else{
                output += curr_char;
            }
        }
        return output;
    }
    public String encryptTwoKeys(String input, int key1, int key2){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String encrypt1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String encrypt2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        String output = "";
        char curr_char;
        int index;
        for (int i = 0; i < input.length(); i++){
            curr_char = input.charAt(i);
            index = alphabet.indexOf(Character.toLowerCase(curr_char));
            if (index != -1){
                if (i == 0 || i % 2 == 0){
                    output += convert(encrypt1, index, curr_char);
                }
                else if (i % 2 == 1){
                    output += convert(encrypt2, index, curr_char);
                }
            }
            else{
                output += curr_char;
            }
        }
        return output;
    }
    //Generalized Function
    private char convert(String encrypted_alphabet, int index, char input){
        char encrypted_char = encrypted_alphabet.charAt(index);
        if (Character.isUpperCase(input)){
            return Character.toUpperCase(encrypted_char);
        }
        else{
            return encrypted_char;
        }        
    }
    
    
    //tests
    public void testEncrypt(){
        String before = "FIRST LEGION ATTACK EAST FLANK!";
        String after = encrypt(before, 23);
        System.out.println(after);
        before = "First Legion";
        after = encrypt(before, 23);
        System.out.println(after); 
        before = "First Legion";
        after = encrypt(before, 17);
        System.out.println(after);
        before = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        after = encrypt(before, 15);
        System.out.println(after);
    }
    public void testEncryptTwoKeys(){
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
        String s = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(encryptTwoKeys(s, 8, 21));
    }
}

