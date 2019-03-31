package Server.Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Server.Model.*;

/**
 * Main implementing class for the shop.
 */
public class ServerShop implements Constants {
	/**
	 * Inventory of the shop.
	 */
	private Inventory inventory;
	/**
	 * Suppliers of the shop.
	 */
	private ArrayList<Supplier> suppliers;
	/**
	 * Order manager for the shop.
	 */
	private Order order;

	/**
	 * Construct a new shop.
	 * @param inventory Inventory for the shop.
	 * @param suppliers Suppliers for the shop.
	 * @param order Order for the shop.
	 */
	public ServerShop(Inventory inventory, ArrayList<Supplier> suppliers, Order order) {
		this.inventory = inventory;
		this.suppliers = suppliers;
		this.order = order;
	}

	/**
	 * Getter for inventory.
	 * @return Inventory.
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * If the supplier can fulfill the order add it.
	 * @param item  Item to order.
	 * @param quantity Quantity to order.
	 */
	public void addOrder(Item item, int quantity) {
		if(item.getSupplier().addOrder(item, quantity)) {
			order.addOrder(item, quantity);
		}
	}

	/**
	 * Read file and split into array by ';' delimiter.
	 * @param fileName File to open.
	 * @return ArrayList of string array of the file separated by ';' and '\n'.
	 */
	public ArrayList<String[]> readFile(String fileName) {
		String line = null;
		String delimiter = ";";
		String[] stringArray;
		ArrayList<String[]> stringMatrix = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				stringArray = line.split(delimiter);
				stringMatrix.add(stringArray);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.err.println("Unable to open file '" + fileName + "'");
		}
		catch(IOException ex) {
			System.err.println("Error reading file '" + fileName + "'");
		}
		return stringMatrix;
	}

	/**
	 * Parse the supplier data from the text file and add it to the supplier list.
	 */
	public void readSupplierFile() {
		int id;
		String companyName;
		String address;
		String salesContact;
		ArrayList<String[]> stringMatrix = readFile(Constants.supplierFileName);
		for (String[] stringArray:stringMatrix) {
			id = Integer.parseInt(stringArray[0]);
			companyName = stringArray[1];
			address = stringArray[2];
			salesContact = stringArray[3];
			Supplier supplier = new Supplier(id,companyName,address,salesContact);
			suppliers.add(supplier);
		}
	}

	/**
	 * Search for supplier by it's data.
	 * @param id Id to search for.
	 * @return Returns supplier object if it's found else null.
	 */
	public Supplier searchSupplierById (int id) {
		for (Supplier s:suppliers) {
			if(s.getId() == id){
				return s;
			}
 		}
		return null;
	}

	/**
	 * Parse the item data from the file and populate the Inventory.
	 */
	public void readItemFile() {
		int id;
		String name;
		int quantity;
		double price;
		int supplierId;
		Item item;
		ArrayList<String[]> stringMatrix = readFile(Constants.itemFileName);
		for (String[] stringArray:stringMatrix) {
			id = Integer.parseInt(stringArray[0]);
			name = stringArray[1];
			quantity = Integer.parseInt(stringArray[2]);
			price = Double.parseDouble(stringArray[3]);
			supplierId = Integer.parseInt(stringArray[4]);
			item = new Item(id,name,quantity,price);
			item.setSupplier(searchSupplierById(supplierId));
			inventory.addItem(item);
		}
	}

	/**
	 * Check the quantity of an item in the shop.
	 * If needed fulfill an order for the item.
	 * @param item Item to check quantity for.
	 * @return Quantity of item.
	 */
	public int checkQuantity(Item item) {
		int quantity = inventory.checkQuantity(item);
		if(quantity<Constants.orderMinThreshhold) {
			int orderQuantity = Constants.orderMaxThreshhold-quantity;
			this.addOrder(item,orderQuantity);
			return inventory.checkQuantity(item);
		}
		return quantity;
	}

	/**
	 * Add a sale for an item.
	 * @param item Item to sell.
	 * @param quantity Quantity of item to sell.
	 * @return true if item was sold, false if there was inefficient quantity.
	 */
	public boolean addSale(Item item, int quantity) {
		return item.addSale(quantity);
	}
}
