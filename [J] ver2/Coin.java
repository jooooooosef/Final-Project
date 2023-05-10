/* 
 * The clock should probably have an internal timer, and a position (implement the Random somewhere else probably)
 */

 import java.awt.*;
 import java.awt.event.*;
 import java.awt.image.BufferedImage;
 import java.io.*;
 import javax.imageio.ImageIO;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;
 import java.io.IOException;

 
 public class Coin implements DrawingObject{
 
     private int xCoordinate;
     private int time;
     private BufferedImage blueCoin, grayCoin, image;
     boolean collisionRight, collisionLeft = false;
     Timer coinTimer;
 
     public Coin(int xCoordinate){
         this.xCoordinate = xCoordinate;
         getCoinImage();
         image = blueCoin;
         time = 6000;
         coinTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                time--;
                if(time == 2000){
                    image = grayCoin;
                }
                else if(time == 0){
                    image = null;
                    coinTimer.stop();
                }
            }
          });
          coinTimer.start();
     }
 
     public void getCoinImage(){
         try {
             blueCoin = ImageIO.read(getClass().getResourceAsStream("/bCoin.png"));
             grayCoin = ImageIO.read(getClass().getResourceAsStream("/gCoin.png"));
         } catch (IOException e){
             System.out.println("Image file(s) not found in getCoinImage function in Player class.");
         }
     }
 
     @Override
     public void draw(Graphics2D g2d){
        g2d.drawImage(image, xCoordinate, 464, 128, 128, null);
     }
 }
 