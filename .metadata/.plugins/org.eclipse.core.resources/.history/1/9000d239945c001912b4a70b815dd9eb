package Client.Controller;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

/**
 * Provides the Client side for the ToolShop
 */
public class Client {

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
     * Creates the JSON Object that gets called by the GUI
     */
    private JSONObject finalObj;



    /*
     * Constructor for the client, initializes the sockets 
     */
    public Client(String serverName, int portNumber) {
        try{

            aSocket = new Socket (serverName, portNumber);
            inSocket = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            outSocket = new PrintWriter(aSocket.getOutputStream(), true);

        }catch(IOException e){
            System.err.println("Error in client" + e.getStackTrace());
        }

    }

    
    /*
     * Communicates with the server, receives a JSONManagerClient object and sends it to the server
     * The response from the server is received from the inSocket and returned to the calling function
     */
    public void communicateWithServer(JSONManagerClient jObject) throws IOException{


        String response = "";
        boolean flag = true;
            try{
                while(flag){
                	outSocket.println(jObject.getJsonObject().toString());
                	response = inSocket.readLine();
                	finalObj = new JSONObject(response);
                    flag = false;
                }
            } catch (NullPointerException e) {
                System.out.println("Client lost connection to server...");
            }
            catch(Exception e) {
                e.printStackTrace();
            } 

    }

    public JSONObject login(String userName, String password) throws IOException {
        JSONManagerClient inventoryClient = new JSONManagerClient("login",userName,password);
        communicateWithServer(inventoryClient);
        return finalObj;
    }

    /*
     * sends a command to list all tools to the socket
     */
    public JSONObject listAllTools() throws IOException{

        JSONManagerClient inventoryClient = new JSONManagerClient("listAllTools");
        communicateWithServer(inventoryClient);

        if(finalObj.getBoolean("success")){
            return finalObj;
        }
        else
            return null;
    }
    
    public JSONObject viewOrder(String startDate, String endDate) throws IOException {
    	
    	JSONManagerClient viewOrderClient = new JSONManagerClient("viewOrder", startDate, endDate,"orderList");
    	communicateWithServer(viewOrderClient);
    	
    	if(finalObj.getBoolean("success")){
            return finalObj;
        }
        else
            return null;
    	
    }

    /*
     * Sends a quit command to the socket, used to show the client has ended
     */
    public JSONObject quit() throws IOException{
        JSONManagerClient inventoryClient = new JSONManagerClient("QUIT");
        communicateWithServer(inventoryClient);
        return finalObj;
    }


    /*
     * sends a search command to the socket to search by tool name
     */
    public JSONObject searchToolName(String toolName) throws IOException{

        JSONManagerClient searchToolNameClient = new JSONManagerClient("searchToolName", toolName);
        communicateWithServer(searchToolNameClient);

        if(finalObj.getBoolean("success")){
            return finalObj;
        }
        else
            return null;
        }



    /*
     * Sends a search command to the socket to search for a tool by id number
     */
    public JSONObject searchToolId (int toolId) throws IOException{

        JSONManagerClient searchToolIdClient = new JSONManagerClient("searchToolId", toolId);
        communicateWithServer(searchToolIdClient);

        if(finalObj.getBoolean("success")){
            return finalObj;
        }
        else{
            return null;
        }
    }


    /*
     * Sends a command to the socket to decrease the quantity of a tool(passed as toolId) by a certain amount(passed as amount)
     */
    public JSONObject decreaseQuantity(int toolId, int amount) throws IOException {

        JSONManagerClient decreaseQuantityClient = new JSONManagerClient("decreaseQuantity", toolId, amount);
        communicateWithServer(decreaseQuantityClient);

        if(finalObj.getBoolean("success")){
            return finalObj;
        }
        else{
            return null;
        }
    }
    
   
    /*
     * Closes the sockets
     */
    public void close() {
    	try {
			inSocket.close();
			outSocket.close();
		} catch (IOException e) {
			System.err.println("Unable to close sockets");
		}
    	
    }

    /**
     * request server for a list of account types
     * @return json object
     */
    public JSONObject getAccountTypes() throws IOException {
        JSONManagerClient accountTypes = new JSONManagerClient("getAccountsTypes");
        communicateWithServer(accountTypes);

        if(finalObj.getBoolean("success")){
            return finalObj;
        }
        else
            return null;
    }

    /**
     * adds user to server
     * @param usernameString username
     * @param passwordString password
     * @param typeId access level
     */
    public JSONObject addUser(String usernameString, String passwordString, int typeId) throws IOException {
        JSONManagerClient inventoryClient = new JSONManagerClient("addUser",usernameString,passwordString,typeId);
        communicateWithServer(inventoryClient);
        return finalObj;
    }
}


