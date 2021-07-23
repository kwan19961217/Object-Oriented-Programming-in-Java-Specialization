
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> counts0;
    private HashMap<String, Integer> counts1;
    private HashMap<String, Integer> counts2;
    HashMap[] countsList;
    public CodonCount(){
        counts0 = new HashMap<String, Integer>();
        counts1 = new HashMap<String, Integer>();
        counts2 = new HashMap<String, Integer>();
        countsList = new HashMap[]{counts0, counts1, counts2};
    }
    public void buildCodonMap(int start, String dna){
        counts0.clear();
        counts1.clear();
        counts2.clear();
        for(int i = 0; i < countsList.length; i++){
            int temp = start + i;
            HashMap<String, Integer> map = countsList[i];
            while(temp + 2 < dna.length()){
                String codon = dna.substring(temp, temp + 3);                
                if(map.containsKey(codon)){
                    map.put(codon, map.get(codon) + 1);
                }
                else{
                    map.put(codon, 1);
                }
                temp += 3;
            }
        }
    }
    public ArrayList<String> getMostCommonCodon(){
        ArrayList<String> list = new ArrayList<String>();
        for(HashMap<String, Integer> map : countsList){
            int max = 0;
            String maxCodon = "";
            for(String s: map.keySet()){
                if (map.get(s) > max){
                    max = map.get(s);
                    maxCodon = s;
                }
            }
            list.add(maxCodon);
        }
        return list;  
    }
    public void printCodonCounts(int start, int end){
        for(HashMap<String, Integer> map : countsList){
            System.out.println("Starting for a frame:");
            for(String s : map.keySet()){
                int count = map.get(s);
                if(count >= start && count <= end){
                    System.out.println(s + ": " + count);
                }
            }
            System.out.println("End");
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        for(String dna : fr.lines()){
            buildCodonMap(0, dna.trim());
        }
        ArrayList<String> mostList = getMostCommonCodon();
        for(int i = 0; i < mostList.size(); i++){
            String maxCodon = mostList.get(i);
            System.out.println("Reading fram starting with " + i +
            " results in " + countsList[i].size() + " unique codons and most common codon is " + 
            maxCodon + " with count " + countsList[i].get(maxCodon));
        }
        printCodonCounts(1, 8);
    }
}
