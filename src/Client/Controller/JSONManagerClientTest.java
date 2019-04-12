package Client.Controller;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the JSONManagerClient functionality
 */
class JSONManagerClientTest {

	/**
	 * Gets the JSONObject and tests its functionality
	 */
	@Test
	void getJsonObject() {
		JSONManagerClient jsonManagerClient = new JSONManagerClient("command string");
		assertNotNull(jsonManagerClient,"JSON object created successfully");
	}

}