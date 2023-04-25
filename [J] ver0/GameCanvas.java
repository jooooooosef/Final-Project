import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class GameCanvas extends JComponent{
    private int width;
    private int height;
    private Player player1;
    private Map0 map0;
    private Map1 map1;
    private ArrayList<DrawingObject> maps;
    private int indexOfMap;

    public GameCanvas(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width,height));
        player1 = new Player(400,300, 0.7);
        map0 = new Map0(width, height, player1);
        map1 = new Map1(width, height, player1);
        indexOfMap = 0;
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        if (indexOfMap == 0)
            map0.draw(g2d);
        else if (indexOfMap == 1)
            map1.draw(g2d);
        player1.draw(g2d);
    }

    public void changeIndexOfMap(int i){
        this.indexOfMap = i;
    }

    public Player getPlayer1(){
        return player1;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean isCollidingWithLeftOfFrame(){
        return (player1.isCollidingWithLeftOfFrame(this));
    }
    public boolean isCollidingWithRightOfFrame(){
        return (player1.isCollidingWithRightOfFrame(this));
    }

    public boolean isCollidingWithTopOfFrame(){
        return ((player1.isCollidingWithTopOfFrame(this)));
    }

    public boolean isCollidingWithBottomOfFrame(){
        return ((player1.isCollidingWithBottomOfFrame(this)));
    }

    public boolean elementIsCollidingWithRightSideOfPlayer(){
        return (map0.elementIsCollidingWithRightSide(player1));
    }

    public boolean elementIsCollidingWithLeftSideOfPlayer(){
        return(map0.isCollidingWithLeftSide(player1));
    }

    public int getCurrentMap(){
        return (this.indexOfMap);
    }
    
}


