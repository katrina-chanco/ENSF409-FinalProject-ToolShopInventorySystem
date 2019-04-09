package Server.Model;
import Server.Controller.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Item class for the shop.
 */
public class Item implements Constants{
	/**
	 * Supplier ID of item.
	 */
	private Supplier supplier;
	/**
	 * Id of item.
	 */
	private int id;
	/**
	 * Name of item.
	 */
	private String itemName;
	/**
	 * Quantity of item.
	 */
	private int quantity;
	/**
	 * Price of item.
	 */
	private double price;

	/**
	 * Constructor for item.
	 * @param id Id of item.
	 * @param name Name of item.
	 * @param quantity Quantity of item.
	 * @param price Price of item.
	 */
	public Item(int id, String name, int quantity, double price) {
		this.id = id;
		this.itemName = name;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Getter for price.
	 * @return Item price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Getter for supplier.
	 * @return Item supplier.
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Setter for supplier.
	 * @param supplier Item supplier.
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * Id getter.
	 * @return Item id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Item Name getter.
	 * @return itemName.
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Quantity getter.
	 * @return Quantity of item.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Quantity setter.
	 * @param quantity Quantity of item.
	 */
	public void setQuantity(int quantity, Database database) {
		this.quantity = quantity;
		try {
			Connection connection = database.getConnection();
			String sqlString = "UPDATE `toolShop`.`items` SET `quantity`=? WHERE  `itemId`=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1,this.quantity);
			preparedStatement.setInt(2,id);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses a string of the item.
	 * @return Parsed item string.
	 */
	public String toString() {
		return "ID: "+id+" NAME: "+itemName+" QUANTITY: "+quantity+" PRICE: $"+price+" SOLD BY: "+supplier.getCompanyName()+"\n";
	}

	/**
	 * Add a sale if quantity is available.
	 * @param saleQuantity Quantity to sell.
	 * @return true if sale was tendered. False if quantity is unavailable.
	 */
	public boolean addSale(int saleQuantity, Database database) {
		if(saleQuantity > quantity) {
			return false;
		} else {
			setQuantity(quantity-saleQuantity, database);
			return true;
		}
	}
}
