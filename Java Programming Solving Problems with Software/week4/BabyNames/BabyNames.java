
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyNames {
    public void totalBirths(CSVParser parser){
        int births = 0;
        int boys = 0;
        int girls = 0;
        int g_name = 0;
        int b_name = 0;
        for (CSVRecord record : parser){
            String s = record.get(2);
            int num = Integer.parseInt(s);
            births += num;
            if (record.get(1).equals("F")){
                girls += num;
                g_name++;
            }
            else if (record.get(1).equals("M")){
                boys += num;
                b_name++;
            }
        }
        System.out.println("Number of births: " + births);
        System.out.println("Number of girls: " + girls);
        System.out.println("Number of girls' names :" + g_name);
        System.out.println("Number of boys: " + boys);
        System.out.println("Number of boys' names :" + b_name);
    }
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                rank++;
            }
            if(record.get(0).equals(name) && record.get(1).equals(gender)){
                return rank;
            }
        }
        return -1;
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                rank--;
            }
            if(rank == 0){
                return record.get(0);
            }
        }      
        return "NO NAME";
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    public int yearOfHighestRank(String name, String gender){
        int curr = 0;
        int highestRank = 0;
        int year = 0;
        int yearOfHighestRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            String filename = f.getName();
            year = Integer.parseInt(filename.substring(3, 7));
            curr = getRank(year, name, gender);
            if (highestRank == 0 && curr != -1){
                highestRank = curr;
                yearOfHighestRank = year;
            }
            if (curr < highestRank && curr != -1){
                highestRank = curr;
                yearOfHighestRank = year;
            }            
        }
        return yearOfHighestRank;
    }
    public double getAverageRank(String name, String gender){
        double curr = 0.0;
        double sum = 0.0;
        int year;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            String filename = f.getName();
            year = Integer.parseInt(filename.substring(3, 7));
            curr = getRank(year, name, gender);
            if (curr != -1){
                sum += curr;
                count++;
            }
        }
        if (sum != 0.0){
            return sum / count;
        }
        else {
            return -1.0;
        }
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int sum = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                if (record.get(0).equals(name)){
                    break;
                }
                else {
                    sum += Integer.parseInt(record.get(2));
                }
            }
        }      
        return sum;
    }
    //Tests
    //Tests yearOfHighestRank
    public void testTotalBirth(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        totalBirths(parser);
    }
    public void testGetRank(){
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    public void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    public void testYearOfHighestRank(){
        int result = yearOfHighestRank("Mich", "M");
        System.out.println(result);
    }
    //Tests averageRank
    public void testAverageRank(){
        double result = getAverageRank("Robert", "M");
        System.out.println(result);
    }
    public void testGetTotalBirthsRankedHigher(){
        int result = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(result);
    }
}