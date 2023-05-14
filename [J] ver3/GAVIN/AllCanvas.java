import java.awt.*;
import javax.swing.JComponent;

public class AllCanvas extends JComponent implements Runnable{
    // Dimensions, FPS 
    public final int screenWidth;
    public final int screenHeight;
    private final int FPS = 60;

    // KEYLISTENER
    private KeyHandler key = new KeyHandler();

    // THREAD
    private Thread gameThread;

    //CANVAS FIELDS
    private int sceneIndex; 
    public TitleScreen titleScreen;
    public Orsem orsem;
    public CoinGame SOM;
    public HoracioQuiz HOR;
    public LeongQuiz LEO;
    Player player;

    //Put StatusBar in your allCanvas, then communicate with the minigames

    public AllCanvas(int w, int h){
        screenWidth = w;
        screenHeight = h;

        this.setPreferredSize(new Dimension(w, h));
        this.addKeyListener(key);
        this.setFocusable(true);

        titleScreen = new TitleScreen(this);
        orsem = new Orsem(this);
        SOM = new CoinGame(this);
        HOR = new HoracioQuiz(this);
        LEO = new LeongQuiz(this);

        gameThread = new Thread(this);
        gameThread.start();
        sceneIndex = 0;

        player = new Player(0, 480, 360, 100, 5, key, this); 
    }

    @Override 
    public void run(){
        double drawInterval = 1000000000/FPS;;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread!= null){
            if(sceneIndex == 0){
                titleScreen.gameStart();
            }
            else if(sceneIndex == 1){
                orsem.changeDialogue();
            }
            /** else if(sceneIndex == 2){
                if(SOM.giveGameIndex() == 0){
                    SOM.gameStart();
                }
                else if(SOM.giveGameIndex() == 1){
                    SOM.CoinCollision();
                }
                else if(SOM.giveGameIndex() == 2){
                    SOM.gameEnd();
                }  
            } **/

            else if(sceneIndex == 2){
                if(LEO.giveGameIndex() == 0){
                    LEO.gameStart();
                }
                else if(LEO.giveGameIndex() == 1){
                    LEO.quizGame();
                }
                else if(LEO.giveGameIndex() == 2){
                    LEO.checked();
                }  
                else if(LEO.giveGameIndex() == 3){
                    LEO.gameEnd();
                }  
            }

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
            titleScreen.draw(g2d); 
        }
        else if(sceneIndex == 1){
            orsem.draw(g2d);
        }
        else if(sceneIndex == 2){
            LEO.draw(g2d);
        }
    }

    public void updateSceneIndex(){
        sceneIndex++;
    }

    public void backToMain(){
        sceneIndex = 0;
    }

    public KeyHandler giveKeyHandler(){
        return key;
    }

    public Thread giveThread(){
        return gameThread;
    }

    public int giveScreenWidth(){
        return screenWidth;
    }

    public int giveScreenHeight(){
        return screenHeight;
    }
}