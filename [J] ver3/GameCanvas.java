import java.awt.*;
import javax.swing.*;
import java.lang.InterruptedException;

public class GameCanvas extends JComponent implements Runnable{

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
    private RunningGame runningGame;
    private MiniGame1 mg1 = new MiniGame1();
    private MiniGame2 mg2 = new MiniGame2();
    private CoinGame coinGame;
    private boolean playingRunningGame = false;
    private boolean playingMiniGame1 = false;
    private boolean playingMiniGame2 = false;
    private boolean playingCoinGame = false;
    private boolean otherPlayerIsPlaying0 = false;
    private boolean otherPlayerIsPlaying1 = false;
    private boolean otherPlayerIsPlaying2 = false;
    private boolean otherPlayerIsPlaying3 = false;

    // GAMEFLOW FIELDS
    private TitleScreen titleS = new TitleScreen(this);
    public Orsem orsem = new Orsem(this);
    private boolean inTitleScreen = true;       // START
    private boolean inOrsem = false;

    // IRRELEVANT FIELDS (AS OF NOW)
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

        // MINIGAMES
        runningGame = new RunningGame(players[ID], this);
        coinGame = new CoinGame(players[ID], this);

        gameThread = new Thread(this);
        gameThread.start();

        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        } else if (playingMiniGame1) {
            mg1.draw(g2d);
        } else if (playingMiniGame2) {
            mg2.draw(g2d);
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
            g2d.drawString("Time Remaining: X", 20, 90);
            g2d.drawString("Buildings: 0/4", 630,90);

            players[ID].draw(g2d);
            
            if (players[ID].getCurrentMap() == players[otherID].getCurrentMap())
                players[otherID].draw(g2d);
                
            this.update();
        }
        
    }

    private void update(){
        players[ID].move();
    }

    public void changeIndexOfMap(int i){
        this.indexOfMap = i;
    }

    public void playingRunningGame(boolean tf) { playingRunningGame = tf; }
    public void playingMiniGame1(boolean tf) { playingMiniGame1 = tf; }
    public void playingMiniGame2(boolean tf) { playingMiniGame2 = tf; }
    public void playingCoinGame(boolean tf) { playingCoinGame = tf; }
    public void inTitleScreen(boolean tf) { inTitleScreen = tf; }
    public void inOrsem (boolean tf) { inOrsem = tf; }

    public int getCurrentMap(){ return (this.indexOfMap); }
    public KeyHandler getKeyHandlers() { return keyH; }
    public KeyBindings getKeyBindings() { return keyB; }
    public Thread getGameThread() { return gameThread; }

    public Map0 getMap0(){
        return (map0);
    }
    
}


