
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);    
    }
    
    public void testUniqueIPVisitsOnday(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ips = la.uniqueIPVisitsOnday("Sep 24");
        System.out.println("Sep 24: " + ips.size());
        for (String ip : ips){
            System.out.println(ip);
        }        
        ips = la.uniqueIPVisitsOnday("Sep 30");
        System.out.println("Sep 30: " + ips.size());
        for (String ip : ips){
            System.out.println(ip);
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int test1 = la.countUniqueIPsInRange(400, 499);
        int test2 = la.countUniqueIPsInRange(300, 399);
        System.out.println(test1);
        System.out.println(test2);
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> count = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(count));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");  
        HashMap<String, Integer> count = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(count));
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.iPsForDays());
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");  
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(iPsForDays));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");  
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(iPsForDays, "Sep 30"));
    }
}
