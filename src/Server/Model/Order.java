package Server.Model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import Server.Controller.*;

/**
 * Order managing class.
 */
public class Order implements Constants{
	/**
	 * List of order lines.
	 */
	private ArrayList<OrderLine> orderLines;

	/**
	 * Constructor for order from list of orderLines.
	 * @param orderLines List of orderLines to be used to create the object.
	 */
	public Order(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	/**
	 * Add an order by creating a new orderLine and writing it to a file.
	 * @param item Item to be ordered.
	 * @param quantity Quantity to be ordered.
	 */
	public void addOrder(Item item,int quantity) {
		OrderLine orderLine = new OrderLine(item,quantity);
		orderLines.add(orderLine);
		try {
			item.setQuantity(item.getQuantity()+quantity);
			writeOrderLine(orderLine);
		} catch (java.io.IOException e) {
			System.err.println("File error!");
		}
	}

	/**
	 * Writes the orderLine to the file specified in Constants.
	 * @param orderLine orderLine to write.
	 * @throws java.io.IOException Throws error for file writing.
	 */
	public void writeOrderLine(OrderLine orderLine) throws java.io.IOException {
		String fileOutput = "";
		if(orderLines.indexOf(orderLine) == 0) {
			fileOutput += orderLine.getHeader();
		} else if(orderLines.get(orderLines.size() -2).getDateOrdered().compareTo(orderLines.get(orderLines.size() -1).getDateOrdered()) != 0) {
			fileOutput += orderLine.getHeader();
		}
		fileOutput += orderLine.getBody();

		File yourFile = new File(Constants.orderFileName);
		yourFile.createNewFile(); // if file already exists will do nothing
		FileOutputStream oFile = new FileOutputStream(yourFile, true);
		oFile.write(fileOutput.getBytes());
		oFile.flush();
		oFile.close();
	}
}
