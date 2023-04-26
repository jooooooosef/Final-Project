import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.InterruptedException;;

public class GameCanvas extends JComponent implements Runnable{

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

    // COLLISIONS
    Player player1;
    TileManager tm = new TileManager(this);
    CollisionChecker cChecker = new CollisionChecker(this);

    // KEYLISTENER
    private KeyHandler keyH = new KeyHandler();

    // THREAD
    private Thread gameThread;

    // IRRELEVANT FIELDS (AS OF NOW)
    private Map0 map0;
    private Map1 map1;
    private ArrayList<DrawingObject> maps;
    private int indexOfMap;

    

    public GameCanvas(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);

        player1 = new Player(480,360, tileSize, 10, keyH, this);
        map0 = new Map0(screenWidth, screenHeight, player1);
        map1 = new Map1(screenWidth, screenHeight, player1);
        indexOfMap = 0;

        gameThread = new Thread(this);
        gameThread.start();

        this.addKeyListener(keyH);
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

        /* if (indexOfMap == 0)
            map0.draw(g2d);
        else if (indexOfMap == 1)
            map1.draw(g2d); */
        
        tm.draw(g2d);
        player1.draw(g2d);
        this.update();
    }

    private void update(){
        player1.move();
    }





    public void changeIndexOfMap(int i){
        this.indexOfMap = i;
    }

    public Player getPlayer1(){
        return player1;
    }

    public int getCurrentMap(){
        return (this.indexOfMap);
    }

    public Map0 getMap0(){
        return (map0);
    }
    
}


