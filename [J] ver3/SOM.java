import java.awt.*;
import java.awt.geom.*;

public class SOM implements MapElement {
    
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;

    public SOM(int xCoordinate, int yCoordinate, int width, int height){
        this.width = width;
        this.height  = height;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public void draw(Graphics2D g2d){
        Rectangle2D.Double som = new Rectangle2D.Double(xCoordinate, yCoordinate, width, height);
        g2d.setColor(Color.BLACK);
        g2d.fill(som);
    }

    public int getXCoordinate(){
        return this.xCoordinate;
    }

    public int getYCoordinate(){
        return this.yCoordinate;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }
}


