import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Map0 implements DrawingObject{
    
    private int width;
    private int height;
    private StatusBar statusBar;
    private ArrayList<MapElement> mapElements;

    public Map0(int width, int height){
        this.width = width;
        this.height = height;

        statusBar = new StatusBar(width,100);

        mapElements = new ArrayList<MapElement>();
        mapElements.add(new LeongHall(425,100,200,150));
        mapElements.add(new Horacio(25,250,150,200));
        mapElements.add(new Trees(0,500,700,100));
    }

    @Override
    public void draw(Graphics2D g2d){
        Rectangle2D.Double background = new Rectangle2D.Double(0,0,width,height);
        g2d.setColor(Color.GREEN);
        g2d.fill(background);

        statusBar.draw(g2d);

        for (MapElement me : mapElements)
            me.draw(g2d);
    }

    public ArrayList<MapElement> getMapElements(){
        return mapElements;
    }
}
