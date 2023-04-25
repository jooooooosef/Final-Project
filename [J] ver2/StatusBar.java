import java.awt.*;
import java.awt.geom.*;

public class StatusBar implements DrawingObject{
    private int width;
    private int height;
    
    public StatusBar(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2d){
        Rectangle2D.Double statusBarBase = new Rectangle2D.Double(0,0,width,height); 
        g2d.setColor(Color.CYAN);
        g2d.fill(statusBarBase);
    }
}
