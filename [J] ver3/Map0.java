import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Map0 implements DrawingObject{
    
    private BufferedImage leongHall;
    private BufferedImage delaCosta;

    public Map0(){
        try{
        leongHall = ImageIO.read(getClass().getResourceAsStream("images/leong73.png"));
        delaCosta = ImageIO.read(getClass().getResourceAsStream("images/horacio63.png"));
        } catch (IOException ex) {
            System.out.println("Image file(s) not found in constructor of Map0");
        }
    }

    @Override 
    public void draw(Graphics2D g2d){
        // values here were hard-coded
        double doubleLhWidth = leongHall.getWidth()*1.69;
        int intLhWidth = (int) doubleLhWidth;
        double doubleLhHeight = leongHall.getHeight()*1.65;
        int intLhHeight = (int) doubleLhHeight;

        double doubleDlWidth = delaCosta.getWidth()*2.24;
        int intDlWidth = (int) doubleDlWidth;
        double doubldDlHeight = delaCosta.getHeight()*1.69;
        int intDlHeight = (int) doubldDlHeight;

        g2d.drawImage(leongHall, 495,144, intLhWidth, intLhHeight, null);
        g2d.drawImage(delaCosta, 15, 336, intDlWidth, intDlHeight, null);
    }
}
