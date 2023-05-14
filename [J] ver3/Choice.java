 import java.awt.*;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import javax.swing.*;
 import java.io.IOException;

 
 public class Choice implements DrawingObject{
 
     private int xCoordinate, yCoordinate, size;
     private BufferedImage aDef, aPick, bDef, bPick, cDef, cPick, dDef, dPick, image, def, select;
     private String identifier;
     GameCanvas gc;
     Timer coinTimer;
     KeyHandler key;
 
     public Choice(GameCanvas gc, String s, int xCoordinate, int yCoordinate){
        this.gc = gc;
        key = gc.getKeyHandlers();
        identifier = s;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        size = 160;
        image = null;
        def = null;
        select = null;
        getChoiceImages();
        getSprites(s);
     }
 
     public void getChoiceImages(){
         try {
            aDef = ImageIO.read(getClass().getResourceAsStream("images/aDef.png"));
            aPick = ImageIO.read(getClass().getResourceAsStream("images/aPick.png"));
            bDef = ImageIO.read(getClass().getResourceAsStream("images/bDef.png"));
            bPick = ImageIO.read(getClass().getResourceAsStream("images/bPick.png"));
            cDef = ImageIO.read(getClass().getResourceAsStream("images/cDef.png"));
            cPick = ImageIO.read(getClass().getResourceAsStream("images/cPick.png"));
            dDef = ImageIO.read(getClass().getResourceAsStream("images/dDef.png"));
            dPick = ImageIO.read(getClass().getResourceAsStream("images/dPick.png"));
         } catch (IOException e){
             System.out.println("Image file(s) not found in getChoiceImages function in Choice class.");
         }
     }
 
     @Override
     public void draw(Graphics2D g2d){
        g2d.drawImage(image, xCoordinate, yCoordinate, size, size, null);
     }

    public void getSprites(String s){
        if(s.equals("a")){
            def = aDef;
            select = aPick;
            image = def;
        }
        if(s.equals("b")){
            def = bDef;
            select = bPick;
            image = def;
        }
        if(s.equals("c")){
            def = cDef;
            select = cPick;
            image = def;
        }
        if(s.equals("d")){
            def = dDef;
            select = dPick;
            image = def;
        }
    }

    public String getIdentifier(){
        return identifier;
    }

    public void highlight(){
        image = select;
    }

    public void dehighlight(){
        image = def;
    }
 }
 