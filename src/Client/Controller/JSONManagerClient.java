package Client.Controller;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import org.json.JSONObject;
import java.util.LinkedList;

/**
 * Provides the JSON Manager for the Client
 */
public class JSONManagerClient{

	/**
	 * Creates the JSON Object used when JSONManagerClient is used
	 */
	private JSONObject jsonObject;

	/**
	 * Constructs the JSONManagerClient for listAllTools()
	 * @param option the command name for the call ("listAllTools")
	 */
	public JSONManagerClient(String option) {
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
	}


	/**
	 * Constructs the JSONManagerClient for searchToolName()
	 * @param option the command name for the call ("searchToolName")
	 * @param name the name of the tool being searched for
	 */
	public JSONManagerClient(String option, String name){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("name", name);
	}

	/**
	 * Constructs the JSONManagerClient for searchToolId()
	 * @param option the command name for the call ("searchToolId")
	 * @param number the number id of the tool being searched for
	 */
	public JSONManagerClient(String option, int number){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("number", number);
	}

	/**
	 * Constructs the JSONManagerClient for decreaseQuantity()
	 * @param option the command name for the call ("decreaseQuantity")
	 * @param number the number id of the tool being searched for
	 * @param amount the amount that is being purchased
	 */
	public JSONManagerClient(String option, int number, int amount){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("number", number);
		jsonObject.put("amount", amount);
	}
	
	public JSONManagerClient(String option, String start, String end){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("startDate", start);
		jsonObject.put("endtDate", end);
	}


	/**
	 * Getter for the JSON Object
	 * @return the JSON Object
	 */
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * Setter for the JSON Object
	 * @param jsonObject the JSON Object being set
	 */
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
}
