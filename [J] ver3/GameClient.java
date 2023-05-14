import java.net.*;
import java.io.*;

public class GameClient {

    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private int ID;

    public GameClient(){
        connectToServer();
        setUpGUI();
    }

    private void connectToServer(){
        try {
            s = new Socket("localhost", 50000);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            ID = in.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpGUI(){
        GameFrame gf = new GameFrame(ID);
        gf.setUpGUI();
        GameCanvas gc = gf.getGameCanvas(
        );

        PlayerPositionManager ppm = new PlayerPositionManager(ID, in, out, gc);
        Thread ppmThread = new Thread(ppm);
        ppmThread.start();
    }

}