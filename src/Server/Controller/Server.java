package Server.Controller;

// Nathan Darby - 30033588
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
            aSocket = serverSocket.accept();
            inSocket = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            outSocket = new PrintWriter(aSocket.getOutputStream(), true);

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
        try {
            database.setConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        LinkedList<OrderLine> orderLines = new LinkedList<>();
        Order order = new Order(orderLines,database);
        Inventory inventory = new Inventory(database);
        ServerShop shopServer = new ServerShop(inventory,order,database);
        String input = null;
        boolean flag = true;
        try{
            while(flag) {

                input = inSocket.readLine();
                JSONObject obj = new JSONObject(input);
                String action = obj.getString("command");

                switch (action) {
                    case "listAllTools":
                        JSONManagerServer<Inventory> inventoryJSONManagerServer = new JSONManagerServer<>(shopServer.getInventory(), "success");
                        outSocket.println(inventoryJSONManagerServer.getJsonObject().toString());
                        break;

                    case "QUIT":
                        flag = false;
                        break;


                    case "searchToolName":
                        String nameTool = obj.getString("name");
                        Item itemName = shopServer.getInventory().searchByName(nameTool);

                        if (itemName == null) {
                            JSONManagerServer<Item> nullCheckName = new JSONManagerServer<>("failure");
                            outSocket.println(nullCheckName.getJsonObject().toString());
                        } else {
                            JSONManagerServer<Item> searchToolNameJSONManagerServer = new JSONManagerServer<>(itemName, "success");
                            outSocket.println(searchToolNameJSONManagerServer.getJsonObject().toString());
                        }
                        break;

                    case "searchToolId":
                        int numberTool = obj.getInt("number");
                        Item itemId = shopServer.getInventory().searchById(numberTool);

                        if (itemId == null) {
                            JSONManagerServer<Item> nullCheckId = new JSONManagerServer<>("failure");
                            outSocket.println(nullCheckId.getJsonObject().toString());
                        } else {
                            JSONManagerServer<Item> searchToolIdJSONManagerServer = new JSONManagerServer<>(itemId, "success");
                            outSocket.println(searchToolIdJSONManagerServer.getJsonObject().toString());
                        }
                        break;


                    case "decreaseQuantity":
                        int numberDecrease = obj.getInt("number");
                        int amountDecrease = obj.getInt("amount");

                        Item workingItem = shopServer.getInventory().searchById(numberDecrease);
                        Boolean saleComplete = shopServer.addSale(workingItem, amountDecrease);

                        if(saleComplete == true){
                            JSONManagerServer<Boolean> decreaseQuantityJSONManagerServer = new JSONManagerServer<>("success");
                            outSocket.println(decreaseQuantityJSONManagerServer.getJsonObject().toString());
                        }
                        else {
                            JSONManagerServer<Boolean> nullCheckQuantity = new JSONManagerServer<>("failure");
                            outSocket.println(nullCheckQuantity.getJsonObject().toString());
                        }
                        break;

                    default:
                        break;

                }

            }
        }catch(NullPointerException e){
            System.out.println("Server lost connection to client...");
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
