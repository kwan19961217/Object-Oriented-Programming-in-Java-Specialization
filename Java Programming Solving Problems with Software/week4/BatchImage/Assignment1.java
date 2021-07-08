
/**
 * Write a description of Assignment1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Assignment1 {
    public void batchGrayscale(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            String filename = f.getName();
            String new_filename = "gray-" + filename;
            ImageResource inImage = new ImageResource(f);
            ImageResource newImage = convertGrayscale(inImage);
            newImage.setFileName(new_filename);
            newImage.save();
        }       
    }
    public ImageResource convertGrayscale(ImageResource inImage){
        ImageResource ir = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel p : ir.pixels()){
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            int red = inPixel.getRed();
            int green = inPixel.getGreen();
            int blue = inPixel.getBlue();
            int average = (red + green + blue) / 3;
            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        return ir;
    }
}
