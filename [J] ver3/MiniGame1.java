import java.awt.*;
import java.awt.geom.*;

public class MiniGame1 implements DrawingObject{
    
    
    @Override 
    public void draw(Graphics2D g2d){
        Rectangle2D.Double rectangle = new Rectangle2D.Double(0,0,960,720);
        g2d.setColor(Color.WHITE);
        g2d.fill(rectangle);
    }

}
