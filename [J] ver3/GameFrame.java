import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame {
    
    private JFrame frame;
    private GameCanvas gameCanvas;
    private JPanel cp;
    private int ID;
    
    public GameFrame(int ID){
        this.ID = ID;

        frame = new JFrame();
        cp = (JPanel) frame.getContentPane();
        gameCanvas = new GameCanvas(ID);
    }

    public void setUpGUI(){
        frame.setTitle("Good day, Ateneans!");
    
        cp.add(gameCanvas);
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // ACCESSOR METHODS

    public GameCanvas getGameCanvas() { return gameCanvas; }
}
