import java.awt.*;

public interface MapElement {
    void draw(Graphics2D g2d);
    int getXCoordinate();
    int getYCoordinate();
    int getHeight();
    int getWidth();
}
