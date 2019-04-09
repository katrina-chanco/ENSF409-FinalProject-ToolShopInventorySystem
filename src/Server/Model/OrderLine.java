package Server.Model;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

import Server.Controller.Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Order line class.
 */
public class OrderLine {
	/**
	 * Item for orderLine.
	 */
	private int itemId;
	/**
	 * Id of orderLine.
	 */
	private int orderId;
	/**
	 * Date of orderLine.
	 */
	private LocalDate dateOrdered;
	/**
	 * Quantity of orderLine.
	 */
	private int quantityOrdered;

	/**
	 * Get the date of orderLine.
	 * @return LocalDate object of orderLine.
	 */
	public LocalDate getDateOrdered() {
		return dateOrdered;
	}

	/**
	 * Constructs the orderLine.
	 * Creates a random 5 digit order ID and sets the date.
	 * @param itemId Item for orderLine.
	 * @param quantity Quantity for orderLine.
	 */
	public OrderLine(int itemId, int quantity, LocalDate dateOrdered) {
		orderId = new Random().nextInt(99999)+10000;
		//DateFormat dateFormat = new SimpleDateFormat("MM dd, YYYY");
		this.dateOrdered = dateOrdered;
		quantityOrdered = quantity;
		//orderDate = dateFormat.format(date);
		this.itemId = itemId;

	}

//	/**
//	 * Generate orderLine header.
//	 * @return String of header.
//	 */
//	public String getHeader() {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, YYYY");
//		return "*********************************************************************************\n" +
//				"Order ID:              "+orderId+"\n" +
//				"Date Ordered:          "+formatter.format(dateOrdered)+"\n\n";
//	}
//
//	/**
//	 * Generate orderLine body.
//	 * @return String of body.
//	 */
//	public String getBody() {
//		return "Item Description:      "+item.getItemName()+"\n" +
//				"Amount Ordered:        "+quantityOrdered+"\n" +
//				"Supplier:              "+item.getSupplier().getCompanyName()+"\n\n";
//	}

}
