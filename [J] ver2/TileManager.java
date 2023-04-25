import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class TileManager {
    
    GameCanvas gc;
    Tile[] tile;
    int mapTileNum[][];
    
    public TileManager(GameCanvas gc){
        this.gc = gc;

        tile = new Tile[16];
        mapTileNum = new int [gc.numScreenTileCol][gc.numScreenTileRow];

        this.getTileImage();
        this.loadMap();
    }

    public void getTileImage(){
        /* 0 : allowed to move in any direction
         * 1 : no up movement
         * 2 : no down movement
         * 3 : no right movement
         * 4 : no left movement
         * 5 : no up & right movement
         * 6 : no up & left movement
         * 7 : no down & right movement
         * 8 : no down & left movement
        */ 
        try{
            tile[0] = new Tile();
            tile[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/g.png"));

            tile[1] = new Tile();
            tile[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/blk.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/blk.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].tileImage = ImageIO.read(getClass().getResourceAsStream("/blk.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].tileImage = ImageIO.read(getClass().getResourceAsStream("/blk.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].tileImage = ImageIO.read(getClass().getResourceAsStream("/b.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].tileImage = ImageIO.read(getClass().getResourceAsStream("/b.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].tileImage = ImageIO.read(getClass().getResourceAsStream("/b.png"));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].tileImage = ImageIO.read(getClass().getResourceAsStream("/b.png"));
            tile[8].collision = true;



        } catch(IOException e){
            System.out.println("Image file not found in getTileImage function in TileManager class.");
        }
    }

    public void loadMap(){

        try{
            InputStream is = getClass().getResourceAsStream("/map0Layout.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gc.numScreenTileCol && row < gc.numScreenTileRow){
                String line = br.readLine();

                while(col < gc.numScreenTileCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gc.numScreenTileCol){
                    col = 0;
                    row ++;
                }
            }
            br.close();
            
        }catch(Exception e){
            System.out.println("File not found in loadMap function in TileManager class.");
        }
    }

    public void draw(Graphics2D g2d){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gc.numScreenTileCol && row < gc.numScreenTileRow){

            int tileNum = mapTileNum[col][row];

            g2d.drawImage(tile[tileNum].tileImage, x, y, gc.tileSize, gc.tileSize, null);
            col++;
            x += gc.tileSize;

            if(col == gc.numScreenTileCol){
                col = 0;
                x = 0;
                row ++;
                y += gc.tileSize;
            }
        }
    }

}
