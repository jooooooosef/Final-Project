import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame {
    
    private JFrame frame;
    private GameCanvas gameCanvas;
    // private 
    // private JPanel cp;
    
    public GameFrame(){
        frame = new JFrame();
        // cp = (JPanel) frame.getContentPane();
        gameCanvas = new GameCanvas();
    }

    public void setUpGUI(){
        frame.setTitle("Good day, Ateneans!");
    
        // cp.add(gameCanvas);
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void changeCanvas(){
        // cp.re
    }
}
