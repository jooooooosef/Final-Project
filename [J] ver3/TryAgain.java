import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.util.ArrayList;

public class TryAgain implements DrawingObject {
    BufferedImage tryagain;

    private Rectangle2D.Double background, display, displayText,tntName;
    private dialogueText dialogue;
    private ArrayList<ArrayList<Rectangle2D.Double>> chequered;
    private int screenWidth, screenHeight; 
    private String rachel;
    private KeyBindings keyB;
    private Thread gameThread;
    private GameCanvas gc;

    public TryAgain(GameCanvas gc, int min){
        this.gc = gc;
        keyB = gc.getKeyBindings();
        gameThread = gc.getGameThread();
        screenWidth = gc.screenWidth;
        screenHeight = gc.screenHeight;
        addImage();

        dialogue = new dialogueText("Oh no! The timer ran out!", "After not finishing the games on time,", "you won't be able to graduate.", "Try again, Atenista!");

        background = new Rectangle2D.Double(0, 0, screenWidth, screenHeight);
        chequered = new ArrayList<ArrayList<Rectangle2D.Double>>();
        addSquares();
        display = new Rectangle2D.Double(50, 50, 860, 350);
        displayText = new Rectangle2D.Double(80, 435, 800, 250);
        tntName = new Rectangle2D.Double(90, 425, 150, 40);
        rachel = "Rachel";
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.decode("#abdbe3"));
        g2d.fill(background);
        g2d.draw(background);

        g2d.setColor(Color.decode("#a4b8c5"));
        for(ArrayList<Rectangle2D.Double> f : chequered){
            for(Rectangle2D.Double g : f){
                g2d.fill(g);
                g2d.draw(g);
            }
        }

        g2d.setColor(Color.decode("#eab676"));
        g2d.fill(display);
        g2d.fill(displayText);
        g2d.draw(display);
        g2d.draw(displayText);

        g2d.setColor(Color.decode("#154c79"));
        g2d.fill(tntName);
        g2d.draw(tntName);

        g2d.drawImage(tryagain,96, 60, null);
        dialogue.draw(g2d);

        g2d.setPaint(Color.WHITE);
        g2d.drawString(rachel, 106, 458);
    }

    public void addImage(){
        try {
            tryagain = ImageIO.read(getClass().getResourceAsStream("images/tryagain.jpg"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in addImage function in TryAgain class.");
        }
    }

    public void backToTitle(){
        if(keyB.enterKeyPressed){
                //Go back to the title screen
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
    }

    public void addSquares(){
        for(int i = 0; i < 12; i++){
           chequered.add(new ArrayList<Rectangle2D.Double>());
        }

        for(int j = 0; j < 12; j+=2){
            for(int k = 0; k < 12; k++){
                chequered.get(j).add(new Rectangle2D.Double(40+(160*k), 0+(160*(j/2)), 80, 80));
            }
            for(int l = 0; l < 12; l++){
                chequered.get(j+1).add(new Rectangle2D.Double(-40+(160*l), 80+(160*(j/2)), 80, 80));
            }
        }
    }
    
}
