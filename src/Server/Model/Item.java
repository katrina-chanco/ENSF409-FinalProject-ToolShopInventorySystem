package Server.Model;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

import Server.Controller.*;
/**
 * Item class for the shop.
 */
public class Item implements Constants{
	/**
	 * Supplier of item.
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
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	 * @param saleQuantitiy Quantity to sell.
	 * @return true if sale was tendered. False if quantity is unavailable.
	 */
	public boolean addSale(int saleQuantitiy) {
		if(saleQuantitiy > quantity) {
			return false;
		} else {
			quantity -=saleQuantitiy;
			return true;
		}
	}
}
