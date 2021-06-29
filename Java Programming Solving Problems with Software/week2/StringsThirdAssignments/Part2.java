
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public float cgRatio(String dna){
        int count = 0;
        int length = dna.length();
        for(int i = 0; i < length; i++){
            if (dna.charAt(i) == 'A' || dna.charAt(i) == 'G'){
                count ++;
            }
        }
        float result = (float) count / length;
        return result;
    }
    public int countCTG(String dna){
        int count = 0;
        int stopIndex = dna.indexOf("CTG");
        while (stopIndex != -1){
            count++;
            stopIndex = dna.indexOf("CTG", stopIndex + 1);
        }        
        return count;
    }
}
