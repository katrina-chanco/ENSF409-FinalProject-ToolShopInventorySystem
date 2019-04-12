package Server.Model;

import Server.Controller.Database;

/**
 * Abstract database
 * @param <Type>
 */
public abstract class DatabaseManager<Type> {
	/**
	 * Database object
	 */
	protected Database database;

	/**
	 * Assigns the database.
	 * @param database database
	 */
	public DatabaseManager(Database database) {
		this.database = database;
	}

	/**
	 * Abstract generic method to get whole table. To be implanted by every instance of Database.
 	 * @return Generic type
	 */
	public abstract Type getWholeTable();
}
