package Server.Controller;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180


/**
 * Constants interface.
 */
public interface Constants {
	/**
	 * Minimum threshold for when to order more stock.
	 */
	int orderMinThreshhold = 40;
	/**
	 * Max quantity to order when fulfilling the order.
	 */
	int orderMaxThreshhold = 50;
	/**
	 * File to write orders to.
	 */
	String orderFileName = "orders.txt";
	/**
	 * File that contains items.
	 */
	String itemFileName = "items.txt";
	/**
	 * File that contains suppliers.
	 */
	String supplierFileName = "suppliers.txt";
	/**
	 * Address of database server
	 */
	String databaseAddress = "35.224.21.116:3306";
	/**
	 * DatabaseManager name
	 */
	String databaseName = "toolShop";
	/**
	 * DatabaseManager username
	 */
	String databaseUserName = "user";
	/**
	 * DatabaseManager password
	 * (yes I know this is insecure but shhh)
	 */
	String databasePassword = "password";
}
