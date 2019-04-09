package Server.Model;

import Server.Controller.Database;

import java.util.LinkedList;

public abstract class DatabaseManager<Type> {
	protected Database database;

	public DatabaseManager(Database database) {
		this.database = database;
	}

	/**
	 * Abstract generic method to get whole table. To be implanted by every instance of Database.
 	 * @return Generic type
	 */
	public abstract Type getWholeTable();
}
