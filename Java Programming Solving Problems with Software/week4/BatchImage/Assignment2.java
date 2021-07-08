
/**
 * Write a description of Assignment1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Assignment2 {
    public void selectAndCovnvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            String filename = f.getName();
            String new_filename = "inverted-" + filename;
            ImageResource inImage = new ImageResource(f);
            ImageResource newImage = makeInversion(inImage);
            newImage.setFileName(new_filename);
            newImage.save();
        }       
    }
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource ir = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel p : ir.pixels()){
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            int red = inPixel.getRed();
            int green = inPixel.getGreen();
            int blue = inPixel.getBlue();
            p.setRed(255 - red);
            p.setGreen(255 - green);
            p.setBlue(255 - blue);
        }
        return ir;
    }
}
