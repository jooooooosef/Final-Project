import java.lang.Math.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.InterruptedException;

public class CollisionChecker {

    private GameCanvas gc;
    private KeyHandler keyH;

    public CollisionChecker(GameCanvas gc, KeyHandler kh){
        this.gc = gc;
        keyH = kh;
    }

    public void checkTile(Player player){

        int playerColIndex = (player.getXCoordinate())/gc.tileSize;
        int playerNextColIndex =  (player.getXCoordinate()+gc.tileSize)/gc.tileSize;
        
        int playerRowIndex = (player.getYCoordinate())/gc.tileSize;
        int playerNextRowIndex = (player.getYCoordinate()+gc.tileSize)/gc.tileSize;
        
        int currentTilePos = gc.tm.mapTileNum[playerColIndex][playerRowIndex];

        int tilePosAfterMovingRight0 = gc.tm.mapTileNum[playerColIndex+1][playerRowIndex];
        int tilePosAfterMovingLeft0 = gc.tm.mapTileNum[playerColIndex][playerRowIndex];
        int tilePosAfterMovingUp0 = gc.tm.mapTileNum[playerColIndex][playerRowIndex];
        int tilePosAfterMovingDown0 = gc.tm.mapTileNum[playerColIndex][playerRowIndex+1];

        int tilePosAfterMovingRight1 = gc.tm.mapTileNum[playerColIndex+1][playerNextRowIndex];
        int tilePosAfterMovingLeft1 = gc.tm.mapTileNum[playerColIndex][playerNextRowIndex];
        int tilePosAfterMovingUp1 = gc.tm.mapTileNum[playerNextColIndex][playerRowIndex];
        int tilePosAfterMovingDown1 = gc.tm.mapTileNum[playerNextColIndex][playerRowIndex+1];

        //  CHECKS IF PLAYER CANNOT GO UP
        if (!((tilePosAfterMovingUp0 == 0) || (tilePosAfterMovingUp1 == 0))){
            player.collisionUp = true;
        }

        // CHECKS IF PLAYER CANNOT GO DOWN
        if (!((tilePosAfterMovingDown0 == 0) || (tilePosAfterMovingDown1 == 0))){
            player.collisionDown = true;
        }

        // CHECKS IF PLAYER CANNOT GO RIGHT
        if (!((tilePosAfterMovingRight0 == 0) || (tilePosAfterMovingRight1 == 0))){
            player.collisionRight = true;
        }

        // CHECKS IF PLAYER CANNOT GO LEFT
        if (!((tilePosAfterMovingLeft0 == 0) || (tilePosAfterMovingLeft1 == 0))){
            player.collisionLeft = true;
        }

        if(tilePosAfterMovingDown0 == 5){
            gc.tm.loadMap("/map1Layout");
            player.changeYLocation(5*gc.tileSize);
            player.changeCurrentMap(1);
            player.gameCanvas.changeIndexOfMap(1);
        }

        if(tilePosAfterMovingUp0 == 6){
            gc.tm.loadMap("/map0Layout");
            player.changeYLocation(12*gc.tileSize);
            player.changeCurrentMap(0);
            player.gameCanvas.changeIndexOfMap(0);
        } 

        if((tilePosAfterMovingUp0 == 7) && (keyH.spacePressed == true)){
            gc.playingRunningGame(true);
        }

        if((tilePosAfterMovingUp0 == 8) && (keyH.spacePressed == true)){
            gc.playingMiniGame1(true);
        }

        if((tilePosAfterMovingDown0 == 7) && (keyH.spacePressed == true)){
            gc.playingMiniGame2(true);
        }

        if((tilePosAfterMovingDown0 == 8) && (keyH.spacePressed == true)){
            gc.playingCoinGame(true);
        }

    }
}
