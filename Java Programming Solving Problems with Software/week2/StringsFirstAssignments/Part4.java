
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {
    public void youtubeReader(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        int start_quote;
        int end_quote;
        for (String line : ur.lines()){
            String clean_line = line.toLowerCase();
            int yt_index = clean_line.indexOf("youtube.com");
            if (clean_line.indexOf("youtube.com") != -1){
                start_quote = line.lastIndexOf("\"",yt_index);
                end_quote = line.indexOf("\"", start_quote + 1);
                System.out.println(line.substring(start_quote+1, end_quote));
            }
        }
    }
}
