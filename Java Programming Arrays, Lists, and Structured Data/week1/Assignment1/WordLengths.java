
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){            
            int length = word.length();
            if (!Character.isLetter(word.charAt(0))){
                length--;
            }
            if (length > 0 && !Character.isLetter(word.charAt(length - 1))){
                length--;
            }            
            if (length >= 30){
                counts[30]++;
            }
            else if(length <= 0){
                continue;
            }
            else{
                counts[length]++;
            }
        }
    }
    public int indexOfMax(int[] values){
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
    //tests
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 1; i < counts.length; i++){
            if(counts[i] != 0){
                System.out.println(counts[i] + " words of length " + i);
            }
        }
        int index = indexOfMax(counts);
        System.out.println("Max counts is of index " + index);
    }
}
