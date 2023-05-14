import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer{

    private static ServerSocket ss;
    private static ArrayList<Socket> sockets;
    private static DataOutputStream outClient0, outClient1;
    private static DataInputStream inClient0, inClient1;
    public static void main(String[] args){

        sockets = new ArrayList<>();
        ss = null;
        outClient0 = outClient1 = null;
        inClient0 = inClient1 = null;

        try{
            ss = new ServerSocket(50000);
            sockets.add(ss.accept());
            outClient0 = new DataOutputStream(sockets.get(0).getOutputStream());
            inClient0 = new DataInputStream(sockets.get(0).getInputStream());
            outClient0.writeInt(0);

            sockets.add(ss.accept());
            outClient1 = new DataOutputStream(sockets.get(1).getOutputStream());
            inClient1 = new DataInputStream(sockets.get(1).getInputStream());
            outClient1.writeInt(1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        PlayerPositionManager ppm = new PlayerPositionManager(inClient0, inClient1, outClient0, outClient1);
        Thread ppmThread = new Thread(ppm);
        ppmThread.start();
    }
}