import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class TitleScreen implements DrawingObject {
    BufferedImage titleScreen;
    AllCanvas allCanvas;
    private KeyHandler key;
    private Thread gameThread; 

    public TitleScreen(AllCanvas ac){
        allCanvas = ac;
        key = ac.giveKeyHandler();
        gameThread = ac.giveThread();
        getTitleScreen();
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(titleScreen,0, 0, null); 
    }

    public void getTitleScreen(){
        try {
            titleScreen = ImageIO.read(getClass().getResourceAsStream("/GOODDAY.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getTitleScreen function in TitleScreen class.");
        }
    }

    public void gameStart(){
        if(key.spacePressed){
            allCanvas.updateSceneIndex();
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
