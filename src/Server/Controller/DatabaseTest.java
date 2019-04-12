package Server.Controller;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests the Database
 */
class DatabaseTest {

	/**
	 * Sets the connection to the database
	 * @throws SQLException (SQL Exception)
	 */
	@org.junit.jupiter.api.Test
	void setConnection() throws SQLException {
		Database database = new Database();
		database.setConnection();
		Connection con = database.getConnection();
		assertTrue(con.isValid(0),"Connection is valid to database");
		con.close();
	}

	/**
	 * Closes the connection to the database
	 * @throws SQLException (SQL Exception)
	 */
	@org.junit.jupiter.api.Test
	void closeConnection() throws SQLException {
		Database database = new Database();
		database.setConnection();
		Connection con = database.getConnection();
		con.close();
		assertEquals(con.isClosed(),true,"Connection closed successfully");
	}

	/**
	 * Gets the connection to the database
	 * @throws SQLException (SQL Exception)
	 */
	@org.junit.jupiter.api.Test
	void getConnection() throws SQLException {
		Database database = new Database();
		database.setConnection();
		Connection con = database.getConnection();
		assertNotNull(con,"Connection was found");
		con.close();
	}
}