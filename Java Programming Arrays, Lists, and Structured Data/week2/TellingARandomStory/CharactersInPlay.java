
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> myNames;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay(){
        myNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    private void update(String person){
        if(myNames.contains(person)){
            int index = myNames.indexOf(person);
            int freq = myFreqs.get(index);
            myFreqs.set(index, freq + 1);
        }
        else{
            myNames.add(person);
            myFreqs.add(1);
        }        
    }
    public void findAllCharacters(){
        myNames.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int index = line.indexOf(".");
            if (index == -1){
                continue;
            }
            String name = line.substring(0, index).trim().toUpperCase();
            update(name);
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < myNames.size(); i++){
            if(myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2){
                System.out.println(myNames.get(i) + " " + myFreqs.get(i));
            }
        }    
    }
    public int findIndexOfMax(){
        int max = 0;
        int maxIndex = 0;
        for(int i = 0; i < myFreqs.size(); i++){
            if(myFreqs.get(i) > max){
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < myNames.size(); i++){
            if(myFreqs.get(i) > 1){
                System.out.println(myNames.get(i) + " " + myFreqs.get(i));
            }
        }
        int max = findIndexOfMax();
        System.out.println("Character who occurs most is " + myNames.get(max) + " with " + myFreqs.get(max) 
        + " times.");
        charactersWithNumParts(10,15);
    }
}
