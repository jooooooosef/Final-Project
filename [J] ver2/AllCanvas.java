import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class AllCanvas extends JComponent implements Runnable{
    // Dimensions, FPS 
    public final int screenWidth;
    public final int screenHeight;
    private final int FPS = 60;

    // KEYLISTENER
    private KeyHandler key = new KeyHandler();

    // THREAD
    private Thread gameThread;

    //Buffered images
    BufferedImage titleScreen;
    BufferedImage orsemIntro;
    BufferedImage horacio;
    BufferedImage ctcsom;
    BufferedImage leong;

    // ORSEM FIELDS
    private Rectangle2D.Double background;
    private Rectangle2D.Double display;
    private Rectangle2D.Double displayText;
    private Rectangle2D.Double tntName;
    private ArrayList<dialogueText> dialogue;
    private ArrayList<BufferedImage> images;
    private int picIndex; 
    private int dialogueIndex; 
    private String rachel;

    //CANVAS FIELDS
    private int sceneIndex; 

    public AllCanvas(int w, int h){
        screenWidth = w;
        screenHeight = h;
        this.setPreferredSize(new Dimension(w, h));

        background = new Rectangle2D.Double(0, 0, screenWidth, screenHeight);
        display = new Rectangle2D.Double(50, 50, 860, 350);
        displayText = new Rectangle2D.Double(80, 435, 800, 250);
        tntName = new Rectangle2D.Double(90, 425, 150, 40);
        rachel = "?????";

        this.addKeyListener(key);
        this.setFocusable(true);

        images = new ArrayList<BufferedImage>();
        addImages();

        dialogue = new ArrayList<dialogueText>();
        addDialogue();

        picIndex = 0;
        sceneIndex = 0;
        dialogueIndex = 0;

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    public void run(){
        double drawInterval = 1000000000/FPS;;

        /**if(sceneIndex == 0){
            drawInterval = 1000000000/FPS;
        }
        else if(sceneIndex == 1){
            drawInterval = 1000000000/FPS;
        }

        //put the rest later
        **/

        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread!= null){
            if(sceneIndex == 0){
                gameStart();
            }
            else if(sceneIndex == 1){
                changeDialogue();
            }
            //put the rest later

            this.repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override 
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);
        
        if(sceneIndex == 0){
            g2d.drawImage(titleScreen,0, 0, this); 
        }
        else if(sceneIndex == 1){
            g2d.setColor(Color.decode("#abdbe3"));
            g2d.fill(background);
            g2d.setColor(Color.decode("#eab676"));
            g2d.fill(display);
            g2d.fill(displayText);
            g2d.setColor(Color.decode("#154c79"));
            g2d.fill(tntName);
            g2d.draw(background);
            g2d.draw(display);
            g2d.draw(displayText);
            g2d.draw(tntName);
            g2d.drawImage(images.get(picIndex),96, 60, this);
            dialogue.get(dialogueIndex).draw(g2d);
            g2d.setPaint(Color.WHITE);
            g2d.drawString(rachel, 106, 458);
        }
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
            titleScreen = ImageIO.read(getClass().getResourceAsStream("/GOODDAY.png"));
            orsemIntro = ImageIO.read(getClass().getResourceAsStream("/orsem.jpg"));
            horacio = ImageIO.read(getClass().getResourceAsStream("/dlcosta.jfif"));
            ctcsom = ImageIO.read(getClass().getResourceAsStream("/ctcsom.jpg"));
            leong = ImageIO.read(getClass().getResourceAsStream("/leong.jpg"));
            images.add(orsemIntro);
            images.add(ctcsom);
            images.add(horacio);
            images.add(leong);
        } catch (IOException e){
            System.out.println("Image file(s) not found in getTitleScreen function in TitleScreen class.");
        }
    }

    public void gameStart(){
        if(key.spacePressed){
            sceneIndex++;
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void changeDialogue(){
        if(key.spacePressed){
            if(dialogueIndex < dialogue.size()-1){
                dialogueIndex += 1;
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
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