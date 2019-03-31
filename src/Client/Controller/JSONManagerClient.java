package Server.Controller;

import Server.Model.Inventory;
import org.json.JSONObject;

/**
 * JSON manager class used to communicate with server.
 */
public class JSONManagerClient {
	/**
	 * JSON object (package we imported) used to create and decode the json.
	 */
	private JSONObject jsonObject;

	/**
	 * Constructor for the manager.
	 */
	public JSONManagerClient(String serverAction) {
		jsonObject = new JSONObject();
		jsonObject.put("action",serverAction);
	}

	public void addJSON(String json){

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
