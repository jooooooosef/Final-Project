import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class StatusBar implements DrawingObject{
    private int width;
    private int height;
    BufferedImage gameLogo; 
    
    
    public StatusBar(int width, int height){
        this.width = width;
        this.height = height;
        getGameLogo();
    }

    @Override
    public void draw(Graphics2D g2d){
        Rectangle2D.Double statusBarBase = new Rectangle2D.Double(0,0,width,height); 
        g2d.setColor(Color.decode("#003466"));
        g2d.fill(statusBarBase);
        g2d.drawImage(gameLogo, 390, -22, 180, 135, null);
    }

    public void getGameLogo(){
        try {
            gameLogo = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getCoinImage function in Player class.");
        }
    }
}
