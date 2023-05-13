/**
One of five basic shape classes, this class draws a Line object. It takes two points on the coordinate plane, a specific stroke width, and a color.
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

public class Line implements DrawingObject {
    private double x_ini;
    private double y_ini; 
    private double x_fin; 
    private double y_fin;
    private Color lColor;
    private int width; 

    /**
     * The constructor. This takes in two points, a Color, and an integer for the thickness. 
     */
    public Line(double x1, double y1, double x2, double y2, Color c, int w) {
        x_ini = x1;
        y_ini = y1;
        x_fin = x2;
        y_fin = y2;
        lColor = c;
        width = w;
    }

    /**
     * The draw(g2d) method from DrawingObject is implemented here. This makes the line, sets the color and stroke width, and then draws the line.
     */
    public void draw(Graphics2D g2d){
        Line2D.Double lineObject = new Line2D.Double(x_ini, y_ini, x_fin, y_fin);
        g2d.setColor(lColor);
        g2d.setStroke(new BasicStroke(width));
        g2d.draw(lineObject);
    }

    /**
     * The adjustX(double amount) method from DrawingObject is implemented here. This shifts the x-coordinates of the two points, thereby shifting the whole line.
     */
    public void adjustX(double amount) {
        x_ini += amount;
        x_fin += amount;
      }

    /**
     * The adjustY(double amount) method from DrawingObject is implemented here. This shifts the y-coordinates of the two points, thereby shifting the whole line.
     */
    public void adjustY(double amount) {
        y_ini += amount; 
        y_fin += amount; 
      }
}
