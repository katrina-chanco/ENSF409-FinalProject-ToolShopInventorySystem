package Server.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Server Manager functionality
 */
class JSONManagerServerTest {

	/**
	 * Gets the JSONObject and tests its functionality
	 */
	@Test
	void getJsonObject() {
		JSONManagerServer<Object> jsonManagerServer = new JSONManagerServer<>("success");
		assertNotNull(jsonManagerServer.getJsonObject(),"JSON object created successfully");
	}
}