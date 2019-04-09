package Server.Model;

import Server.Controller.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Inventory management class for the shop.
 */
public class Inventory extends DatabaseManager {

	/**
	 * Getter for the item list.
	 * Required for the JSONManager.
	 * @return itemList
	 */
	public LinkedList<Item> getItemList() {
		return getWholeTable();
	}

	/**
	 * Constructs inventory from databaseManager.
	 * @param database
	 */
	public Inventory(Database database) {
		super(database);
	}

	/**
	 * Gets whole item list from database.
	 * @return LinkedList of items in database.
	 */
	public LinkedList<Item> getWholeTable() {
		LinkedList<Item> returnList = new LinkedList<>();
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM items";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Item item = getItemFromResultSet(resultSet);
					returnList.add(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;

	}

//	/**
//	 * Add an item to the inventory.
//	 * @param item Item to add inventory.
//	 */
//	public void addItem(Item item) {
//		itemList.add(item);
//	}
//
//	/**
//	 * Remove an item from inventory.
//	 * @param item Item to remove.
//	 */
//	public void removeItem(Item item) {
//		itemList.remove(item);
//	}

	/**
	 * Search for item in inventory by name.
	 * @param itemName Name of item to search for.
	 * @return Item object if found else null.
	 */
	public Item searchByName(String itemName) {
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM items WHERE itemName=? LIMIT 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,itemName);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					return getItemFromResultSet(resultSet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Search for item in inventory by id.
	 * @param itemId Id of item to search for.
	 * @return Item object if found else null.
	 */
	public Item searchById(int itemId) {
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM items WHERE itemId=? LIMIT 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1,itemId);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					return getItemFromResultSet(resultSet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns quantity of item.
	 * @param item Item to get quantity of.
	 * @return Quantity of item.
	 */
	public int checkQuantity(Item item) {
		return item.getQuantity();
	}

	/**
	 * Formats a string from the inventory.
	 * @return formatted string.
	 */
	public String toString() {
		LinkedList<Item> itemList = getItemList();
		if (itemList != null ) {
			String returnString = new String();
			for (Item item : itemList) {
				returnString += item.toString();
			}
			return returnString;
		}
		return null;
	}

	/**
	 * Helper method that build an Item object from a resultSet
	 * @param resultSet from a database call to items table
	 * @return item
	 * @throws SQLException
	 */
	private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
		Suppliers suppliers = new Suppliers(database);
		LinkedList<Supplier> supplierLinkedList = suppliers.getSuppliers();
		Item item = new Item(resultSet.getInt("itemId"),
				resultSet.getString("itemName"),
				resultSet.getInt("quantity"),
				resultSet.getDouble("price"));
		item.setSupplier(suppliers.searchSupplierById(resultSet.getInt("supplier"),supplierLinkedList));
		return item;
	}

}
