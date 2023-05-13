/**
This class is the main Canvas, on which all visual objects are drawn. The program uses an ArrayList to iterate over each shape and draw as instructed. The color scheme for this project was inspired by GMK Bento.
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

import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;


public class SceneCanvas extends JComponent {
    ArrayList<DrawingObject> shapesToDraw;

    /**
     * The constructor. This sets the size to 1024*768, and the different components are instantiated and added to the ArrayList. The Steam object and the two Kamaboko objects are made fields so as to be able to modify them.
     */
    public SceneCanvas(){
        setPreferredSize(new Dimension(800, 600));
        shapesToDraw = new ArrayList<DrawingObject>();
    }

    /**
     * This method draws all the objects in the ArrayList. It also sets the RenderingHint for Anti-aliasing for smoother-looking objects.
     */
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints hints = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);
        
        for(int i = 0; i < shapesToDraw.size(); i++){
            shapesToDraw.get(i).draw(g2d);
        }
    }
}
