
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word: fr.words()){
            word = word.toLowerCase();
            if(myWords.contains(word)){
                int index = myWords.indexOf(word);
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
            else{
                myWords.add(word);
                myFreqs.add(1);
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
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for(int i = 0 ; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + ": " + myFreqs.get(i));
        }
        int max = findIndexOfMax();
        System.out.println("The number of unique words is " + myWords.size());
        System.out.println("The word that occurs most often and its count are: "
        + myWords.get(max) + " " + myFreqs.get(max));
    }
}
