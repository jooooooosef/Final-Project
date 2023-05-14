import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class HoracioScreen implements DrawingObject{
    
    private int width;
    private int height;
    private StatusBar statusBar;
    private BufferedImage background;
    CoinCollisionChecker ccc;
    Rectangle2D.Double blackboard;

    public HoracioScreen(int width, int height){
        this.width = width;
        this.height = height;
        statusBar = new StatusBar(width,144);
        getBackground();
        blackboard = new Rectangle2D.Double(50, 165, 860, 230); 
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(background, 0, 0, 960, 720, null);
        statusBar.draw(g2d);
        g2d.setColor(Color.decode("#3c8539"));
        g2d.fill(blackboard);
    }

    public void getBackground(){
        try {
            background = ImageIO.read(getClass().getResourceAsStream("images/horacio-bg.png"));
        } catch (IOException e){
            System.out.println("Image file(s) not found in getBackground function in HoracioScreen class.");
        }
    }
}
