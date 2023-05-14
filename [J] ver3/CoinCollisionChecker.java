import java.lang.Math.*;

public class CoinCollisionChecker {

    private Player player;

    public CoinCollisionChecker(Player player){
       this.player = player;
    }

    public void checkPlace(){

        int playerX = (player.getXCoordinate());
        int playerY =  (player.getYCoordinate());

        //  CHECKS IF PLAYER CANNOT GO UP
        if (!(playerY >= 228)){
            player.collisionUp = true;
        }

        // CHECKS IF PLAYER CANNOT GO DOWN
        if (!(playerY+100 <= 592)){ 
            player.collisionDown = true;
        }

        // CHECKS IF PLAYER CANNOT GO RIGHT
        if (!(playerX+100 <= 960)){
            player.collisionRight = true;
        }

        // CHECKS IF PLAYER CANNOT GO LEFT
        if (!(playerX >= 0)){
            player.collisionLeft = true;
        }
    }
}
