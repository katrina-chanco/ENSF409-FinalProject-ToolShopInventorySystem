package Server.Controller;
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
}
