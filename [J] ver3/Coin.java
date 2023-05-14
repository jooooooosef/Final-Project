 import java.awt.*;
 import java.awt.event.*;
 import java.awt.image.BufferedImage;
 import java.io.*;
 import javax.imageio.ImageIO;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;
 import java.io.IOException;
 import java.util.Random;
 import java.util.concurrent.ThreadLocalRandom;

 
 public class Coin implements DrawingObject{
 
     private int xCoordinate, yCoordinate, width, height;
     private int time;
     private BufferedImage blueCoin, grayCoin, image;
     private String status;
     private GameCanvas gc;
     boolean collisionRight, collisionLeft = false;
     Timer coinTimer;
 
     public Coin(GameCanvas gc){
        this.gc = gc;
        xCoordinate = ThreadLocalRandom.current().nextInt(0, gc.screenWidth - 95);
        yCoordinate = ThreadLocalRandom.current().nextInt(228, (gc.screenHeight - 128 - 95));
        width = 96;
        height = 96;
        time = 3;
        status = "full";

        getCoinImage();
        image = blueCoin;
         
         coinTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                time--;
                if(time == 1){
                    image = grayCoin;
                    status = "half";
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
             blueCoin = ImageIO.read(getClass().getResourceAsStream("images/bCoin.png"));
             grayCoin = ImageIO.read(getClass().getResourceAsStream("images/gCoin.png"));
         } catch (IOException e){
             System.out.println("Image file(s) not found in getCoinImage function in Player class.");
         }
     }
 
     @Override
     public void draw(Graphics2D g2d){
        g2d.drawImage(image, xCoordinate, yCoordinate, width, height, null);
     }
     
    public int getXCoordinate(){
        return (this.xCoordinate);
    }

    public int getYCoordinate(){
        return (this.yCoordinate);
    }

    public boolean isStillACoin(){
        return(this.image != null);
    }
    
    public boolean collisionCheck(Player p){
        return!( this.xCoordinate + this.width <= p.getXCoordinate() ||
                 this.xCoordinate >= p.getXCoordinate() + 100 ||
                 this.yCoordinate + this.height <= p.getYCoordinate() || 
                 this.yCoordinate >= p.getYCoordinate() + 100 );
    }

    public void playerCollided(){
        this.image = null;
    }

    public double addScore(String s){
        if(s.equals("full")){
            return 1;
        }
        else{
            return 0.5;
        }
    }

    public String getStatus(){
        return status;
    }
 }
 