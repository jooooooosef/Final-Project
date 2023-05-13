/* 
 * https://www.youtube.com/watch?v=M-F7z1xWS6o
 * 
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Player implements DrawingObject{

    private int ID;
    private int xCoordinate, yCoordinate;
    private int speed;
    private int tileSize;
    public String direction;
    private BufferedImage downImg, upImg, leftImg, rightImg;
    private BufferedImage image;
    private KeyHandler keyH;
    private int currentMap;

    boolean collisionUp, collisionDown, collisionRight, collisionLeft = false;
    GameCanvas gameCanvas;

    public Player(int ID, int xCoordinate, int yCoordinate, int tileSize, int speed, KeyHandler keyH, GameCanvas gameCanvas){
        this.ID = ID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.speed = speed;
        this.keyH = keyH;
        this.gameCanvas = gameCanvas;
        currentMap = 0;

        this.tileSize = tileSize;

        direction = "down";
        getPlayerImage(ID);
        image = null;

    }

    public void getPlayerImage(int ID){
        if (ID == 0){
            try {
                downImg = ImageIO.read(getClass().getResourceAsStream("/p1-0.png"));
                upImg = ImageIO.read(getClass().getResourceAsStream("/p1-1.png"));
                leftImg = ImageIO.read(getClass().getResourceAsStream("/p1-2.png"));
                rightImg = ImageIO.read(getClass().getResourceAsStream("/p1-3.png"));
            } catch (IOException e){
                System.out.println("Image file(s) not found in getPlayerImage function in Player class.");
            }
        }

        if (ID == 1){
            try {
                downImg = ImageIO.read(getClass().getResourceAsStream("/p2-0.png"));
                upImg = ImageIO.read(getClass().getResourceAsStream("/p2-1.png"));
                leftImg = ImageIO.read(getClass().getResourceAsStream("/p2-2.png"));
                rightImg = ImageIO.read(getClass().getResourceAsStream("/p2-3.png"));
            } catch (IOException e){
                System.out.println("Image file(s) not found in getPlayerImage function in Player class.");
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        switch(direction){
        case "down":
            image = downImg;
            break;
        case "up":
            image = upImg;
            break;
        case "left":
            image = leftImg;
            break;
        case "right":
            image = rightImg;
            break;
        }

        g2d.drawImage(image, xCoordinate, yCoordinate, tileSize, tileSize, null);
    }

    public void move(){
        // PLAYER WILL MOVE IF COLLISION IS FALSE

        gameCanvas.cChecker.checkTile(this);

        if (keyH.upPressed == true && !collisionUp){
            yCoordinate -= speed;
            direction = "up";
        }  
        if (keyH.downPressed == true && !collisionDown){
            yCoordinate += speed;
            direction = "down";
        }  
        if (keyH.leftPressed == true && !collisionLeft){
            xCoordinate -= speed;
            direction = "left";
        }  
        if (keyH.rightPressed == true && !collisionRight){
            xCoordinate += speed;
            direction = "right";
        }

        // RESET COLLISION VALUES   
        collisionUp = false;
        collisionDown = false;
        collisionLeft = false;
        collisionRight = false;
    }

    public void changeLocation(int y){
        this.yCoordinate = y;
    }

    public int getXCoordinate(){
        return (this.xCoordinate);
    }

    public int getYCoordinate(){
        return (this.yCoordinate);
    }

    public void setLocation(int x, int y){
        xCoordinate = x;
        yCoordinate = y;
    }

    public void changeCurrentMap(int i){
        currentMap = i;
    }

    public int getCurrentMap() { return currentMap; }
}
