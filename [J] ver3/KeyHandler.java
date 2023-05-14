import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed;
    public boolean rightArrowPressed = true;
    
    @Override
    public void keyTyped(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT){
            rightArrowPressed = true;
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            upPressed = true;
        }
        if(key == KeyEvent.VK_S){
            downPressed = true;
        }
        if(key == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(key == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(key == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(key == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            upPressed = false;
        }
        if(key == KeyEvent.VK_S){
            downPressed = false;
        }
        if(key == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(key == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(key == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if(key == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }

}
