
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        while(true){
            int currIndex = stringb.indexOf(stringa);
            if (currIndex == -1){
                break;
            }
            else{
                count++;
                stringb = stringb.substring(currIndex + stringa.length());
            }
        }
        return count;
    }
    public void testHowMany(){
        String test1a = "GAA";
        String test1b = "ATGAACGAATTGAATC";
        String test2a = "AA";
        String test2b = "ATAAAA";
        String test3a = "ana";
        String test3b = "banana";
        String[] tests = {test1a, test1b, test2a, test2b, test3a, test3b};
        for (int i = 0; i < tests.length; i += 2){
            int result = howMany(tests[i], tests[i+1]);
            System.out.println(result);
        }   
    }
}
