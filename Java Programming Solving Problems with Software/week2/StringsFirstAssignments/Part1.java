
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int start_index = dna.indexOf("ATG");
        int end_index;
        
        if (start_index == -1) {
            System.out.println("ATG not found");
            return "";
        }
        
        end_index = dna.indexOf("TAA", start_index + 3);
        
        if (end_index == -1) {
            System.out.println("TAA not found");
            return "";
        }
        
        if ((end_index - start_index) % 3 == 0) {
            return dna.substring(start_index, end_index + 3);
        }
        
        else {
            System.out.println("Not a multiple of 3");
            return "";
        }
        
    }
    
    public void testSimpleGene() {
        //No ATG
        String test1 = "AATTGGTTCCATCGTAC";
        //No TAA
        String test2 = "ATGGTATGGATGGGATCCTAG";
        //TAA earlier than ATG
        String test3 = "TAAGTCGTATGGCAAGCGTT";
        //Not Multiple of 3
        String test4 = "ATGAAAGTAATATCCGTCTATAA";
        //Pass
        String test5 = "AAGTATGATCCGAGCTTAAGGCATA";
        String practicequiz1 = "AAATGCCCTAACTAGATTAAGAAACC";
        String[] tests = {test1, test2, test3, test4, test5, practicequiz1};
        
        for (int i = 0; i < tests.length; i++){
            System.out.println(tests[i]);
            String result = findSimpleGene(tests[i]);
            System.out.println(result);
        }
        
        
    }
    
}
