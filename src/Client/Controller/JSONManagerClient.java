package Client.Controller;

import org.json.JSONObject;

import java.util.LinkedList;

public class JSONManagerClient{

	private JSONObject jsonObject;

	public JSONManagerClient(String option) {
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
	}


	public JSONManagerClient(String option, String name){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("name", name);
	}

	public JSONManagerClient(String option, int number){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("number", number);
	}

	public JSONManagerClient(String option, int number, int amount){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("number", number);
		jsonObject.put("amount", amount);
	}



	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
}
