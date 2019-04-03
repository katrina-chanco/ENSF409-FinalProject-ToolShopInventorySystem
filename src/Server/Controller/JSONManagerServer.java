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
	 * @param item
	 */
	public JSONManagerServer(Type item) {
		jsonObject = new JSONObject(item);
	}

	//THIS SECTION IS STILL UNDER DEVELOPMENT
	public JSONManagerServer(){
		jsonObject = new JSONObject();
		jsonObject.isNull("nullType");
	}

	//THIS SECTION IS STILL UNDER DEVELOPMENT
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
