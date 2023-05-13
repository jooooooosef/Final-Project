/**
This is the program in which the GUI Frame is instantiated. The program includes the different needed components as well as the ActionListener for the timer.
@author Michael Gavin N. Del Castillo (222055)
@version March 9, 2023
**/

/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.
I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneFrame extends JPanel{
    private int width;
    private int height;
    private JFrame frame;
    private JPanel gamePanel;
    private AllCanvas allCanvas;

    /**
     * The constructor. This takes in width and height for the window shape. This also makes use of a Panel in which the Canvas is locked in (it also helps center the Canvas). A timer is instantiated for the looping Steam animation and the music file is played here.
     */
    public SceneFrame(int w, int h) {
        width = w;
        height = h;
        frame = new JFrame();
        gamePanel = new JPanel();
        allCanvas = new AllCanvas(w, h);
      }
  
    /**
     * This method sets up the GUI to be shown on the screen. The Canvas is added to a Panel, which is then added to the Frame. The different basic characteristics, like closing on exit and the pack() method are also called here.
     */
    public void setUpGUI() {
        Container cp = frame.getContentPane();
        gamePanel.add(allCanvas);
        cp.add(gamePanel);
        frame.setTitle("Good Day, Ateneans!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
      }
}
