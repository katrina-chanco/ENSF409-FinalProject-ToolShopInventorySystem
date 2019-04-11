package Server.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONManagerServerTest {

	@Test
	void getJsonObject() {
		JSONManagerServer<Object> jsonManagerServer = new JSONManagerServer<>("success");
		assertNotNull(jsonManagerServer.getJsonObject(),"JSON object created successfully");
	}
}