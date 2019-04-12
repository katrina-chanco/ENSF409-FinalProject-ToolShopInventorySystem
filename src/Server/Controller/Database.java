package Server.Controller;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that connects to the database based off constants data.
 */
public class Database {
	/**
	 * Connection object for database
	 */
	private Connection connection;

	/**
	 * Constructor; does nothing
	 */
	public Database() {

	}

	/**
	 * Connects to database with information from Constants.
	 * @throws SQLException (SQL exception)
	 */
	public void setConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://"+Constants.databaseAddress+"/"+Constants.databaseName+"?useLegacyDatetimeCode=false&serverTimezone=GMT",Constants.databaseUserName,Constants.databasePassword);
	}


	/**
	 * Closes the connection to the database
	 * @throws SQLException (SQL Exception)
	 */
	public void closeConnection() throws SQLException {
		connection.close();
	}

	/**
	 * getter for connection
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
}
