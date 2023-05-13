/**
One of five basic shape classes. This creates a Triangle using a path.
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

public class Triangle implements DrawingObject {
    private double x_1;
    private double y_1; 
    private double x_2; 
    private double y_2;
    private double x_3; 
    private double y_3;
    private Color tColor;

    /**
     * The constructor. This takes three points and a Color for the triangle.
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3, Color c) {
        x_1 = x1;
        y_1 = y1;
        x_2 = x2;
        y_2 = y2;
        x_3 = x3;
        y_3 = y3;
        tColor = c;
    }

    /**
     * The draw(g2d) method from DrawingObject is implemented here. This uses a path to draw a Triangle and fill it with a color.
     */
    public void draw(Graphics2D g2d){
        Path2D.Double triangleObject = new Path2D.Double();
        
        triangleObject.moveTo(x_1, y_1);
        triangleObject.lineTo(x_2, y_2);
        triangleObject.lineTo(x_3, y_3);
        triangleObject.lineTo(x_1, y_1);

        g2d.setColor(tColor);
        g2d.fill(triangleObject);
    }

    /**
     * The adjustX(double amount) method from DrawingObject is implemented here. This moves the x-coordinates, thereby moving the whole Triangle.
     */
    public void adjustX(double amount) {
        x_1 += amount;
        x_2 += amount;
        x_3 += amount;
      }
  
    /**
     * The adjustY(double amount) method from DrawingObject is implemented here. This moves the y-coordinates, thereby moving the whole Triangle.
     */
      public void adjustY(double amount) {
        y_1 += amount;
        y_2 += amount;
        y_3 += amount;
      }
}
