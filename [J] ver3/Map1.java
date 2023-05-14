import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Map1 implements DrawingObject{
    
    private BufferedImage ctc;
    private BufferedImage som;

    public Map1(){
        try{
        ctc = ImageIO.read(getClass().getResourceAsStream("images/ctcsom63.png"));
        som = ImageIO.read(getClass().getResourceAsStream("images/ctcsom63.png"));
        } catch (IOException ex) {
            System.out.println("Image file(s) not found in constructor of Map0");
        }
    }

    @Override 
    public void draw(Graphics2D g2d){
        // values were hard-coded

        double doubleSomWidth = som.getWidth()*1.94;
        int intSomWidth = (int) doubleSomWidth;
        double doubleSomHeight = som.getHeight()*1.69;
        int intSomHeight = (int) doubleSomHeight;
        

        g2d.drawImage(som, 18, 528, intSomWidth, intSomHeight, null);
        g2d.drawImage(ctc, 402, 528, intSomWidth, intSomHeight, null);
       
    }
}
