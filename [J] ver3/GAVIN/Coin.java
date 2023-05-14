/* 
 * The clock should probably have an internal timer, and a position (implement the Random somewhere else probably)
 */

 import java.awt.*;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.*;
 import java.io.IOException;
 import java.util.concurrent.ThreadLocalRandom;

 
 public class Coin implements DrawingObject{
 
     private int xCoordinate, yCoordinate, size;
     private int time;
     private BufferedImage blueCoin, grayCoin, image;
     private String status;
     AllCanvas allCanvas;
     Timer coinTimer;
 
     public Coin(AllCanvas ac){
        allCanvas = ac;
        xCoordinate = ThreadLocalRandom.current().nextInt(0, ac.giveScreenWidth() - 95);
        yCoordinate = ThreadLocalRandom.current().nextInt(228, (ac.giveScreenHeight() - 128 - 95));
        size = 96;
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
             blueCoin = ImageIO.read(getClass().getResourceAsStream("/bCoin.png"));
             grayCoin = ImageIO.read(getClass().getResourceAsStream("/gCoin.png"));
         } catch (IOException e){
             System.out.println("Image file(s) not found in getCoinImage function in Coin class.");
         }
     }
 
     @Override
     public void draw(Graphics2D g2d){
        g2d.drawImage(image, xCoordinate, yCoordinate, size, size, null);
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
        return!( this.xCoordinate + this.size <= p.getXCoordinate() ||
                 this.xCoordinate >= p.getXCoordinate() + 100 ||
                 this.yCoordinate + this.size <= p.getYCoordinate() || 
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
 