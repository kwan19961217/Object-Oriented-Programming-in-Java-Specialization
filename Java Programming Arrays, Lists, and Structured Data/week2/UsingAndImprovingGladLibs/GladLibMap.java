import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList> myMap;
    private ArrayList<String> usedWords;
    private Random myRandom;
    private ArrayList<String> usedCats;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        myMap = new HashMap<String, ArrayList>();
        String[] categories ={"adjective", "noun", "color", "country",
        "name", "animal", "timeframe", "verb", "fruit"};
        for(String cat : categories){
            myMap.put(cat,readIt(source + "/" + cat +".txt"));            
        }
        usedWords = new ArrayList<String>();
        usedCats = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if (myMap.containsKey(label)){
            return randomFrom(myMap.get(label));      
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if (!usedCats.contains(sub)){
            usedCats.add(sub);
        }        
        while (usedWords.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }        
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    public int totalWordsInMap(){
        int count = 0;
        for(String cat : myMap.keySet()){
            count += myMap.get(cat).size();
        }
        return count;
    }
    public int totalWordsConsidered(){
        int count = 0;
        for(String cat : myMap.keySet()){
            if(usedCats.contains(cat)){
                count += myMap.get(cat).size();
            }
        }
        return count;
    }    
    
    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal number of words to pick from: " + totalWordsInMap());
        System.out.println("Total number of words considered: " + totalWordsConsidered());
    }
    


}
