import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class LeongQuiz implements DrawingObject {
    private KeyHandler key;
    private int screenWidth, screenHeight, quizIndex;
    private int score;
    Thread gameThread;
    ArrayList<Choice> choices;
    ArrayList<Question> questions;
    GameCanvas gc;
    LeongScreen leongScreen;
    String checked;
    dialogueText start;
    Rectangle2D.Double displayText, tntName;
    int choiceIndex, gameFlowIndex;

    public LeongQuiz(GameCanvas gc){
        this.gc = gc;
        key = gc.getKeyHandlers();
        gameThread = gc.getGameThread();
        screenWidth = gc.screenWidth;
        screenHeight = gc.screenHeight;

        leongScreen = new LeongScreen(screenWidth, screenHeight);

        gameFlowIndex = 0;
        quizIndex = 0;
        choiceIndex = 0;
        
        score = 0;
        checked = null;
        
        questions = new ArrayList<Question>();
        addQuestions();
        choices = new ArrayList<Choice>();
        addChoices();
        choices.get(0).highlight();

        displayText = new Rectangle2D.Double(80, 435, 800, 250);
        tntName = new Rectangle2D.Double(90, 425, 150, 40);
        start = new dialogueText("Welcome to InTACT!", "To pass the quiz, get a score of at least 6/10.", "Press A or D to cycle through the questions,", "and enter to submit your answer. Good luck!");
    }

    public void draw(Graphics2D g2d){
        leongScreen.draw(g2d);
        g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 50));
        g2d.setPaint(Color.decode("#d6dacf"));
        g2d.drawString("SCORE: " + Integer.toString(score)+"/10", 600, 70);

        if(gameFlowIndex == 0){
            g2d.setColor(Color.decode("#eab676"));
            g2d.fill(displayText);
            g2d.draw(displayText);
    
            g2d.setColor(Color.decode("#154c79"));
            g2d.fill(tntName);
            g2d.draw(tntName);

            g2d.setPaint(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 34));
            g2d.drawString("Rachel", 106, 458);
            start.draw(g2d);
        }

        else if(gameFlowIndex == 1){
            AffineTransform reset = g2d.getTransform();
            g2d.translate(0,42);

            if(quizIndex < questions.size()){
                questions.get(quizIndex).draw(g2d);
            }
            g2d.setTransform(reset);
            for(int i = 0; i < choices.size(); i++){
                choices.get(i).draw(g2d);
            }
        }

        else if(gameFlowIndex == 2){
            g2d.setColor(Color.decode("#eab676"));
            g2d.fill(displayText);
            g2d.draw(displayText);
    
            g2d.setColor(Color.decode("#154c79"));
            g2d.fill(tntName);
            g2d.draw(tntName);

            g2d.setPaint(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 34));
            g2d.drawString("Rachel", 106, 458);
            g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC,55));
            if(checked != null){
                g2d.drawString(checked, 106, 575);   
            }
        }

        else if(gameFlowIndex == 3){
            g2d.setColor(Color.decode("#eab676"));
            g2d.fill(displayText);
            g2d.draw(displayText);
    
            g2d.setColor(Color.decode("#154c79"));
            g2d.fill(tntName);
            g2d.draw(tntName);

            g2d.setPaint(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 34));
            results(score).draw(g2d);
        }
    }

    public void addChoices(){
        choices.add(new Choice(gc, "a", 85, 450));
        choices.add(new Choice(gc, "b", 295, 450));
        choices.add(new Choice(gc, "c", 505, 450));
        choices.add(new Choice(gc, "d", 715, 450));
    }

    public void addQuestions(){
        questions.add(new Question("1. AdMU began in ____.", "A. 2023", "B. 1858", "C. 1859", "D. 1870", "c"));
        questions.add(new Question("2. AdMU is a ___ school.", "A. Jesuit", "B. Dominican", "C. Augustinian", "D. Non-Sectarian", "a"));
        questions.add(new Question("3. Ateneo's official colors are", "A. Yellow and Green", "B. Purple and Yellow", "C. Pink and Apricot", "D. Blue and White", "d"));
        questions.add(new Question("4. Ateneo often sings 'Song for ___'", "A. Mary", "B. Jesus", "C. Ateneo", "D. the Nation", "a"));
        questions.add(new Question("5. Complete the phrase:'We stand on a ___'.", "A. Plain", "B. Tree", "C. Hill", "D. Ship", "c"));
        questions.add(new Question("6. The Ateneo Art Gallery was established in ____.", "A. 1957", "B. 1958", "C. 1959", "D. 1960", "d"));
        questions.add(new Question("7. Just beside the Art Gallery is the ____.", "A. SOM", "B. Arete", "C. Leong Hall", "D. CTC", "b"));
        questions.add(new Question("8. Ateneo's mascot is the _____.", "A. Blue Eagle", "B. Red Eagle", "C. Black Mamba", "D. Green Goblin", "a"));
        questions.add(new Question("9. Complete the phrase:'We pray, you'll keep us, Mary, ___'.", "A. always true", "B. while(true)", "C. constantly true", "D. constantly blue", "c"));
        questions.add(new Question("10. AdMU has a total of _ branches.", "A. 5", "B. 4", "C. 3", "D. 2", "b"));
    }


    public dialogueText results(int s){
        dialogueText result = null;
        if(s >= 6){
            result = new dialogueText("You scored a total of " + Integer.toString(score) + "/10.", "Congratulations! You win!", "Press enter to return to campus.", "");
        }
        else{
            result = new dialogueText("You scored a total of only " + Integer.toString(score) + "/10.", "Better luck next time!", "Press enter to return to campus.", "");
        }
        return result;
    }

    public int giveGameIndex(){
        return gameFlowIndex;
    }

    public void gameStart(){
        if(key.enterPressed){
            gameFlowIndex++;
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        key.enterPressed = false;
        }
    }

    public void quizGame(){
        if(key.leftPressed && choiceIndex > 0){
            choiceIndex--;
            choiceHighlight();
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            key.leftPressed = false;
            }
        else if(key.rightPressed && choiceIndex < 3){
            choiceIndex++;
            choiceHighlight();
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            key.rightPressed = false;
            }
        else if(key.enterPressed){
            gameFlowIndex = 2;
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            key.enterPressed = false;
            }
    }

    public void check(Choice c, Question q){
        if(c.getIdentifier().equals(q.getCorrectAnswer()) || q.getCorrectAnswer().equals("ANY")){
            checked = "That's correct!";
        }
        else{
            checked = "Sorry! That's not correct.";
        }
    }

    public void checked(){
        check(choices.get(choiceIndex), questions.get(quizIndex));
        if(key.enterPressed && quizIndex < 10){
            gameFlowIndex = 1;
            quizIndex++;
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            if(checked.equals("That's correct!")){
                score++;
            }
            if(quizIndex == 10){
                gameFlowIndex = 3;
            }
            checked = null;
            key.enterPressed = false;
        }
    }


    public void gameEnd(){
        if(key.enterPressed){
            gc.playingLeongQuiz(false);
            if (score >= 6)
                gc.getGameDoneArray()[0] = true;
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            gameFlowIndex = 0;
            quizIndex = 0;
            choiceIndex = 0;
            score = 0;
            key.enterPressed = false;
        }
    }

    public void choiceHighlight(){
        if(choiceIndex == 0){
            choices.get(1).dehighlight();
            choices.get(2).dehighlight();
            choices.get(3).dehighlight();
        }
        else if(choiceIndex == 1){
            choices.get(0).dehighlight();
            choices.get(2).dehighlight();
            choices.get(3).dehighlight();
        }
        else if(choiceIndex == 2){
            choices.get(0).dehighlight();
            choices.get(1).dehighlight();
            choices.get(3).dehighlight();
        }
        else if(choiceIndex == 3){
            choices.get(0).dehighlight();
            choices.get(1).dehighlight();
            choices.get(2).dehighlight();
        }
        choices.get(choiceIndex).highlight();
    }
}
