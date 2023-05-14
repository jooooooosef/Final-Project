import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.TreeUI;
import java.lang.InterruptedException;

public class GameCanvas extends JComponent implements Runnable, ActionListener{

    // PLAYER IDENTIFICATION
    private int ID;
    private int otherID;
    
    // SCREEN DIMENSIONS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int numScreenTileCol = 20;
    public final int numScreenTileRow = 15;
    public final int screenWidth = tileSize * numScreenTileCol; // 960px
    public final int screenHeight = tileSize * numScreenTileRow; // 720px

    // SCREEN FRAME RATE
    private int FPS = 60;

    // GRAPHICS 
    private StatusBar statusBar = new StatusBar(960,144);
    private dialogueText buildingOccupiedText = new dialogueText("Oops! Teka lang, Atenista!", "This building is currently being played.", "Please wait for them to finish or try visiting", "other buildings. Press enter to go back.");
    private Rectangle2D.Double displayText = new Rectangle2D.Double(80, 435, 800, 250);
    private Rectangle2D.Double tntName = new Rectangle2D.Double(90, 425, 150, 40);
    private BufferedImage buildingOccupiedBackground;

    // KEYLISTENER
    private KeyHandler keyH = new KeyHandler();
    private KeyBindings keyB = new KeyBindings(this);

    // COLLISIONS
    public Player[] players = new Player[2];
    TileManager tm = new TileManager(this);
    CollisionChecker cChecker = new CollisionChecker(this, keyH);

    // THREAD
    private Thread gameThread;

    // MINIGAMES FIELDS BOOLEANS
    private LeongQuiz leongQuiz;
    private HoracioQuiz horacioQuiz;
    private RunningGame runningGame;
    private CoinGame coinGame;
    private boolean playingLeongQuiz = false;
    private boolean playingHoracioQuiz = false;
    private boolean playingRunningGame = false;
    private boolean playingCoinGame = false;
    private boolean otherPlayerIsPlaying0 = false;
    private boolean otherPlayerIsPlaying1 = false;
    private boolean otherPlayerIsPlaying2 = false;
    private boolean otherPlayerIsPlaying3 = false;
    private boolean buildingOccupied = false;
    private boolean[] gameDone;


    // GAMEFLOW FIELDS
    private TitleScreen titleS = new TitleScreen(this);
    public Orsem orsem = new Orsem(this);
    private boolean inTitleScreen = true;       // START
    private boolean inOrsem = false;

    // TIMER-RELATED FIELDS
    private Timer timer;
    private int seconds = 59;
    private int minutes = 9;

    // OTHER FIELDS
    private Map0 map0;
    private Map1 map1;
    private int indexOfMap;
    
    public GameCanvas(int ID){
        this.ID = ID;
        otherID = ID == 0 ? 1 : 0;

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);

        players[0] = new Player(0, 480, 360, tileSize, 10, keyH, keyB, this);
        players[1] = new Player(1, 480, 360, tileSize, 10, keyH, keyB, this);
        map0 = new Map0();
        map1 = new Map1();
        indexOfMap = 0;
        timer = new Timer(1000, this);

        gameDone = new boolean[4];
        gameDone[0] = false;
        gameDone[1] = false;
        gameDone[2] = false;
        gameDone[3] = false;

        // MINIGAMES
        runningGame = new RunningGame(players[ID], this);
        coinGame = new CoinGame(players[ID], this);
        horacioQuiz = new HoracioQuiz(this);
        leongQuiz = new LeongQuiz(this);

        gameThread = new Thread(this);
        gameThread.start();

        this.addKeyListener(keyH);
        this.setFocusable(true);

        // RETRIEVAL OF IMAGE
        try {
            buildingOccupiedBackground = ImageIO.read(getClass().getResourceAsStream("images/tekalang.png"));
        } catch (IOException ex) {
            System.out.println("Image file not found in GameCanvas constructor.");
        }
    }

    @Override 
    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

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

        statusBar.draw(g2d);

        if (inTitleScreen) {
            titleS.draw(g2d);
            titleS.gameStart();
        } else if (inOrsem) {
            orsem.draw(g2d);
            orsem.changeDialogue();
        } else if (playingRunningGame) {
            players[ID].direction = "right";
            runningGame.draw(g2d);
        } else if (playingHoracioQuiz) {
            horacioQuiz.draw(g2d);
            if(horacioQuiz.giveGameIndex() == 0){
                horacioQuiz.gameStart();
            }
            else if(horacioQuiz.giveGameIndex() == 1){
                horacioQuiz.quizGame();
            }
            else if(horacioQuiz.giveGameIndex() == 2){
                horacioQuiz.checked();
            }  
            else if(horacioQuiz.giveGameIndex() == 3){
                horacioQuiz.gameEnd();
            }  
        } else if (playingLeongQuiz) {
            leongQuiz.draw(g2d);
            if(leongQuiz.giveGameIndex() == 0){
                leongQuiz.gameStart();
            }
            else if(leongQuiz.giveGameIndex() == 1){
                leongQuiz.quizGame();
            }
            else if(leongQuiz.giveGameIndex() == 2){
                leongQuiz.checked();
            }  
            else if(leongQuiz.giveGameIndex() == 3){
                leongQuiz.gameEnd();
            }  
        } else if (playingCoinGame) {
            if(coinGame.giveGameIndex() == 0){
                coinGame.draw(g2d);
                coinGame.gameStart();
            }
            else if(coinGame.giveGameIndex() == 1){
                coinGame.draw(g2d);
                coinGame.CoinCollision();
            }
            else if(coinGame.giveGameIndex() == 2){
                coinGame.draw(g2d);
                coinGame.gameEnd();
            }  
        } else if (buildingOccupied) {
            messageDisplay(g2d, buildingOccupiedText);
            if (keyH.enterPressed == true)
                buildingOccupied = false;
        } else {
            tm.draw(g2d);
            if (indexOfMap == 0)
                map0.draw(g2d);
            else 
                map1.draw(g2d);

            // STATUS BAR
            statusBar.draw(g2d);
            g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 35));
            g2d.setColor(Color.WHITE);
            g2d.drawString("Time Left: ", 20, 90);
            g2d.drawString(Integer.toString(minutes) + ": ", 200, 90);
            if (seconds < 10)
                g2d.drawString("0" + Integer.toString(seconds), 235, 90);
            else
                g2d.drawString(Integer.toString(seconds), 235, 90);
            
            int doneBuildingsCount = 0;
            for (boolean x : gameDone){
                if (x == true)
                    doneBuildingsCount++;
            }
            g2d.drawString("Buildings: " + Integer.toString(doneBuildingsCount) + "/4", 690,90);

            players[ID].draw(g2d);
            players[ID].move();
            
            if (players[ID].getCurrentMap() == players[otherID].getCurrentMap())
                players[otherID].draw(g2d);
        }   
    }

    public void messageDisplay(Graphics2D g2d, dialogueText dt){
        g2d.drawImage(buildingOccupiedBackground, 0, 144, 960, 576, null);

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
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        System.out.println("working");
        seconds--;
        if (seconds == -1) {
            minutes--;
            seconds = 59;
        }
    }
    
    // MODIFIER METHODS
    public void playingRunningGame(boolean tf) { playingRunningGame = tf; }
    public void playingHoracioQuiz(boolean tf) { playingHoracioQuiz = tf; }
    public void playingLeongQuiz(boolean tf) { playingLeongQuiz = tf; }
    public void playingCoinGame(boolean tf) { playingCoinGame = tf; }
    public void inTitleScreen(boolean tf) { inTitleScreen = tf; }
    public void inOrsem(boolean tf) { inOrsem = tf; }
    public void changeBuildingOccupiedStatus(boolean tf) { buildingOccupied = tf; }
    public void changeIndexOfMap(int i) { this.indexOfMap = i; }

    // ACESSOR METHODS
    public int getCurrentMap() { return (this.indexOfMap); }
    public KeyHandler getKeyHandlers() { return keyH; }
    public KeyBindings getKeyBindings() { return keyB; }
    public Thread getGameThread() { return gameThread; }
    public boolean getLeongStatus() { return otherPlayerIsPlaying0; }
    public boolean getHoracioStatus() { return otherPlayerIsPlaying1; }
    public boolean getCTCStatus() { return otherPlayerIsPlaying2; }
    public boolean getSOMStatus() { return otherPlayerIsPlaying3; }
    public Timer getTimer() { return timer; }
    public boolean[] getGameDoneArray() { return gameDone; }
}