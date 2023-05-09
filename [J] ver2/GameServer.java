import java.net.*;
import java.io.*;

public class GameServer {
    
    private Socket[] sockets = new Socket[2];
    private ServerSocket serverSocket;
    private int xCoordinate;
    private int yCoordinate;
    private Vector playerPositions = new Vector();

    public GameServer(){
        System.out.println("--- GAME SERVER ---");
        setUpServer();
        acceptClients();
    }

    public void setUpServer(){
        try{
            serverSocket = new ServerSocket(50000);
            System.out.println("Successfully created main server!");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void acceptClients(){
        int clientNum = 0;
        while (clientNum < 2){
            try{
                sockets[clientNum] = serverSocket.accept();
                DataInputStream in = new DataInputStream(sockets[clientNum].getInputStream());
                DataOutputStream out = new DataOutputStream(sockets[clientNum].getOutputStream());

                out.writeInt(clientNum);
                setUpReadWriteStreams(in, out, clientNum);

                System.out.printf("Player %d has connected.\n", clientNum);
                clientNum++;
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public void setUpReadWriteStreams(DataInputStream in, DataOutputStream out, int ID){
        ReadFromClient rfc = new ReadFromClient(in, ID);
        Thread readThread = new Thread(rfc);
        readThread.start();

        WriteToClient wtc = new WriteToClient(out, ID);
        Thread writeThread = new Thread(wtc);
        writeThread.start();
    }

    private class ReadFromClient implements Runnable{

        private DataInputStream in;
        private int ID;
        private int otherID;

        public ReadFromClient(DataInputStream in, int ID){
            this.in = in;
            this.ID = ID;
            otherID = ID == 0 ? 1 : 0;
        }

        @Override
        public void run(){
            while(true){
                readOtherPlayerPosition();
            }
        }

        public void readOtherPlayerPosition(){
            try{
                playerPositions.x = in.readInt();
                playerPositions.y = in.readInt();
                System.out.println(playerPositions.x);
                System.out.println(playerPositions.y);
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private class WriteToClient implements Runnable{

        private DataOutputStream out;
        private int ID;
        private int otherID;

        public WriteToClient(DataOutputStream out, int ID){
            this.out = out;
            this.ID = ID;
            this.otherID = otherID;
        }

        @Override 
        public void run(){
            while (true){
                writeOtherPlayerPosition();
            }
        }

        public void writeOtherPlayerPosition(){
            try{
                out.writeInt(playerPositions.x);
                out.writeInt(playerPositions.y);
                
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new GameServer();
    }
}
