import java.awt.*;
import java.awt.geom.*;

public class Map1 implements DrawingObject{
    private int width;
    private int height;
    private Player player1;
    private StatusBar statusBar;
    private Parking parking;
    private SOM som;
    private CTC ctc;
    private SkyWalk skywalk;
    private SkyWalkBridge0 skyWalkBridge0;
    private SkyWalkBridge1 skyWalkBridge1;

    public Map1(int width, int height, Player player1){
        this.width = width;
        this.height = height;
        this.player1 = player1;
        statusBar = new StatusBar(width,100);
        parking = new Parking(0,100,700,100);
        som = new SOM(50,425,250,150);
        ctc = new CTC(350,425,250,150);
        skywalk = new SkyWalk(0,250,650,130);
        skyWalkBridge0 = new SkyWalkBridge0(112,380,125,45);
        skyWalkBridge1 = new SkyWalkBridge1(412,380,125,45);
    }

    @Override
    public void draw(Graphics2D g2d){
        Rectangle2D.Double background = new Rectangle2D.Double(0,0,width,height);
        g2d.setColor(Color.MAGENTA);
        g2d.fill(background);
        
        som.draw(g2d);
        ctc.draw(g2d);
        parking.draw(g2d);
        skywalk.draw(g2d);
        skyWalkBridge0.draw(g2d);
        skyWalkBridge1.draw(g2d);

        statusBar.draw(g2d);

        player1.draw(g2d);
    }
    
}


