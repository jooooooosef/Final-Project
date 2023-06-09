import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.util.ArrayList;

public class Graduation implements DrawingObject {
    BufferedImage grad1, grad2, grad3;

    private Rectangle2D.Double background, display, displayText,tntName;
    private ArrayList<dialogueText> dialogue;
    private ArrayList<BufferedImage> images;
    private ArrayList<ArrayList<Rectangle2D.Double>> chequered;
    private int picIndex, dialogueIndex, screenWidth, screenHeight; 
    private String rachel;
    private KeyBindings keyB;
    private Thread gameThread;
    private GameCanvas gc;

    public Graduation(GameCanvas gc){
        this.gc = gc;
        keyB = gc.getKeyBindings();
        gameThread = gc.getGameThread();
        screenWidth = gc.screenWidth;
        screenHeight = gc.screenHeight;

        images = new ArrayList<BufferedImage>();
        addImages();

        dialogue = new ArrayList<dialogueText>();
        addDialogue();

        background = new Rectangle2D.Double(0, 0, screenWidth, screenHeight);
        chequered = new ArrayList<ArrayList<Rectangle2D.Double>>();
        addSquares();
        display = new Rectangle2D.Double(50, 50, 860, 350);
        displayText = new Rectangle2D.Double(80, 435, 800, 250);
        tntName = new Rectangle2D.Double(90, 425, 150, 40);
        
        picIndex = 0;
        dialogueIndex = 0;
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

        g2d.drawImage(images.get(picIndex),96, 60, null);
        dialogue.get(dialogueIndex).draw(g2d);

        g2d.setPaint(Color.WHITE);
        g2d.drawString(rachel, 106, 458);
    }

    public void addDialogue(){
        dialogue.add(new dialogueText("Congrats, Atenista! You finally reached the end!", "After finishing all four school years,", "you're finally graduating!", ""));
        
        int checkMinutes = gc.getMinutes();
        
        if(checkMinutes > 8){
            dialogue.add(new dialogueText("With more than 8 minutes left on the clock, ", "you'll be graduating with the honor of", "Summa Cum Laude! That's amazing!", ""));
        }
        else if(checkMinutes > 6){
            dialogue.add(new dialogueText("With more than 6 minutes left on the clock, ", "you'll be graduating with the honor of", "Magna Cum Laude! That's really good!", ""));
        }
        else if(checkMinutes > 4){
            dialogue.add(new dialogueText("With more than 4 minutes left on the clock, ", "you'll be graduating with the honor of", "Cum Laude! Nicely done!", ""));
        }
        else if(checkMinutes > 2){
            dialogue.add(new dialogueText("With more than 2 minutes left on the clock, ", "you'll be graduating with an", "Honorable Mention! Slay!", ""));
        }
        else if(checkMinutes >= 0){
            dialogue.add(new dialogueText("With less than 2 minutes left on the clock, ", "you'll finally be getting your degree! Slay!", " ", " "));
        }

        dialogue.add(new dialogueText("We hope you enjoyed your time", "here in the Ateneo, and wish you", "all the best wherever you go.", "See you around, Atenista! (THE END.)"));
    }

    public void addImages(){
        try {
            grad1 = ImageIO.read(getClass().getResourceAsStream("images/grad1.jpg"));
            grad2 = ImageIO.read(getClass().getResourceAsStream("images/grad2.jfif"));
            grad3 = ImageIO.read(getClass().getResourceAsStream("images/grad3.jpg"));
            images.add(grad1);
            images.add(grad2);
            images.add(grad3);
        } catch (IOException e){
            System.out.println("Image file(s) not found in addImages function in Graduation class.");
        }
    }

    public void changeDialogue(){
        if(keyB.enterKeyPressed){
            if(dialogueIndex < dialogue.size()){
                dialogueIndex++;
                picIndex++;
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            else if(dialogueIndex == dialogue.size()-1){
                //gc.inGrad(false);
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                dialogueIndex = 0;
                picIndex = 0;
            }
            keyB.enterKeyPressed = false;
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
