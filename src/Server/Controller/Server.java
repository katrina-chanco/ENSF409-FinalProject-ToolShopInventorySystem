package Server.Controller;

// Nathan Darby - 30033588x/
// Katrina Chanco - 30037408
// Evan Krul - 30043180


import Server.Model.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides the Server side for the ToolShop
 */
public class Server {

    /**
     * Creates the server socket
     */
    private ServerSocket serverSocket;

    /**
     * Creates a socket
     */
    private Socket aSocket;

    /**
     * Creates the socket that reads into the server
     */
    private BufferedReader inSocket;

    /**
     * Creates the socket that writes out of the server
     */
    private PrintWriter outSocket;

    /**
     * Creates an Executor object that utilizes thread pools
     */
    private ExecutorService pool;


    /**
     * Constructs the Server
     * @param portNumber the port number for the client-server application
     */
    public Server(int portNumber) {
        try{

            serverSocket = new ServerSocket(portNumber);
            pool = Executors.newCachedThreadPool();
            System.out.println("Server is now running...");

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Communicates with the Client
     * @throws IOException (input/output exception)
     */
    public void communicateWithClient() throws IOException{
        Database database = new Database();
        try{
            while (true) {
                aSocket = serverSocket.accept();
                inSocket = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                outSocket = new PrintWriter(aSocket.getOutputStream(), true);

                try {
                    database.setConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ArrayList<Item> items = new ArrayList<>();
                ArrayList<Supplier> suppliers = new ArrayList<>();
                LinkedList<OrderLine> orderLines = new LinkedList<>();
                Order order = new Order(orderLines, database);
                Inventory inventory = new Inventory(database);
                ServerShop shopServer = new ServerShop(inventory, order, database,aSocket);

                pool.execute(shopServer);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            System.out.println("Server stopped running...");
            try{
                inSocket.close();
                outSocket.close();
                serverSocket.close();
                pool.shutdown();
                database.closeConnection();
            }catch (IOException e){
                System.err.println("Closing error: " + e.getMessage());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *  Provides the means to test the functionality of the server
     *
     * @param args the command line arguments, not used in this program
     * @throws IOException (input/output exception)
     */
    public static void main(String[] args) throws IOException{

        Server server = new Server(8099);
        server.communicateWithClient();
    }
}
