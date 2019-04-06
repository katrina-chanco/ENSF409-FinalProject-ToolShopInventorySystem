package Server.Controller;

import org.json.JSONObject;

/**
 * JSON manager class used to communicate with client.
 */
public class JSONManagerServer<Type> {
	/**
	 * JSON object (package we imported) used to create and decode the json.
	 */
	private JSONObject jsonObject;

	/**
	 * Constructor for the manager.
	 * @param item the type for the JSON Manager Server object
	 */
	public JSONManagerServer(Type item) {
		jsonObject = new JSONObject(item);
	}


	/**
	 * Constructs the JSONManagerServer for the null return
	 */
	public JSONManagerServer(){
		jsonObject = new JSONObject();
		jsonObject.isNull("nullType");
	}


	/**
	 * Constructs the JSONManagerServer for the quantity change responses
	 * @param flag the reference on which message to send
	 */
	public JSONManagerServer(String flag){
		jsonObject = new JSONObject();

		if(flag == "success"){
			jsonObject.put("message", "Sale Successful. Quantity Changed.");
		}
		else if (flag == "failure"){
			jsonObject.put("message", "Sale NOT Successful.");
		}
	}


	/**
	 * Getter for the JSON object
	 * @return
	 */
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * Setter for the JSON Object
	 * @param jsonObject
	 */
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
}
