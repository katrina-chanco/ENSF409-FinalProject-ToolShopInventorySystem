package Client.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONManagerClientTest {

	@Test
	void getJsonObject() {
		JSONManagerClient jsonManagerClient = new JSONManagerClient("command string");
		assertNotNull(jsonManagerClient,"JSON object created successfully");
	}

}