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
	 * Constructor for the manager from an object.
	 * @param item
	 */
	public JSONManagerServer(Type item) {
		jsonObject = new JSONObject(item);
	}

	/**
	 * Constructor for the manager from an string.
	 * @param item
	 */
	public JSONManagerServer(String item) {
		jsonObject = new JSONObject(item);
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
