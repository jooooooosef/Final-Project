import java.io.*;
import java.net.*;

public class PlayerPositionManager implements Runnable{

    // Fields for everyone
    
    
    // Fields for Server
    private DataInputStream input0;
    private DataInputStream input1;
    private DataOutputStream output0;
    private DataOutputStream output1;
    private boolean fromServer = false;

    // Fields for Client
    private int playerID;
    private DataInputStream clientInput;
    private DataOutputStream clientOutput;
    private GameCanvas gc;
    private int otherID;

    // Constructor for Server
    public PlayerPositionManager(DataInputStream input0, DataInputStream input1, DataOutputStream output0, DataOutputStream output1){
        this.input0 = input0;
        this.input1 = input1;
        this.output0 = output0;
        this.output1 = output1;
        fromServer = true;
    }

    // Constructor for Client
    public PlayerPositionManager(int ID, DataInputStream ci, DataOutputStream co, GameCanvas gc){
        playerID = ID;
        otherID = ID == 0 ? 1 : 0;
        clientInput = ci;
        clientOutput = co;
        this.gc = gc;
    }

    @Override
    public void run(){
        while (true){
            if (fromServer){
                try{
                    int xCoordinateOfPlayer0, yCoordinateOfPlayer0, xCoordinateOfPlayer1, yCoordinateOfPlayer1;

                    // Read all data being sent to the server
                    xCoordinateOfPlayer0 = input0.readInt();
                    yCoordinateOfPlayer0 = input0.readInt();
                    xCoordinateOfPlayer1 = input1.readInt();
                    yCoordinateOfPlayer1 = input1.readInt();

                    // Send approproate data to client 0 and client 1
                    output0.writeInt(xCoordinateOfPlayer1);
                    output0.writeInt(yCoordinateOfPlayer1);
                    output1.writeInt(xCoordinateOfPlayer0);
                    output1.writeInt(yCoordinateOfPlayer0);

                } catch(IOException ex){
                    ex.printStackTrace();
                }

            } else {
                
                int xCoordinateOtherPlayer = 0;
                int yCoordinateOtherPlayer = 0;

                try{
                    clientOutput.writeInt(gc.players[playerID].getXCoordinate());
                    clientOutput.writeInt(gc.players[playerID].getYCoordinate());
                    xCoordinateOtherPlayer = clientInput.readInt();
                    yCoordinateOtherPlayer = clientInput.readInt();
                } catch (Exception ex){
                    ex.printStackTrace();
                }

                gc.players[otherID].setLocation(xCoordinateOtherPlayer, yCoordinateOtherPlayer);
            }
        }
    }
}
