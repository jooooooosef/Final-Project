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
    //private StatusBar statusBar;
    private BufferedImage background, floorDown, floorUp;
    CoinCollisionChecker ccc;

    public CoinScreen(int width, int height, Player player){
        this.width = width;
        this.height = height;
        //statusBar = new StatusBar(width,100);
        ccc = new CoinCollisionChecker(player);
        getFloorTile();
        getBackground();
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(background, 0, 0, 960, 720, null);
        //statusBar.draw(g2d);
        for(int i = 0; i < 8; i++){
            g2d.drawImage(floorUp, 0-32+i*128, 100, 128, 128, null);
            g2d.drawImage(floorDown, 0-32+i*128, 592, 128, 128, null);
        }
    }

    public void getFloorTile(){
        try {
            floorDown = ImageIO.read(getClass().getResourceAsStream("images/floor128.png"));
            floorUp = ImageIO.read(getClass().getResourceAsStream("images/floor128U.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getFloorTile function in CoinScreen class.");
        }
    }

    public void getBackground(){
        try {
            background = ImageIO.read(getClass().getResourceAsStream("images/coin-bg.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getBackground function in CoinScreen class.");
        }
    }
}
