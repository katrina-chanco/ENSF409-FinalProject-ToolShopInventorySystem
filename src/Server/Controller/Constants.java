package Server.Controller;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

<<<<<<< HEAD
=======

>>>>>>> bdef2a6919cf7641c4a9150e7fdfdd87c8fd0e0f
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
