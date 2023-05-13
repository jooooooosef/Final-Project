/**
One of five basic shape classes. This class creates a rectangle of equal length and width.
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

public class Square implements DrawingObject {
    private double xPos;
    private double yPos; 
    private double size;
    private Color qColor; 

    /**
     * The constructor. This takes in width, height, and size to create the square object.
     */
    public Square(double x, double y, double s, Color c) {
        xPos = x;
        yPos = y;
        size = s;
        qColor = c;
    }

    /**
     * The draw(g2d) method from DrawingObject is implemented here. This fills the square in with a specific Color and draws it.
     */
    public void draw(Graphics2D g2d){
        Rectangle2D squareObject = new Rectangle2D.Double(xPos, yPos, size, size);
        g2d.setColor(qColor);
        g2d.fill(squareObject);
    }

    /**
     * The adjustX(double amount) method from DrawingObject is implemented here. This moves the x-coordinate of the Square.
     */
    public void adjustX(double amount) {
        xPos += amount;
      }

    /**
     * The adjustY(double amount) method from DrawingObject is implemented here. This moves the y-coordinate of the Square.
     */
      public void adjustY(double amount) {
        yPos += amount;
      }
}
