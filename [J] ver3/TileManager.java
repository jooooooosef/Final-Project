import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class TileManager {
    
    GameCanvas gc;
    Tile[] tile;
    int[][] mapTileNum;
    
    public TileManager(GameCanvas gc){
        this.gc = gc;

        tile = new Tile[16];
        mapTileNum = new int [gc.numScreenTileCol][gc.numScreenTileRow];

        this.getTileImage();
        this.loadMap("/map0Layout");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].tileImage = ImageIO.read(getClass().getResourceAsStream("images/grasstile.png"));

            tile[1] = new Tile();
            tile[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/sh0.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].tileImage = ImageIO.read(getClass().getResourceAsStream("images/road32.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].tileImage = ImageIO.read(getClass().getResourceAsStream("images/grasstile.png"));

            tile[4] = new Tile();
            tile[4].tileImage = ImageIO.read(getClass().getResourceAsStream("images/bridgeout32.png"));

            // GO TO MAP1
            tile[5] = new Tile();
            tile[5].tileImage = ImageIO.read(getClass().getResource("images/grasstile.png"));

            // GO TO MAP0
            tile[6] = new Tile();
            tile[6].tileImage = ImageIO.read(getClass().getResource("images/grasstile.png"));

            // GO TO A MINIGAME
            tile[7] = new Tile();
            tile[7].tileImage = ImageIO.read(getClass().getResource("/sh2.png"));
            tile[8] = new Tile();
            tile[8].tileImage = ImageIO.read(getClass().getResource("/sh2.png"));

        } catch(IOException e){
            System.out.println("Image file(s) not found in getTileImage function in TileManager class.");
        }
    }

    public void loadMap(String mapLayout){

        try{
            InputStream is = getClass().getResourceAsStream(mapLayout + ".txt");
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
