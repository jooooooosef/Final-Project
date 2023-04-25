import java.awt.*;
import java.awt.geom.*;

public class LeongHall implements MapElement {
    
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;

    public LeongHall(int xCoordinate, int yCoordinate, int width, int height){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height  = height;
    }

    @Override
    public void draw(Graphics2D g2d){
        Rectangle2D.Double leongHall = new Rectangle2D.Double(xCoordinate, yCoordinate, width, height);
        g2d.setColor(Color.BLACK);
        g2d.fill(leongHall);
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
