import javax.swing.*;
import java.awt.event.*;

public class KeyBindings {
    
    private GameCanvas gc;
    boolean spaceKeyPressed, enterKeyPressed = false;

    public KeyBindings(GameCanvas gc){
        this.gc = gc;
        addKeyBindings();
    }

    public void addKeyBindings(){
        ActionMap am = gc.getActionMap();
        InputMap im = gc.getInputMap();

        AbstractAction moveRight = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
                spaceKeyPressed = true;
            }
        };

        AbstractAction pressEnter = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
               enterKeyPressed = true;
            }
        };

        am.put("right", moveRight);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "right");
        am.put("enter", pressEnter);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "enter");
    }
}
