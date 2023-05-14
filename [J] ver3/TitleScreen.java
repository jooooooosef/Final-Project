import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class TitleScreen implements DrawingObject {
    BufferedImage titleScreen;
    GameCanvas gc;
    private KeyBindings keyB;
    private Thread gameThread; 

    public TitleScreen(GameCanvas gc){
        this.gc = gc;
        keyB = gc.getKeyBindings();
        gameThread = gc.getGameThread();
        getTitleScreen();
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(titleScreen,0, 0, gc.screenWidth, gc.screenHeight, null); 
    }

    public void getTitleScreen(){
        try {
            titleScreen = ImageIO.read(getClass().getResourceAsStream("images/GOODDAY.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getTitleScreen function in TitleScreen class.");
        }
    }

    public void gameStart(){
        if(keyB.spaceKeyPressed){
            gc.inTitleScreen(false);
            gc.inOrsem(true);
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            keyB.spaceKeyPressed = false;
        }
    }
}
