
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    public boolean twoOccurrences(String stringa, String stringb){
        int first_occur = stringb.indexOf(stringa);
        int a_len = stringa.length();
        int second_occur;
        boolean flag = false;
        if (first_occur != -1) {
            second_occur = stringb.indexOf(stringa, first_occur + a_len - 1);
            if (second_occur != -1) {
                flag = true;
            }
        }
        return flag;
    }
    
    public String lastPart(String stringa, String stringb){
        int start_index = stringb.indexOf(stringa);
        int a_len = stringa.length();
        if (start_index != -1){
            return stringb.substring(start_index + a_len);
        }
        else {
            return stringb;
        }
    }
    
    
    public void testing(){
        String test1a = "an";
        String test1b = "Banana";
        String test2a = "Pen";
        String test2b = "Pencil";
        String test3a = "atg";
        String test3b = "ctgtatgta";
        String test4a = "by";
        String test4b = "A story by Abby Long";
        String test5a = "zoo";
        String test5b = "forest";
        String[] tests = {test1a,test1b,test2a,test2b,test3a,test3b,test4a,test4b,test5a,test5b};
        for(int i = 0; i < tests.length; i+=2){
            System.out.println(twoOccurrences(tests[i], tests[i+1]));
            System.out.println(lastPart(tests[i], tests[i+1]));
        }
    }
    
    
}
