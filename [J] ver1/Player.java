/* 
 * https://www.youtube.com/watch?v=M-F7z1xWS6o
 * 
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class Player implements DrawingObject{

    private int xCoordinate;
    private int yCoordinate;
    private double scale;
    private BufferedImage sprite;
    private int imageWidth;
    private int imageHeight;

    public Player(int xCoordinate, int yCoordinate, double scale){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        try{
            sprite = ImageIO.read(getClass().getResourceAsStream("/p1-0.png"));
        } catch(IOException e){
            System.out.println("Sprite image not found in Player class.");
        }

        this.scale = scale;
        this.imageWidth = (int) (sprite.getWidth()*scale);
        this.imageHeight = (int) (sprite.getHeight()*scale);
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(sprite, xCoordinate, yCoordinate, imageWidth, imageHeight, null);
    }

    public void moveDown(int y){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/p1-0.png"));
        } catch(IOException e){
            System.out.println("Image file not found in moveDown function in Player class.");
        }
        yCoordinate += y;
    }

    public void moveUp(int y){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/p1-1.png"));
        } catch(IOException e){
            System.out.println("Image file not found in moveUp function in Player class.");
        }
        yCoordinate -= y;
    }

    public void moveLeft(int x){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/p1-2.png"));
        } catch(IOException e){
            System.out.println("Image file not found in moveLeft function in Player class.");
        }
        xCoordinate -= x;
    }

    public void moveRight(int x){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/p1-3.png"));
        } catch(IOException e){
            System.out.println("Image file not found in moveRight function in Player class.");
        }
        xCoordinate += x;
    }

    public boolean isMovingToMap0(){
        return (this.xCoordinate > 600 &&
                this.yCoordinate < 100 );
    }

    public boolean isMovingToMap1(){
        return (this.xCoordinate > 600 &&
                this.yCoordinate > 450);
    }

    public void changeLocation(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public int getXCoordinate(){
        return (this.xCoordinate);
    }

    public int getYCoordinate(){
        return (this.yCoordinate);
    }

    public int getWidth(){
        return (imageWidth);
    }

    public int getHeight(){
        return (imageHeight);
    }


    public boolean isAllowedToMoveRight(GameCanvas gc, ArrayList<MapElement> mapElements){
        boolean result = false;
        for (MapElement me : mapElements){
            
            if ((this.yCoordinate > (me.getYCoordinate() + me.getHeight()))){
                    result = true;
                    break;
            }
        }
        
        if(this.xCoordinate + this.imageWidth < gc.getWidth() && result){
            return true;
        } else{
            return false;
        }
    }

   
    
}
