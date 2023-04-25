import java.lang.Math.*;

public class CollisionChecker {

    private GameCanvas gc;

    public CollisionChecker(GameCanvas gc){
       this.gc = gc;
    }

    public void checkTile(Player player){

        int playerColIndex = player.getXCoordinate()/48;
        int playerRowIndex = player.getYCoordinate()/48;
        
        int currentTilePos = gc.tm.mapTileNum[playerColIndex][playerRowIndex];
        int currentTilePosForDown = gc.tm.mapTileNum[playerColIndex][playerRowIndex+1];

        //  CHECKS IF PLAYER CANNOT GO UP
        if (currentTilePos == 1 || currentTilePos == 5 || currentTilePos == 6){
            player.collisionUp = true;
        }

        // CHECKS IF PLAYER CANNOT GO DOWN
        if (currentTilePosForDown == 2 || currentTilePosForDown == 7 || currentTilePosForDown == 8){
            player.collisionDown = true;
        }

        // CHECKS IF PLAYER CANNOT GO RIGHT
        if (currentTilePos == 3 || currentTilePos == 5 || currentTilePos == 7){
            player.collisionRight = true;
        }


        // CHECKS IF PLAYER CANNOT GO LEFT
        if (currentTilePos == 4 || currentTilePos == 6 || currentTilePos == 7){
            player.collisionLeft = true;
        }
    }
}
