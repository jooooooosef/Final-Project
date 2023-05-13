/**
This class starts up the GUI. Through the main method, a resolution for the window is set and the setUpGUI() method is called.
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

public class SceneStarter {

    /**
     * The main method. A new SceneFrame is instantiated and run.
     */
    public static void main (String[] args){
        SceneFrame sf = new SceneFrame(960, 720);
        sf.setUpGUI();
    }
}
