import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Timer;

import javax.imageio.ImageIO;

public class RunningGame implements DrawingObject, ActionListener{

    // FIELDS FOR OVERALL MECHANICS OF THE GAME
    private int startingPos = 1;
    private int finishPos = 750;
    private Timer timer;
    private int seconds = 0;
    private int timeLeft = 10;
    private int flowIndex = 0;
    private int currentRound = 1;

    // FIELDS FOR GRAPHICS
    private BufferedImage background;
    private BufferedImage flag;
    private BufferedImage ctc;
    private BufferedImage bel;
    private BufferedImage road;
    private Rectangle2D.Double displayText = new Rectangle2D.Double(80, 435, 800, 250);
    private Rectangle2D.Double tntName = new Rectangle2D.Double(90, 425, 150, 40);
    private dialogueText instruction = new dialogueText("Welcome to CTC-to-Bel Running Game!", "Your goal is to arrive at Bellarmine before ", "the timer runs out. Press enter when", "you're ready. Good luck!");
    private dialogueText levelOneComplete = new dialogueText("Phew! That was fast!", "Are you ready for Round 2?", "Press enter when you are.", "Good luck!");
    private dialogueText levelTwoComplete = new dialogueText("Way to go!", "Things are going to get tougher at this point.", "Are you ready for Round 3?", "Press enter when you are!");
    private dialogueText levelThreeComplete = new dialogueText("Hey, Usain! You better watch out!", "The battle's not over yet, though.", "Good luck on the fourth and last round!", "Press enter when you're ready to run!");
    private dialogueText gameComplete = new dialogueText("Slay the house boots down Houston", "I'm deceased! You really killed it out there!", "You accomplished the CTC-BEL game!", "Congrats! Press enter to return to campus.");
    private dialogueText gameLost = new dialogueText("Oh no! The Bellarmine building is", "really that far, huh? Practice", "more running, Atenista!", "Press enter to return to campus.");

    // FIELDS
    private Player player;
    private GameCanvas gc;
    
    public RunningGame(Player player, GameCanvas gc){
        this.player = player;
        this.gc = gc;


        // GET IMAGE URLS
        try{
            background = ImageIO.read(getClass().getResourceAsStream("images/ctc-bg.png"));
            flag = ImageIO.read(getClass().getResourceAsStream("images/finish32.png"));
            ctc = ImageIO.read(getClass().getResourceAsStream("images/ctcsom63.png"));
            bel = ImageIO.read(getClass().getResourceAsStream("images/leong73.png"));
            road = ImageIO.read(getClass().getResourceAsStream("images/bridge32.png"));
        } catch (IOException ex) {
            System.out.println("Image file(s) not found");
        }

        timer = new Timer(1000,this);
        timer.start();
    }

    @Override 
    public void draw(Graphics2D g2d){
        Rectangle2D.Double bg0 = new Rectangle2D.Double(0,500,960,50);
            g2d.drawImage(background, 0, 144, 960, 576, null);

            if (flowIndex == 0) { // INSTRUCTION
                messageDisplay(g2d, instruction);
            } else if (flowIndex == 1){ // ROUND 1
                startTheRound(g2d, 15);
            } else if (flowIndex == 2) { // LEVEL 1 ACCOMPLISED
                messageDisplay(g2d, levelOneComplete);
            } else if (flowIndex == 3) { // LEVEL 2
                startTheRound(g2d, 12);
            } else if (flowIndex == 4) { // LEVEL 2 ACCOMPLISED
                messageDisplay(g2d, levelTwoComplete);
            } else if (flowIndex == 5) { // LEVEL 3
                startTheRound(g2d, 10);
            } else if (flowIndex == 6) { // LEVEL 3 ACCOMPLISED
                messageDisplay(g2d, levelThreeComplete);
            } else if (flowIndex == 7) { // LEVEL 4
                startTheRound(g2d, 9);
            } else if (flowIndex == 8) { // GAME ACCOMPLISED
                messageDisplay(g2d, gameComplete);
            } else { // GAME LOST
                messageDisplay(g2d, gameLost);
            }
    }

    public void startTheRound(Graphics2D g2d, int speed){
        Rectangle2D.Double bg0 = new Rectangle2D.Double(0,500,960,50);
        g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 50));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Timer:", 50, 90);
        g2d.drawString(Integer.toString(timeLeft), 220, 90);

        String sCurrentRound = Integer.toString(currentRound);
        String strCurrentRound = "Round " + sCurrentRound;
        g2d.drawString(strCurrentRound, 680,90);

        g2d.setColor(Color.decode("#F2EBBF"));
        g2d.fill(bg0);

        // ROAD DRAWING
        for (int i = 0; i <= 960; i += 35){
            g2d.drawImage(road, i, 480, null);
        }

        // CONVERSION OF WIDTH AND HEIGHT VALUES TO INT
        double dblCTCWidth = ctc.getWidth()*1.4;
        double dblCTCHeight = ctc.getHeight()*1.4;
        int intCTCWidth = (int) dblCTCWidth;
        int intCTCHeight = (int) dblCTCHeight;
        double dblBELWidth = bel.getWidth()*1.4;
        double dblBELHeight = bel.getHeight()*1.4;
        int intBELWidth = (int) dblBELWidth;
        int intBELHeight = (int) dblBELHeight;
        double dblFLAGWidth = flag.getWidth()*2;
        double dblFLAGHeight = flag.getHeight()*2;
        int intFLAGWidth = (int) dblFLAGWidth;
        int intFLAGHeight = (int) dblFLAGHeight;
        
        g2d.drawImage(ctc, 50, 400, intCTCWidth, intCTCHeight, null);
        g2d.drawImage(bel, 600, 400, intBELWidth, intBELHeight, null);
        g2d.drawImage(flag, finishPos-20, 460, intFLAGWidth, intFLAGHeight, null);
        
        player.moveRunningGame(speed);
        player.draw(g2d);

        if (timeLeft == 0 && player.getXCoordinate() >= finishPos){
            flowIndex++;
            currentRound++;
            timeLeft = 10;
        }
        if (timeLeft == 0 && player.getXCoordinate() <= finishPos)
            flowIndex = -1; 
        
    }

    public void messageDisplay(Graphics2D g2d, dialogueText dt){
        Rectangle2D.Double bg0 = new Rectangle2D.Double(0,500,960,50);
        g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 50));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Timer: 10", 50, 90);

        String sCurrentRound = Integer.toString(currentRound);
        String strCurrentRound = "Round " + sCurrentRound;
        g2d.drawString(strCurrentRound, 680,90);

        g2d.setColor(Color.decode("#eab676"));
        g2d.fill(displayText);
        g2d.draw(displayText);

        g2d.setColor(Color.decode("#154c79"));
        g2d.fill(tntName);
        g2d.draw(tntName);

        g2d.setPaint(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 34));
        g2d.drawString("Rachel", 106, 458);
        dt.draw(g2d);

        changePlayerSettings();
        if (dt == gameComplete || dt == gameLost){
            if (gc.getKeyHandlers().enterPressed == true){
                currentRound = 1;
                player.changePlayerSizeToOriginal();
                gc.playingRunningGame(false);
            }
        }   
        
        if (gc.getKeyHandlers().enterPressed == true){
            flowIndex++;
            timeLeft = 10;
        } 
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        seconds++;
        timeLeft -= 1;
    }

    public void changePlayerSettings(){
        player.setLocation(75, 450);
        player.changePlayerSize(80,80);
    }


    // ACCESSOR METHODS
    public int getGameIndex() { return flowIndex; }

}
