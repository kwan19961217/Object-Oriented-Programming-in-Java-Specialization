
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> ips = findUniqueIPs();
         return ips.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry record : records){
             int status = record.getStatusCode();
             if(status > num){
                 System.out.println(record);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnday(String someday){
         ArrayList<String> uniqueIPsVisited = new ArrayList<String>();
         for(LogEntry record : records){
             String date = record.getAccessTime().toString();
             String mmmDD = date.substring(4,10);
             if(someday.equals(mmmDD)){
                 String ip = record.getIpAddress();
                 if(!uniqueIPsVisited.contains(ip)){
                     uniqueIPsVisited.add(ip);
                 }
             }
         }
         return uniqueIPsVisited;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         int count = 0;
         ArrayList<String> ips = new ArrayList<String>();
         for (LogEntry record : records){
             int status = record.getStatusCode();
             if(status >= low && status <= high){
                 String ip = record.getIpAddress();
                 if(!ips.contains(ip)){
                     count++;
                     ips.add(ip);
                 }
             }
         }
         return count;
     }
     
     private ArrayList<String> findUniqueIPs(){
         ArrayList<String> ips = new ArrayList<String>();
         for(LogEntry record : records){
             String ip = record.getIpAddress();
             if(ips.contains(ip)){
                 continue;
             }
             ips.add(ip);
         }      
         return ips;
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry record : records){
             String ip = record.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip, 1);
             }
             else{
                 counts.put(ip, counts.get(ip) + 1);
             }
         }             
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for(String ip : counts.keySet()){
            if(counts.get(ip) > max){
                max = counts.get(ip);
            }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> list = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for(String ip : counts.keySet()){
            if(counts.get(ip) == max){
                list.add(ip);
            }
         }   
         return list;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for(LogEntry record : records){
             String date = record.getAccessTime().toString();
             String mmmDD = date.substring(4,10);
             if(!map.containsKey(mmmDD)){
                 map.put(mmmDD, new ArrayList<String>());
             }
             map.get(mmmDD).add(record.getIpAddress());
            }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays){
         int max = 0;
         String date = "";
         for(String day: iPsForDays.keySet()){
            if (iPsForDays.get(day).size() > max){
                max = iPsForDays.get(day).size();
                date = day;
            }
         }
         return date;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays,
     String date){
         ArrayList<String> ips = iPsForDays.get(date);
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(String ip : ips){
             if(!counts.containsKey(ip)){
                 counts.put(ip, 1);
             }
             else{
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return iPsMostVisits(counts);
     }
}
