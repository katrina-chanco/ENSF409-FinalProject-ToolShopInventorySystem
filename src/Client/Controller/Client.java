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


    /**
     * Constructs the Client and initializes the sockets
     * @param serverName name of the Server used
     * @param portNumber number of the port
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


    /**
     * Communicates with the Server
     * Utilizes function calls and a JSONManagerClient object for communicating between the client and server side
     * @param jObject  JSONManagerClient object
     * @throws IOException (input/output exception)
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

    /**
     * Provides a command to communicate the login() call between the client and server
     * @param userName username of the client
     * @param password password of the client
     * @return JSONObject containing login information
     * @throws IOException (input/output exception)
     */
    public JSONObject login(String userName, String password) throws IOException {
        JSONManagerClient loginClient = new JSONManagerClient("login", userName, password);
        communicateWithServer(loginClient);
        return finalObj;
    }


    /**
     * Provides a command to the listAllTools() call between client and server
     * @return JSONObject containing list all tools information
     * @throws IOException (input/output exception)
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

    /**
     * Provides a command to the viewOrder() call between client and server
     * @param startDate beginning date for the order call
     * @param endDate end date for the order call
     * @return JSONObject containing the order requested
     * @throws IOException (input/output exception)
     */
    public JSONObject viewOrder(String startDate, String endDate) throws IOException {
    	
    	JSONManagerClient viewOrderClient = new JSONManagerClient("viewOrder", startDate, endDate,"orderList");
    	communicateWithServer(viewOrderClient);
    	System.out.println(finalObj.toString());
    	if(finalObj.getBoolean("success")){
            return finalObj;
        }
        else
            return null;
    	
    }


    /**
     * Provides a command to the searchToolName() call between the client and server
     * @param toolName name of the tool
     * @return JSONObject containing information about the tool requested
     * @throws IOException (input/output exception
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


    /**
     * Provides a command to the searchToolId() call between the client and server
     * @param toolId number id of the tool
     * @return JSONObject containing information about the tool requested
     * @throws IOException (input/output exception)
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


    /**
     * Provides a command to the decreaseQuantity() call between the client and server
     * @param toolId number id of the tool
     * @param amount amount user wants to decrease the tool quantity by
     * @return JSONObject containing information about if the sale was successful or not
     * @throws IOException (input/output exception)
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


    /**
     * Provides a command to the quit() call between te client and server
     * @return JSONObject containing information to quit the program
     * @throws IOException (input/output exception)
     */
    public JSONObject quit() throws IOException{
        JSONManagerClient inventoryClient = new JSONManagerClient("QUIT");
        communicateWithServer(inventoryClient);
        return finalObj;
    }

    /**
     * Request server for a list of account types
     * @return JSONObject containing information about the account type
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
     * Adds user to server
     * @param usernameString username of user
     * @param passwordString password of user
     * @param typeId access level of user
     */
    public JSONObject addUser(String usernameString, String passwordString, int typeId) throws IOException {
        JSONManagerClient inventoryClient = new JSONManagerClient("addUser",usernameString,passwordString,typeId);
        communicateWithServer(inventoryClient);
        return finalObj;
    }


    /**
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
}


