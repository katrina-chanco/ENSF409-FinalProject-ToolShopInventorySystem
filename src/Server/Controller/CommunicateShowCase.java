package Server.Controller;
import Server.Model.*;

import java.util.ArrayList;

/**
 * Class to show how to send and receive JSON data. NOT FOR FINAL PROJECT
 */
public class CommunicateShowCase {


	public static void main(String[] args) {
		/*
		ArrayList<Item> items = new ArrayList<>();
		Inventory inventory = new Inventory(items);
		ArrayList<Supplier> suppliers = new ArrayList<>();
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		Order order = new Order(orderLines);
		ServerShop shop = new ServerShop(inventory,suppliers,order);
		shop.readSupplierFile();
		shop.readItemFile();
		*/

		//JSONManagerServer is generic. Just pass ANY object as an argument and it will parse all the data that has a getter.
		//This works with lists and arrays.
		//The thing im printing can be sent to the client and then parsed into the GUI.
		//@see(https://www.baeldung.com/java-org-json)
		
		//JSONManagerServer<Inventory> inventoryJSONManagerServer = new JSONManagerServer<>(shop.getInventory());

		//System.out.println(inventoryJSONManagerServer.getJsonObject().toString());
	}
}
