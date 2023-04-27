import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.lang.InterruptedException;

public class orsemCanvas extends JComponent implements Runnable {
    // SCREEN DIMENSIONS
    public final int screenWidth = 960;
    public final int screenHeight = 720;
    private final int FPS = 60;

    // KEYLISTENER
    private KeyHandler keyO = new KeyHandler();

    // THREAD
    private Thread gameThread;

    // WIP FIELDS
    private Rectangle2D.Double background;
    private Rectangle2D.Double display;
    private Rectangle2D.Double displayText;
    //private ArrayList<Text> dialogue;
    //private ArrayList<Image> images;

    public orsemCanvas(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));

        background = new Rectangle2D.Double(0, 0, screenWidth, screenHeight);
        display = new Rectangle2D.Double(50, 50, 860, 350);
        displayText = new Rectangle2D.Double(100, 435, 760, 250);

        gameThread = new Thread(this);
        gameThread.start();

        this.addKeyListener(keyO);
        this.setFocusable(true);
    }

    @Override 
    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread!= null){

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

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.decode("#abdbe3"));
        g2d.fill(background);
        g2d.setColor(Color.decode("#eab676"));
        g2d.fill(display);
        g2d.setColor(Color.decode("#eab676"));
        g2d.fill(displayText);
        g2d.draw(background);
        g2d.draw(display);
        g2d.draw(displayText);
    } 
}