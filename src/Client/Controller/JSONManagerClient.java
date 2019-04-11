package Client.Controller;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	 * Constructs the JSONManagerClient for login()
	 * @param option the command name for the call ("listAllTools")
	 */
	public JSONManagerClient(String option, String userName, String password) {
		jsonObject = new JSONObject();
		String passwordString = generateMD5(password.toString());
		jsonObject.put("command", option);
		jsonObject.put("userName", userName);
		jsonObject.put("password", passwordString);
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
	
	public JSONManagerClient(String option, String start, String end,String garbage){
		jsonObject = new JSONObject();
		jsonObject.put("command", option);
		jsonObject.put("startDate", start);
		jsonObject.put("endDate", end);
	}


	/**
	 * Constructor for JSONManagerClient for addUser()
	 * @param option command
	 * @param usernameString username
	 * @param password password
	 * @param typeId access level
	 */
	public JSONManagerClient(String option, String usernameString, String password, int typeId) {
		jsonObject = new JSONObject();
		String passwordString = generateMD5(password);
		jsonObject.put("command", option);
		jsonObject.put("userName", usernameString);
		jsonObject.put("password", passwordString);
		jsonObject.put("level",typeId);
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

	/**
	 * generates a md5 hash from a string
	 * @param string input
	 * @return output md5
	 */
	private String generateMD5(String string) {
		//https://www.geeksforgeeks.org/md5-hash-in-java/
		try {
			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// digest() method is called to calculate message digest
			//  of an input digest() return array of byte
			byte[] messageDigest = md.digest(string.getBytes());
			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			string = hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return string;
	}

}
