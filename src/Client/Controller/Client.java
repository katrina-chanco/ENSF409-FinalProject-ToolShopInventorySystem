package Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

public class Client {

    private Socket aSocket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;





    public Client(String serverName, int portNumber) {
        try{

            aSocket = new Socket (serverName, portNumber);
            inSocket = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            outSocket = new PrintWriter(aSocket.getOutputStream(), true);

        }catch(IOException e){
            System.err.println("Error in client" + e.getStackTrace());
        }

    }

    private JSONObject finalObj;

    public void communicateWithServer(JSONManagerClient jObject) throws IOException{


        String response = "";
        boolean flag = true;
            try{
                while(flag){

                        outSocket.println(jObject.getJsonObject().toString());
                        response = inSocket.readLine();

                        if(response == null){
                            finalObj = null;
                        }
                        else{
                            finalObj = new JSONObject(response);
                        }

                        flag = false;
                }
            } catch (NullPointerException e) {
                System.out.println("Client lost connection to server...");
            }
            catch(Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    inSocket.close();
                    outSocket.close();
                }catch (IOException e){
                    System.err.println("Closing error: " + e.getMessage());
                }
            }


    }

    public static void main(String[] args) throws IOException{

        Client client = new Client("localhost", 1234);
        //JSONObject obj = client.listAllTools();
        //System.out.println(obj.toString());

        //JSONObject obj2 = client.quit();
        //System.out.println(obj2.toString());


        //Orf Dappers
        //JSONObject obj3 = client.searchToolName("Slam Hammer");
        //System.out.println(obj3.toString());

        //1000
        //JSONObject obj4 = client.searchToolId(8001);
        //System.out.println(obj4.toString());


        JSONObject obj5 = client.decreaseQuantity(1000, 2);
        System.out.println(obj5.toString());

    }






    public JSONObject listAllTools() throws IOException{

        JSONManagerClient inventoryClient = new JSONManagerClient("listAllTools");
        communicateWithServer(inventoryClient);
        return finalObj;
    }


    public JSONObject quit() throws IOException{
        JSONManagerClient inventoryClient = new JSONManagerClient("QUIT");
        communicateWithServer(inventoryClient);
        return finalObj;
    }


    public JSONObject searchToolName(String toolName) throws IOException{

        JSONManagerClient searchToolNameClient = new JSONManagerClient("searchToolName", toolName);
        communicateWithServer(searchToolNameClient);

        if(finalObj == null){
            System.err.println("Item Name not in Inventory");
            return null;
        }
        else{
            return finalObj;
        }
    }


    public JSONObject searchToolId (int toolId) throws IOException{

        JSONManagerClient searchToolIdClient = new JSONManagerClient("searchToolId", toolId);
        communicateWithServer(searchToolIdClient);

        if(finalObj == null){
            System.err.println("Item ID not in Inventory");
            return null;
        }
        else{
            return finalObj;
        }
    }


    public JSONObject decreaseQuantity(int toolId, int amount) throws IOException {

        JSONManagerClient decreaseQuantityClient = new JSONManagerClient("decreaseQuantity", toolId, amount);
        communicateWithServer(decreaseQuantityClient);

        if(finalObj == null){
            System.err.println("Could not decrease Quantity");
            return null;
        }
        else{
            return finalObj;
        }
    }
}


