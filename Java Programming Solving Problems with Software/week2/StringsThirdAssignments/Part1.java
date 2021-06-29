
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.StorageResource;
import edu.duke.FileResource;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (stopIndex != -1){
            int diff = stopIndex - startIndex;
            if (diff % 3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        return dna.length();
    }
    public String findGene(String dna){
        int closest;
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int taaindex = findStopCodon(dna, startIndex, "TAA");
        int tagindex = findStopCodon(dna, startIndex, "TAG");
        int tgaindex = findStopCodon(dna, startIndex, "TGA");
        if (taaindex < tagindex){
            closest = taaindex;
        }
        else {
            closest = tagindex;
        }
        if (tgaindex < closest){
            closest = tgaindex;
        }
        if(closest < dna.length()){
            return dna.substring(startIndex, closest + 3);
        }
        else{
            return "";
        }
    }
    public void printAllGenes(String dna){
        int count = 0;
        while(true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
                break;
            }
            count++;
            System.out.println(gene);
            int index = dna.indexOf(gene);
            dna = dna.substring(index + gene.length());
        }
        System.out.println("Number of genes: " + count);
    }
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        while(true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
                break;
            }
            geneList.add(gene);
            int index = dna.indexOf(gene);
            dna = dna.substring(index + gene.length());
        }
        return geneList;
    }
    
    
    
    //Part2
    public float cgRatio(String dna){
        int count = 0;
        int length = dna.length();
        for(int i = 0; i < length; i++){
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                count ++;
            }
        }
        float result = (float) count / length;
        return result;
    }
    public int countCTG(String dna){
        int count = 0;
        //StorageResource geneList = getAllGenes(dna);
        //for (String s : geneList.data()){
        //    int stopIndex = s.indexOf("CTG");
        //    while (stopIndex != -1){
        //        if(stopIndex % 3 == 0){
        //            count++;
        //        }
        //        stopIndex = s.indexOf("CTG", stopIndex + 1);
        //    }        
        //}
        int index = dna.indexOf("CTG");
        while (index != -1){
            count++;
            index = dna.indexOf("CTG", index + 3);
        }
        return count;
    }
    
    
    
    
    //Part3
    public void processGenes(StorageResource sr){
        int len_count = 0;
        int cg_count = 0;
        int longest = 0;
        int sixty_count = 0;
        for (String s : sr.data()){
            if(s.length() > 9){
                //Print all strings longer than 9
                System.out.println("Longer than 9 characters: " + s);
                len_count++;
            }
            if (cgRatio(s) > 0.35){
                System.out.println("C-G-ratio higher than 0.35: " + s);
                cg_count++;
            }
            if (s.length() > longest){
                longest = s.length();
            }
            if (s.length() > 60){
                System.out.println("Longer than 60 characters: " + s);
                sixty_count++;
            }
        }
        //print the number of string longer than 9
        System.out.println("Number of strings longer than 9 characters: " + len_count);
        System.out.println("Number of strings higher than 0.35: " + cg_count);
        System.out.println("Longest gene's length is: " + longest);
        System.out.println("Number of strings longer than 60 characters: " + sixty_count);
    }
    
    
    
    
    //Tests
    public void testFindCodon(){
        String dna = "ATGAAATTTGGGAAATTTGGGTGA";
        for (int i = 0; i < 6; i++){
            int result = findStopCodon(dna, i, "TGA");
            System.out.println(result);
        }
    }
    public void testFindGene(){
        //No ATG
        //             012345678901234567890123456789
        String DNA1 = "AGGTAGGTCGTAGCGTAGCTTACCGGATCA";
        //One valid stop
        String DNA2 = "GGGTTTGATGGGATCAGGTACGGTATGACC";
        //Multiple valid stop
        String DNA3 = "GGGTTTGATGGGATCATAGACTTAATGACC";
        //No valid stop
        String DNA4 = "CCATATGCCACGTCTACGTCATCCTGCACG";
        //Two valid stop
        String DNA5 = "CCATGCGTAACTAATGACCTACTACCTACG";
        String[] tests = {DNA1,DNA2,DNA3,DNA4,DNA5};
        for (int i = 0; i < tests.length; i++){
            printAllGenes(tests[i]);
            StorageResource geneList = getAllGenes(tests[i]);
            for (String s : geneList.data()){
                System.out.println(s);
            }
        }
    }
    
    public void testProcessGenes(){
        //             012345678901234567890123456789
        //somes genes longer than 9 chars
        //String DNA1 = "ATGCACCGGTAAATGCATAGATAGATGTAA";
        //no genes longer than 9 chars
        //String DNA2 = "ATGTAAATGTGAATGTAGATGTAACCCGGG";
        //Higher than 0.35
        //String DNA3 = "ATGCCCTAAATGGGGTGAATGCGCTAGCCC";
        //Lower than 0.35
        //String DNA4 = "ATGATATAAATGTTTTAG";
        //String DNA5 = "CCATGCGTAACTAATGACCTACTACCTACG";
        //String[] tests = {DNA1,DNA2,DNA3,DNA4,DNA5};
        //for(int i = 0; i < tests.length; i++){
        //    System.out.println("Case Number " + i);
        //    StorageResource geneList = getAllGenes(tests[i]);
        //    processGenes(geneList);
        //}
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        printAllGenes(dna.toUpperCase());
        StorageResource geneList = getAllGenes(dna.toUpperCase());
        processGenes(geneList);
    }
    
    public void testCountCTG(){
        int count;
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        count = countCTG(dna.toUpperCase());
        System.out.println(count);
    }
}
