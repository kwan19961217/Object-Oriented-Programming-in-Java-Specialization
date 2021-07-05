
/**
 * Write a description of exercise here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class exercise {
    //Question 1
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestHour = getLowest(parser, "TemperatureF");
        return coldestHour;
    }
    //Question 2
    public String fileWithColdestTemperature(){
        File coldestDay = getFileWithLowestRecord("TemperatureF");
        FileResource fr = new FileResource(coldestDay);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = getLowest(parser, "TemperatureF");
        System.out.println("Coldest day was in file " + coldestDay.getName());
        System.out.println("Coldest temperature on that day was " + coldestHour.get("TemperatureF"));
        parser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord record : parser){
            System.out.println(record.get("DateUTC")+": "+ record.get("TemperatureF"));
        }
        return coldestDay.getName();
    }
    //Question 3
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = getLowest(parser, "Humidity");
        return lowestHumidity;
    }
    //Question 4
    public CSVRecord lowestHumidityInManyFiles(){
        File lowestHumidityDay = getFileWithLowestRecord("Humidity");
        FileResource fr = new FileResource(lowestHumidityDay);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidityHour = getLowest(parser, "Humidity");
        return lowestHumidityHour;
    }    
    //Question 5
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        double average;
        for (CSVRecord record : parser){
            String s = record.get("TemperatureF");
            double d = Double.parseDouble(s);
            sum += d;
            count++;
        }
        average = sum / count;
        return average;
    }
    //Question 6
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        int count = 0;
        double average;
        for (CSVRecord record : parser){
            int humid = Integer.parseInt(record.get("Humidity"));
            if (humid < value){
                continue;
            }
            String s = record.get("TemperatureF");
            double d = Double.parseDouble(s);
            sum += d;
            count++;
        }
        average = sum / count;
        return average;        
    }
    //Generalized Function
    //Get the lowest record for a field
    public CSVRecord getLowest(CSVParser parser, String header){
        CSVRecord lowest = null;
        for (CSVRecord record : parser){
            if (lowest == null){
                lowest = record;
            }
            else{
                String s = record.get(header);
                if (s.equals("N/A")){
                    continue;
                }
                double temp = Double.parseDouble(s);
                
                if (temp < Double.parseDouble(lowest.get(header)) &&
                temp != -9999){
                    lowest = record;
                }
            }
        }
        return lowest;    
    }
    //Get the file with lowest record
    public File getFileWithLowestRecord(String header){
        File lowestDay = null;
        CSVRecord lowestHour = null;        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord temp = getLowest(parser, header);
            if (lowestDay == null){
                lowestHour = temp;
                lowestDay = f;
            }
            else{
                double currentData = Double.parseDouble(temp.get(header));
                if (currentData < Double.parseDouble(lowestHour.get(header))){
                    lowestHour = temp;
                    lowestDay = f;
                }
            }
        }
        return lowestDay;
    }
    
    //Test for Question 1
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = coldestHourInFile(parser);
        System.out.println(result.get("TemperatureF"));
    }
    //Test for Question 2
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();

    }
    //Test for Question 3
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + result.get("Humidity") + " at " + result.get("DateUTC"));
    }
    //Test for Question 4
    public void testLowestHumidityInManyFiles(){
        CSVRecord result = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + result.get("Humidity") + " at " + result.get("DateUTC"));
    }
    //Test for Question 5
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    //Test for Question 6
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (!Double.isNaN(average)){
            System.out.println("Average Temp when high Humidity is " + average);
        }
        else{
            System.out.println("No temperatures with that humidity");
        }
    }
}
