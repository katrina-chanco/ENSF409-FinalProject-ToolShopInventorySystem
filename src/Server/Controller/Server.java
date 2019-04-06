
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

package Server.Controller;

import Server.Model.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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

        ArrayList<Item> items = new ArrayList<>();
        Inventory inventory = new Inventory(items);
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        Order order = new Order(orderLines);
        ShopServer shopServer = new ShopServer(inventory,suppliers,order);
        shopServer.readSupplierFile();
        shopServer.readItemFile();

        
        JSONManagerServer nullCheck = new JSONManagerServer();

        String input = null;
        boolean flag = true;
        try{
            while(flag) {

                input = inSocket.readLine();
                JSONObject obj = new JSONObject(input);
                String action = obj.getString("command");

                switch (action) {
                    case "listAllTools":
                        JSONManagerServer<Inventory> inventoryJSONManagerServer = new JSONManagerServer<>(shopServer.getInventory());
                        outSocket.println(inventoryJSONManagerServer.getJsonObject().toString());
                        break;

                    case "QUIT":
                        flag = false;
                        break;

                    case "searchToolName":
                        String nameTool = obj.getString("name");
                        
                        if(shopServer.getInventory().searchByName(nameTool) == null){
                            outSocket.println(nullCheck.getJsonObject().toString());
                        }
                        else{
                        	JSONManagerServer<Item> searchToolNameJSONManagerServer = new JSONManagerServer<>(shopServer.getInventory().searchByName(nameTool));
                            outSocket.println(searchToolNameJSONManagerServer.getJsonObject().toString());
                        }
                        break;


                    case "searchToolId":
                        int numberTool = obj.getInt("number");
                        
                        if(shopServer.getInventory().searchById(numberTool) == null){

                            outSocket.println(nullCheck.getJsonObject().toString());
                        }
                        else{
                        	JSONManagerServer<Item> searchToolIdJSONManagerServer = new JSONManagerServer<>(shopServer.getInventory().searchById(numberTool));
                            outSocket.println(searchToolIdJSONManagerServer.getJsonObject().toString());
                        }
                        break;



                    case "decreaseQuantity":
                        int numberDecrease = obj.getInt("number");
                        Item workingItem = shopServer.getInventory().searchById(numberDecrease);
                        int amountDecrease = obj.getInt("amount");

                        if((shopServer.addSale(workingItem, amountDecrease)) == true){
                            JSONManagerServer messageDecrease = new JSONManagerServer("success");
                            outSocket.println(messageDecrease.getJsonObject().toString());
                        }
                        else if ((shopServer.addSale(workingItem, amountDecrease)) == false){
                            JSONManagerServer messageDecrease = new JSONManagerServer("failure");
                            outSocket.print(messageDecrease.getJsonObject().toString());
                        }
                        else{
                            outSocket.println(nullCheck.getJsonObject().toString());
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
            }catch (IOException e){
                System.err.println("Closing error: " + e.getMessage());

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

        Server server = new Server(1234);
        server.communicateWithClient();
    }
}
