
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
        while(true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
                break;
            }
            System.out.println(gene);
            int index = dna.indexOf(gene);
            dna = dna.substring(index + gene.length());
        }
    }
    public int countGenes(String dna){
        int count = 0;
        while(true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
                break;
            }
            count++;
            int index = dna.indexOf(gene);
            dna = dna.substring(index + gene.length());
        }
        return count;
    }
    public void testCountGenes(){
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
        String DNA6 = "ATGTAAGATGATTTAGGGATGGCTCTGTGA";
        String DNA7 = "ATGCTAACTGACTAGATGCCATGCTAAATGATT";
        String[] tests ={DNA1,DNA2,DNA3,DNA4,DNA5,DNA6,DNA7};
        for (int i = 0; i < tests.length; i++){
            int count = countGenes(tests[i]);
        }    
    }
}
