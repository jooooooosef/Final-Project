import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class HoracioQuiz implements DrawingObject {
    private KeyHandler key;
    private int screenWidth, screenHeight, quizIndex;
    private int score;
    Thread gameThread;
    ArrayList<Choice> choices;
    ArrayList<Question> questions;
    AllCanvas allCanvas;
    HoracioScreen horacioScreen;
    String checked;
    dialogueText start;
    Rectangle2D.Double displayText, tntName;
    int choiceIndex, gameFlowIndex;

    public HoracioQuiz(AllCanvas ac){
        allCanvas = ac;
        key = ac.giveKeyHandler();
        gameThread = ac.giveThread();
        screenWidth = ac.giveScreenWidth();
        screenHeight = ac.giveScreenHeight();

        horacioScreen = new HoracioScreen(screenWidth, screenHeight);

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
        start = new dialogueText("Welcome to InTACT!", "To pass the quiz, get a score of at least 6/10.", "Press A or D to cycle through the questions,", "and Space to submit your answer. Good luck!");
    }

    public void draw(Graphics2D g2d){
        horacioScreen.draw(g2d);
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
            if(quizIndex < questions.size()){
                questions.get(quizIndex).draw(g2d);
            }
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
            g2d.drawString("Rachel", 106, 458);
            results(score).draw(g2d);
        }
    }

    public void addChoices(){
        choices.add(new Choice(allCanvas, "a", 85, 450));
        choices.add(new Choice(allCanvas, "b", 295, 450));
        choices.add(new Choice(allCanvas, "c", 505, 450));
        choices.add(new Choice(allCanvas, "d", 715, 450));
    }

    public void addQuestions(){
        questions.add(new Question("1. AdMU stands for…", "A. Ateneo de Manila University", "B. Ateneo de Minnesota University", "C. Ateneo de Mgreatest Uschool", "D. Across de Multiple Universes", "a"));
        questions.add(new Question("2. The school's motto is…", "A. Ad Majorem Dei Gloriam", "B. Magis", "C. Lux in Domino", "D. Ignatian Spirituality", "c"));
        questions.add(new Question("3. Lux in Domino stands for…", "A. Light in the Lord ", "B. Light in the World", "C. Light in the Domino", "D. Light in God", "a"));
        questions.add(new Question("4. There are _ Loyola Schools.", "A. 4", "B. 5", "C. 6", "D. 7", "b"));
        questions.add(new Question("5. Meanwhile, there are _ Professional Schools.", "A. 3", "B. 4", "C. 5", "D. 6", "b"));
        questions.add(new Question("6. The BS CS course is part of the School of ___.", "A. Science and Mathematics", "B. Magic", "C. Engineering", "D. Science and Engineering", "d"));
        questions.add(new Question("7. The home org of CS Students is ___.", "A. CompSAT", "B. The Computer Org", "C. ProgVar", "D. SATComp", "a"));
        questions.add(new Question("8. SOSE has __ undergraduate programs.", "A. 17", "B. 18", "C. 19", "D. 20", "b"));
        questions.add(new Question("9. Of these programs, _ have CS in their name.", "A. 2", "B. 3", "C. 4", "D. 5", "b"));
        questions.add(new Question("10. One Big _____!", "A. Fight", "B. Flight", "C. Fright", "D. Freight", "a"));
        questions.add(new Question("BONUS: Choose the best Ateneo professor.", "A. Jessica “Jess” O. Sugay", "B. Alberto “Choob” H. Medalla", "C. Sugay, Jessica “Jess” O.", "D. Medalla, Alberto “Choob” H.", "ANY"));
    }


    public dialogueText results(int s){
        dialogueText result = null;
        if(s >= 6){
            result = new dialogueText("You scored a total of " + Integer.toString(score) + "/10.", "Congratulations! You win!", "Press the spacebar to return to campus.", "");
        }
        else{
            result = new dialogueText("You scored a total of only " + Integer.toString(score) + "/10.", "Better luck next time!", "Press the spacebar to return to campus.", "");
        }
        return result;
    }

    public int giveGameIndex(){
        return gameFlowIndex;
    }

    public void gameStart(){
        if(key.spacePressed){
            gameFlowIndex++;
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            key.spacePressed = false;
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
        else if(key.spacePressed){
            gameFlowIndex = 2;
                try {
                    gameThread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            key.spacePressed = false;
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
        if(key.spacePressed && quizIndex < 11){
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
            if(quizIndex == 11){
                gameFlowIndex = 3;
            }
            checked = null;
            key.spacePressed = false;
        }
    }


    public void gameEnd(){
        if(key.spacePressed){
            allCanvas.backToMain();
            try {
                gameThread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            gameFlowIndex = 0;
            quizIndex = 0;
            choiceIndex = 0;
            score = 0;
            key.spacePressed = false;
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
