import javax.swing.*;
import java.awt.event.*;

public class KeyBindings {
    
    private GameCanvas gc;
    boolean spaceKeyPressed = false;

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

        am.put("right", moveRight);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "right");
    }
}
