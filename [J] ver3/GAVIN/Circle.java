/**
One of five basic shape classes. This creates an ellipse with even width and height and colored a specific color.
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

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Circle implements DrawingObject {
    private double xPos;
    private double yPos; 
    private double yReset;
    private double size;
    private Color cColor; 

    /**
     * The constructor. The numbers x and y are used to plot the coordinates, while s is used for the width and height of the circle (radius). The color, c, is set in the draw(g2d) method later on.
     */
    public Circle(double x, double y, double s, Color c) {
        xPos = x;
        yPos = y;
        yReset = y;
        size = s;
        cColor = c;
    }

    /**
     * The draw(g2d) method from DrawingObject is implemented here. This draws all the different visual objects and resets the AffineTransform.
     */
    public void draw(Graphics2D g2d){
        Ellipse2D roundObject = new Ellipse2D.Double(xPos, yPos, size, size);
        g2d.setColor(cColor);
        g2d.fill(roundObject);
    }

    /**
     * This method moves the x-coordinate of the Circle. 
     */
    public void adjustX(double amount) {
        xPos += amount;
      }

    /**
     * This method moves the y-coordinate of the Circle. 
     */
    public void adjustY(double amount) {
        yPos += amount;
      }

    /**
     * This method reverts the y-coordinate of the Circle to its original one. This is only used in the Steam object for a repeating animation.
     */
    public void resetY() {
        yPos = yReset;
    }
}
