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
    private int width, height;
    private int speed;
    private int tileSize;
    public String direction;
    private BufferedImage downImg, upImg, leftImg, rightImg;
    private BufferedImage image;
    private KeyHandler keyH;
    private KeyBindings keyB;
    private int currentMap;
    private CoinCollisionChecker ccc = new CoinCollisionChecker(this);

    boolean collisionUp, collisionDown, collisionRight, collisionLeft = false;
    GameCanvas gameCanvas;

    public Player(int ID, int xCoordinate, int yCoordinate, int tileSize, int speed, KeyHandler keyH, KeyBindings keyB, GameCanvas gameCanvas){
        this.ID = ID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.speed = speed;
        this.keyH = keyH;
        this.keyB = keyB;
        this.gameCanvas = gameCanvas;
        currentMap = 0;
        this.tileSize = tileSize;
        this.width = tileSize;
        this.height = tileSize;

        direction = "down";
        getPlayerImage(ID);
        image = null;

    }

    public void getPlayerImage(int ID){
        if (ID == 0){
            try {
                downImg = ImageIO.read(getClass().getResourceAsStream("images/p1-0.png"));
                upImg = ImageIO.read(getClass().getResourceAsStream("images/p1-1.png"));
                leftImg = ImageIO.read(getClass().getResourceAsStream("images/p1-2.png"));
                rightImg = ImageIO.read(getClass().getResourceAsStream("images/p1-3.png"));
            } catch (IOException e){
                System.out.println("Image file(s) not found in getPlayerImage function in Player class.");
            }
        }

        if (ID == 1){
            try {
                downImg = ImageIO.read(getClass().getResourceAsStream("images/p2-0.png"));
                upImg = ImageIO.read(getClass().getResourceAsStream("images/p2-1.png"));
                leftImg = ImageIO.read(getClass().getResourceAsStream("images/p2-2.png"));
                rightImg = ImageIO.read(getClass().getResourceAsStream("images/p2-3.png"));
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

        g2d.drawImage(image, xCoordinate, yCoordinate, width, height, null);
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

    public void moveRunningGame(int speed) {
        if (keyB.spaceKeyPressed == true){
            xCoordinate += speed;
            keyB.spaceKeyPressed = false;
        }
    }

    public void moveCoinGame(int speed){
        // PLAYER WILL MOVE IF COLLISION IS FALSE
        ccc.checkPlace();

        if (keyH.upPressed && !collisionUp){
            yCoordinate -= speed;
            direction = "up";
        }  
        if (keyH.downPressed && !collisionDown){
            yCoordinate += speed;
            direction = "down";
        }  
        if (keyH.leftPressed && !collisionLeft){
            xCoordinate -= speed;
            direction = "left";
        }  
        if (keyH.rightPressed && !collisionRight){
            xCoordinate += speed;
            direction = "right";
        }

        // RESET COLLISION VALUES   
        collisionUp = false;
        collisionDown = false;
        collisionLeft = false;
        collisionRight = false;
    }

    public void changeYLocation(int y){
        this.yCoordinate = y;
    }

    public void changeXLocation(int x){
        this.xCoordinate = x;
    }

    public void setLocation(int x, int y){
        xCoordinate = x;
        yCoordinate = y;
    }

    public void changeCurrentMap(int i){
        currentMap = i;
    }

    public void changePlayerSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void changePlayerSizeToOriginal(){
        width = tileSize;
        height = tileSize;
    }

    // ACCESSOR METHODS
    public int getXCoordinate() { return (this.xCoordinate); }
    public int getYCoordinate() { return (this.yCoordinate); }
    public int getCurrentMap() { return currentMap; }
}
