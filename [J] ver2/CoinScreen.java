import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class CoinScreen implements DrawingObject{
    
    private int width;
    private int height;
    //private Player player1;
    private StatusBar statusBar;
    private BufferedImage background, floor;

    public CoinScreen(int width, int height){
        this.width = width;
        this.height = height;
        //this.player1 = player1;
        statusBar = new StatusBar(width,100);
        getFloorTile();
        getBackground();
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(background, 0, 0, 960, 720, null);
        statusBar.draw(g2d);
        for(int i = 0; i < 8; i++){
            g2d.drawImage(floor, 0-32+i*128, 592, 128, 128, null);
        }
    }

    public void getFloorTile(){
        try {
            floor = ImageIO.read(getClass().getResourceAsStream("/floor128.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getFloorTile function in CoinScreen class.");
        }
    }

    public void getBackground(){
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/coin-bg.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getBackground function in CoinScreen class.");
        }
    }

    
}
