import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.util.ArrayList;

public class Orsem implements DrawingObject {
    BufferedImage orsemIntro, horacio, ctcsom, leong;

    private Rectangle2D.Double background, display, displayText,tntName;
    private ArrayList<dialogueText> dialogue;
    private ArrayList<BufferedImage> images;
    private int picIndex, dialogueIndex, screenWidth, screenHeight; 
    private String rachel;

    private KeyHandler key;
    private Thread gameThread;
    private AllCanvas allCanvas;

    public Orsem(AllCanvas ac){
        allCanvas = ac;
        key = ac.giveKeyHandler();
        gameThread = ac.giveThread();
        screenWidth = ac.giveScreenWidth();
        screenHeight = ac.giveScreenHeight();

        images = new ArrayList<BufferedImage>();
        addImages();

        dialogue = new ArrayList<dialogueText>();
        addDialogue();

        background = new Rectangle2D.Double(0, 0, screenWidth, screenHeight);
        display = new Rectangle2D.Double(50, 50, 860, 350);
        displayText = new Rectangle2D.Double(80, 435, 800, 250);
        tntName = new Rectangle2D.Double(90, 425, 150, 40);
        
        picIndex = 0;
        dialogueIndex = 0;
        rachel = "?????";
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.decode("#abdbe3"));
        g2d.fill(background);
        g2d.draw(background);

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
        dialogue.add(new dialogueText("Welcome to the Ateneo!", "We're so excited to finally have you here,", "and we hope you enjoy your stay.", ""));
        dialogue.add(new dialogueText("I'll be your OrSem TNT for today.", "I'll walk you through some buildings,", "and go a little bit into what they're for.", "Name's Rachel, by the way!"));
        dialogue.add(new dialogueText("Let's go up the skywalk first.", "", "", ""));
        dialogue.add(new dialogueText("Over here are the CTC and SOM buildings!", "They're both connected by a bridge,", "making it easy to go back and forth", "between the two."));
        dialogue.add(new dialogueText("Next, here's the Horacio de la Costa Building!", "Called 'Horacio' for short, this is", "where students tend to practice for oral exams,", "right in front of the statue of Saint De La Costa!"));
        dialogue.add(new dialogueText("For our last stop for today,", "this is Leong Hall!", "Events usually take place either in the", "auditorium or in the roofdeck!"));
        dialogue.add(new dialogueText("That's all the time we have for today.", "We wish you all the best in your classes", "and know you'll do great!", "Kaya mo yan, Atenista!"));
    }

    public void addImages(){
        try {
            orsemIntro = ImageIO.read(getClass().getResourceAsStream("/orsem.jpg"));
            horacio = ImageIO.read(getClass().getResourceAsStream("/dlcosta.jfif"));
            ctcsom = ImageIO.read(getClass().getResourceAsStream("/ctcsom.jpg"));
            leong = ImageIO.read(getClass().getResourceAsStream("/leong.jpg"));
            images.add(orsemIntro);
            images.add(ctcsom);
            images.add(horacio);
            images.add(leong);
        } catch (IOException e){
            System.out.println("Image file(s) not found in addImages function in Orsem class.");
        }
    }

    public void changeDialogue(){
        if(key.spacePressed){
            if(dialogueIndex < 6){
                dialogueIndex ++;
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            else if(dialogueIndex == 6){
                allCanvas.updateSceneIndex();
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                dialogueIndex = 0;
                picIndex = 0;
                rachel = "??????";
            }
        }
        else if(dialogueIndex == 2){
            rachel = "Rachel";
        }
        else if(dialogueIndex == 3){
            picIndex = 1;
        }
        else if(dialogueIndex == 4){
            picIndex = 2; 
        }
        else if(dialogueIndex == 5){
            picIndex = 3;
        }
        else if(dialogueIndex == 6){
            picIndex = 0;
        }
    }
    
}
