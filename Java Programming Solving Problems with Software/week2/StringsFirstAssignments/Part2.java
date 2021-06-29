
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int start_index;
        int end_index;
        
        if (dna.contains("A") || dna.contains("C") 
        || dna.contains("G") || dna.contains("T")) {
            dna = dna.toUpperCase();
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        else {
            dna = dna.toLowerCase();
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        
        start_index = dna.indexOf(startCodon);
        
        if (start_index == -1) {
            System.out.println("Start Codon not found");
            return "";
        }
        
        end_index = dna.indexOf(stopCodon, start_index + 3);
        
        if (end_index == -1) {
            System.out.println("Stop Codon not found");
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
        String test6 = test5.toLowerCase();
        String[] tests = {test1, test2, test3, test4, test5, test6};
        
        for (int i = 0; i < tests.length; i++){
            System.out.println(tests[i]);
            String result = findSimpleGene(tests[i],"ATG","TAA");
            System.out.println(result);
        }
        
        
    }
}
