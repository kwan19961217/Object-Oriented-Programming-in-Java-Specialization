import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num = 0;
        for (Point currPt : s.getPoints()){
            num++;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perimeter = getPerimeter(s);
        double num = getNumPoints(s);
        double average_length = perimeter / num;
        return average_length;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largest = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currSide = prevPt.distance(currPt);
            if(currSide > largest){
                largest = currSide;
            }
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX;
        Point prevPt = s.getLastPoint();
        largestX = prevPt.getX();
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if(currX > largestX){
                largestX = currX;
            }
            prevPt = currPt;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPerimeter){
                largestPerimeter = getPerimeter(s);
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double largestPerimeter = 0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPerimeter){
                largestPerimeter = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int num = getNumPoints(s);
        System.out.println("number = " + num);
        double average_length = getAverageLength(s);
        System.out.println("average length = " + average_length);
        double largest = getLargestSide(s);
        System.out.println("largest side = " + largest);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestPerimeterFile = getFileWithLargestPerimeter();
        System.out.println("largest perimeter file = " + largestPerimeterFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
