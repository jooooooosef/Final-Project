 import java.awt.*;
 import java.awt.image.BufferedImage;
 import javax.imageio.ImageIO;
 import javax.swing.*;
 import java.io.IOException;

 
 public class Choice implements DrawingObject{
 
     private int xCoordinate, yCoordinate, size;
     private BufferedImage aDef, aPick, bDef, bPick, cDef, cPick, dDef, dPick, image, def, select;
     private String identifier;
     AllCanvas allCanvas;
     Timer coinTimer;
     KeyHandler key;
 
     public Choice(AllCanvas ac, String s, int xCoordinate, int yCoordinate){
        allCanvas = ac;
        key = ac.giveKeyHandler();
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
            aDef = ImageIO.read(getClass().getResourceAsStream("/aDef.png"));
            aPick = ImageIO.read(getClass().getResourceAsStream("/aPick.png"));
            bDef = ImageIO.read(getClass().getResourceAsStream("/bDef.png"));
            bPick = ImageIO.read(getClass().getResourceAsStream("/bPick.png"));
            cDef = ImageIO.read(getClass().getResourceAsStream("/cDef.png"));
            cPick = ImageIO.read(getClass().getResourceAsStream("/cPick.png"));
            dDef = ImageIO.read(getClass().getResourceAsStream("/dDef.png"));
            dPick = ImageIO.read(getClass().getResourceAsStream("/dPick.png"));
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
 