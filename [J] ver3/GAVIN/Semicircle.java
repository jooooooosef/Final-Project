/**
One of five basic shape classes. This class creates a semicircle by setting the points and making a line back to the moveTo() point. 
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

public class Semicircle implements DrawingObject {
    private double x_1;
    private double y_1; 
    private double x_2; 
    private double y_2;
    private double x_3; 
    private double y_3;
    private double x_4; 
    private double y_4;
    private Color sColor;

    /**
     * The constructor. This takes in four points and a Color to make a Semicircle. 
     */
    public Semicircle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, Color c) {
        x_1 = x1;
        y_1 = y1;
        x_2 = x2;
        y_2 = y2;
        x_3 = x3;
        y_3 = y3;
        x_4 = x4; 
        y_4 = y4;
        sColor = c;
    }

    /**
     * The draw(g2d) method from DrawingObject is implemented here. This draws the Semicircle and fills it with a color.
     */
    public void draw(Graphics2D g2d){
        Path2D.Double semicircleObject = new Path2D.Double();
        
        semicircleObject.moveTo(x_1, y_1);
        semicircleObject.curveTo(x_2, y_2, x_3, y_3, x_4, y_4);
        semicircleObject.lineTo(x_1, y_1);

        g2d.setColor(sColor);
        g2d.fill(semicircleObject);
    }

    /**
     * The adjustX(double amount) method from DrawingObject is implemented here. This moves the x-coordinates, thereby moving the whole Semicircle.
     */
    public void adjustX(double amount) {
        x_1 += amount;
        x_2 += amount;
        x_3 += amount;
        x_4 += amount;
      }
  
    /**
     * The adjustY(double amount) method from DrawingObject is implemented here. This moves the y-coordinates, thereby moving the whole Semicircle.
     */
    public void adjustY(double amount) {
        y_1 += amount;
        y_2 += amount;
        y_3 += amount;
        y_4 += amount;
      }
}
