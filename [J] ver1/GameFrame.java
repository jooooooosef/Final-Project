import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame {
    
    private JFrame frame;
    private GameCanvas gameCanvas;
    private JPanel cp;
    
    public GameFrame(){
        frame = new JFrame();
        cp = (JPanel) frame.getContentPane();
        gameCanvas = new GameCanvas(800,600);
    }

    public void setUpGUI(){
        frame.setTitle("Good day, Ateneans!");
    
        cp.add(gameCanvas);
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void addKeyBindings(){
        ActionMap am = cp.getActionMap();
        InputMap im = cp.getInputMap();

        AbstractAction moveUp = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
                gameCanvas.getPlayer1().moveUp(20);
                
                if (gameCanvas.getPlayer1().isMovingToMap0()){
                    if (gameCanvas.getCurrentMap() == 1)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),400);

                    gameCanvas.changeIndexOfMap(0);
                } else if (gameCanvas.getPlayer1().isMovingToMap1()){
                    if (gameCanvas.getCurrentMap() == 0)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),150);

                    gameCanvas.changeIndexOfMap(1);
                }

                gameCanvas.repaint(); 
            }
        };

        AbstractAction moveDown = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
                gameCanvas.getPlayer1().moveDown(20);
            
                if (gameCanvas.getPlayer1().isMovingToMap0()){
                    if (gameCanvas.getCurrentMap() == 1)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),400);
                    gameCanvas.changeIndexOfMap(0);

                } else if (gameCanvas.getPlayer1().isMovingToMap1()){
                    if (gameCanvas.getCurrentMap() == 0)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),150);
                    gameCanvas.changeIndexOfMap(1);
                }
                gameCanvas.repaint();
                
            }
        };

        AbstractAction moveLeft = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
                gameCanvas.getPlayer1().moveLeft(20);
        
                if (gameCanvas.getPlayer1().isMovingToMap0()){
                    if (gameCanvas.getCurrentMap() == 1)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),400);
                    gameCanvas.changeIndexOfMap(0);

                } else if (gameCanvas.getPlayer1().isMovingToMap1()){
                    if (gameCanvas.getCurrentMap() == 0)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),150);
                    gameCanvas.changeIndexOfMap(1);
                }

                gameCanvas.repaint();
                
            }
        };

        AbstractAction moveRight = new AbstractAction(){
            public void actionPerformed(ActionEvent ae){
                if (gameCanvas.getPlayer1().isAllowedToMoveRight(gameCanvas, gameCanvas.getMap0().getMapElements())){
                    gameCanvas.getPlayer1().moveRight(20);
                }
                
                if (gameCanvas.getPlayer1().isMovingToMap0()){
                    if (gameCanvas.getCurrentMap() == 1)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),400);
                    gameCanvas.changeIndexOfMap(0);

                } else if (gameCanvas.getPlayer1().isMovingToMap1()){
                    if (gameCanvas.getCurrentMap() == 0)
                        gameCanvas.getPlayer1().changeLocation(gameCanvas.getPlayer1().getXCoordinate(),150);
                    gameCanvas.changeIndexOfMap(1);
                    
                }

                gameCanvas.repaint();
                
            }
        };

        am.put("up", moveUp);
        am.put("down", moveDown);
        am.put("left", moveLeft);
        am.put("right", moveRight);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,false), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,false), "down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0,false), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0,false), "right");
    }
}
