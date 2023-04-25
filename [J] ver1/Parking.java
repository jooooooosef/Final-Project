import java.awt.*;
import java.awt.geom.*;

public class Parking implements MapElement {
    
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;

    public Parking(int xCoordinate, int yCoordinate, int width, int height){
        this.width = width;
        this.height  = height;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public void draw(Graphics2D g2d){
        Rectangle2D.Double parking = new Rectangle2D.Double(xCoordinate, yCoordinate, width, height);
        g2d.setColor(Color.ORANGE);
        g2d.fill(parking);
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



