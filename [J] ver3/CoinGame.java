import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

public class CoinGame implements DrawingObject {
    private KeyHandler keyH;
    private KeyBindings keyB;
    private Thread gameThread;
    private int screenWidth, screenHeight, time, coinIndex, gameFlowIndex;
    private double score;
    private Boolean spawnAgain = false;
    ArrayList<Coin> coins;
    private GameCanvas gc;
    CoinScreen coinScreen;
    Player player;
    Timer coinGameTimer;
    dialogueText start;
    Rectangle2D.Double displayText, tntName;

    public CoinGame(Player player, GameCanvas gc){
        this.player = player;
        this.gc = gc;
        keyH = gc.getKeyHandlers();
        keyB = gc.getKeyBindings();
        gameThread = gc.getGameThread();
        screenWidth = gc.screenWidth;
        screenHeight = gc.screenHeight;

        coinScreen = new CoinScreen(screenWidth, screenHeight, player);
        coins = new ArrayList<Coin>();
        coins.add(new Coin(gc));

        displayText = new Rectangle2D.Double(80, 435, 800, 250);
        tntName = new Rectangle2D.Double(90, 425, 150, 40);
        start = new dialogueText("Welcome to SOM's Coin Collecting course!", "The goal here is to collect at least 60 coins.", "Blue coins count as a whole coin,", "and gray coins count as half a coin. Good luck!");
        
        coinIndex = 0;
        gameFlowIndex = 0;
        score = 0;
        time = 60;

        coinGameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                time--;
                if(time == 0){
                    gameFlowIndex = 2;
                    coinGameTimer.stop();
                }
            }
          });
    }

    public void draw(Graphics2D g2d){
        coinScreen.draw(g2d);
        g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 50));
        g2d.setPaint(Color.decode("#d6dacf"));
        g2d.drawString("TIME: " + Integer.toString(time), 100, 70);
        g2d.drawString("SCORE: " + Double.toString(score), 600, 70);

        if(gameFlowIndex == 0){
            g2d.setColor(Color.decode("#eab676"));
            g2d.fill(displayText);
            g2d.draw(displayText);
    
            g2d.setColor(Color.decode("#154c79"));
            g2d.fill(tntName);
            g2d.draw(tntName);

            g2d.setPaint(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 34));
            g2d.drawString("Rachel", 106, 458);
            start.draw(g2d);
        }

        else if(gameFlowIndex == 1){
            coins.get(coinIndex).draw(g2d);
            player.draw(g2d);
        }

        else if(gameFlowIndex == 2){
            g2d.setColor(Color.decode("#eab676"));
            g2d.fill(displayText);
            g2d.draw(displayText);
    
            g2d.setColor(Color.decode("#154c79"));
            g2d.fill(tntName);
            g2d.draw(tntName);

            g2d.setPaint(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 34));
            g2d.drawString("Rachel", 106, 458);
            results(score).draw(g2d);
        }
    }

    public void CoinCollision(){
        player.moveCoinGame(5);

        Coin current = coins.get(coinIndex);

        if(current.collisionCheck(player) && current.isStillACoin()){
            coins.add(new Coin(gc));
            score += current.addScore(current.getStatus());
            coinIndex++;
        }

        if(current.isStillACoin() == false){
            coins.add(new Coin(gc));
            coinIndex++;
        }
    }

    public dialogueText results(double d){
        dialogueText result = null;

        if(d >= 60){
            result = new dialogueText("You collected a total of " + Double.toString(score) + " coins.", "Congratulations! You win!", "Press enter to return to campus.", "");
            gc.getGameDoneArray()[3] = true;
        }
        else{
            result = new dialogueText("You collected a total of only " + Double.toString(score) + " coins.", "Better luck next time!", "Press enter to return to campus.", "");
        }
        player.setLocation(192,480);
        return result;
    }

    public int giveGameIndex(){
        return gameFlowIndex;
    }

    public void gameStart(){
        if(keyH.enterPressed){
            gameFlowIndex = 1;
            coinGameTimer.start();
            player.setLocation(0,480);
            player.changePlayerSize(100,100);
        }
    }

    public void gameEnd(){
        if(keyH.enterPressed){
            gc.playingCoinGame(false);
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            gameFlowIndex = 0;
            time = 60;
            player.changePlayerSizeToOriginal();
            score = 0;
        }
    }
}
