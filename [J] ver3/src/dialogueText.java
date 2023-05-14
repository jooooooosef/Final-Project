/**
Limit each piece of text to three lines. 
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
import java.util.ArrayList;

import javax.swing.*;

public class dialogueText implements DrawingObject {
    private int x; 
    private int y; 
    private int size;
    private ArrayList<String> dialogues; 

    /**
     * The constructor. This takes the coordinates for the text, as well as an integer for the font size.
     */
    public dialogueText(String a, String b, String c, String d){
        this.x = 95;
        this.y = 505;
        size = 34;
        dialogues = new ArrayList<String>();
        dialogues.add(a);
        dialogues.add(b);
        dialogues.add(c);
        dialogues.add(d);
    }

    /**
     * The draw(g2d) method from DrawingObject is implemented here. The font and color are set, and the text is drawn.
     * SOURCE: https://kodejava.org/how-do-i-draw-a-string-in-java-2d/
     * SOURCE: https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html#:~:text=Logical%20fonts%20are%20the%20five,Monospaced%2C%20Dialog%2C%20and%20DialogInput
     */
    public void draw(Graphics2D g2d){
        g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, size));
        g2d.setPaint(Color.decode("#48718a"));
        int lineHeight = g2d.getFontMetrics().getHeight();
        for(int t = 0; t < dialogues.size(); t++){
            g2d.drawString(dialogues.get(t), x, y + lineHeight*t);
        }
    }

    /**
     * The adjustX(double amount) method from DrawingObject is implemented here. This moves the x-coordinate of the text.
     */
    public void adjustX(double amount){
        x += amount;
    }

    /**
     * The adjustY(double amount) method from DrawingObject is implemented here. This moves the y-coordinate of the text.
     */
    public void adjustY(double amount){
        y += amount;
    }
}
