
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> filesOfWords;
    
    public WordsInFiles(){
        filesOfWords = new HashMap<String, ArrayList<String>>();
    }
   
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            if(filesOfWords.containsKey(word)){
                if(!filesOfWords.get(word).contains(f.getName())){
                    filesOfWords.get(word).add(f.getName());
                }
            }
            else{
                ArrayList<String> al = new ArrayList<String>();
                al.add(f.getName());
                filesOfWords.put(word, al); 
            }
        }        
    }
    
    public void buildWordFileMap(){
        filesOfWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;        
        for(String word : filesOfWords.keySet()){
            int currSize = filesOfWords.get(word).size();
            if(currSize > max){
                max = currSize;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> al = new ArrayList<String>();
        for(String word :filesOfWords.keySet()){
            if(filesOfWords.get(word).size() == number){
                al.add(word);
            }
        }
        return al;
    }
    
    public void printFilesIn(String word){
        System.out.println(word + " is in below files: ");
        ArrayList<String> al = filesOfWords.get(word);
        for(String name : al){
            System.out.println(name);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        System.out.println("Highest occurance: " + maxNumber());
        ArrayList<String> al = wordsInNumFiles(4);
        int count = 0;
        System.out.println("Below words satisfy the required number of files: ");
        for(String word : al){
            count++; 
            System.out.println(word);
        }
        System.out.println("total of " + count + " words satisfies");
        printFilesIn("tree");
        printFilesIn("sea");
    }
}

