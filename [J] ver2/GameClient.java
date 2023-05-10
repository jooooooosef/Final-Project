import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class GameClient {
    private int ID = -1;
    private int otherID = -1;
    private GameCanvas gc; 
    private DataInputStream in;
    private DataOutputStream out;
    
    public GameClient(){
        System.out.println("---Connecting to Server---");
        connectToMainServer();

        System.out.println(ID);
        System.out.println(otherID);
        GameFrame gf = new GameFrame(ID);
        gf.setUpGUI();
        gc = gf.getGameCanvas();

        setUpReadWriteThreads(in, out);
    }

    private void connectToMainServer(){
        try{
            Socket s = new Socket("localhost", 50000);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            ID = in.readInt();
            otherID = ID == 0 ? 1 : 0;
            
            System.out.println("Succesfully connected to the main server.");
        } catch (IOException ex){
            System.out.println("Failed to connect to the main server.");
            ex.printStackTrace();
        }
    }

    private void setUpReadWriteThreads(DataInputStream in, DataOutputStream out) {
        ReadFromServer rfs = new ReadFromServer(in);
        Thread readThread = new Thread(rfs);
        readThread.start();

        WriteToServer wts = new WriteToServer(out);
        Thread writeThread = new Thread(wts);
        writeThread.start();
    }

    private class ReadFromServer implements Runnable{
        private DataInputStream in;

        public ReadFromServer (DataInputStream in){
            this.in = in;
        }

        @Override 
        public void run(){
            while (true){
                try {
                    readOtherPlayerPosition();
                    Thread.sleep(1);
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }

        public void readOtherPlayerPosition(){
            try{
                int xCoordinate = in.readInt();
                int yCoordinate = in.readInt();

                gc.players[otherID].setLocation(xCoordinate, yCoordinate);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    // all good until this line

    private class WriteToServer implements Runnable{
        private DataOutputStream out;

        public WriteToServer (DataOutputStream out){
            this.out = out;
        }

        @Override
        public void run(){
            while (true) {
                try {
                    sendMyPositionToServer();
                    Thread.sleep(1);
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }

        public void sendMyPositionToServer(){
            try{
                out.writeInt(gc.players[ID].getXCoordinate());
                out.writeInt(gc.players[ID].getYCoordinate());
                
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }
}
