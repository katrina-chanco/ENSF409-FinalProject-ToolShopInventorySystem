package Server.Controller;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

<<<<<<< HEAD
=======

>>>>>>> bdef2a6919cf7641c4a9150e7fdfdd87c8fd0e0f

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
	public JSONManagerServer(Type item, String flag) {
		jsonObject = new JSONObject(item);

		switch(flag){
			case "success":
				jsonObject.put("success", true);
				jsonObject.put("failure", false);
				break;
			case "failure":
				jsonObject.put("failure", true);
				jsonObject.put("success", false);
				break;
		}

	}

	public JSONManagerServer(String flag) {
		jsonObject = new JSONObject();

		switch(flag){
			case "success":
				jsonObject.put("success", true);
				jsonObject.put("failure", false);
				break;
			case "failure":
				jsonObject.put("failure", true);
				jsonObject.put("success", false);
				break;
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
