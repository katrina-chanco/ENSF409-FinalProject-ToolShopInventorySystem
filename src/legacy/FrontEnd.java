import java.util.ArrayList;
import java.util.Scanner;

/**
 * Frontend implementation got the Store.
 * @author evan krul
 */
public class FrontEnd {
	/**
	 * Presents the selection menu for the user.
	 * @param shop Shop object that the menu is working for.
	 */
	public static void presentMenu(Shop shop) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		while (true) {
			System.out.println("1. List all tools\n" +
					"2. Search for tool\n" +
					"3. Check Item Quantity\n" +
					"4. Make a sale\n" +
					"5. Quit\n");
			System.out.println("Please make a selection: ");
			choice = scanner.nextInt();
			switch (choice) {
				case 1:
					listTools(shop);
					break;
				case 2:
					searchForItemPrint(shop,scanner);
					break;
				case 3:
					checkQuantity(shop,scanner);
					break;
				case 4:
					makeASale(shop,scanner);
					break;
				case 5:
					System.exit(0);
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Make a sale for Shop.
	 * If the ordered quantity is greater that the quantity in stock the order is canceled.
	 * @param shop Shop to purchase from.
	 * @param scanner Scanner to read input.
	 */
	private static void makeASale(Shop shop, Scanner scanner) {
		Item item = searchForItem(shop,scanner);
		if(item == null) {
			return;
		}
		System.out.println("Enter quantity to sell: ");
		int quantity = scanner.nextInt();
		if(shop.addSale(item,quantity)) {
			System.out.println("The sale of "+quantity+" "+item.getItemName()+" has been completed.");
			shop.checkQuantity(item);
		} else {
			System.err.println("Not enough stock! The sale is canceled!");
		}
	}
	/**
	 * Prints the searched for item.
	 * @param shop  Shop object to be used.
	 * @param scanner Scanner object to be used for selection input.
	 */
	private static void searchForItemPrint(Shop shop, Scanner scanner) {
		Item item = searchForItem(shop,scanner);
		System.out.println(item.toString());
	}

	/**
	 * Search for an item after requesting to search for ID or NAME.
	 * @param shop Shop to be searched in.
	 * @param scanner Scanner to be used for user input.
	 * @return  Found item object or null if no item is found.
	 */
	private static Item searchForItem(Shop shop, Scanner scanner) {
		System.out.println("Search by: \n" +
				"1. Item Name\n" +
				"2. Item ID\n");
		System.out.println("Please make a selection: ");
		int choice = scanner.nextInt();
		Item item;
		switch (choice) {
			case 1:
				item = searchByName(shop);
				break;
			case 2:
				item = searchById(shop);
				break;
			default:
				item = null;
				break;
		}
		return item;
	}

	/**
	 * Check the quantity of an item after searching for the item.
	 * @param shop Shop to use.
	 * @param scanner Scanner to use to read.
	 */
	private static void checkQuantity(Shop shop, Scanner scanner) {
		Item item =searchForItem(shop,scanner);
		int quantity = shop.checkQuantity(item);
		System.out.println("The quantity of "+item.getItemName() +" is : "+quantity);
	}

	/**
	 * Search inventory by id. Return found item or null of no item found.
	 * @param shop Shop to search in.
	 * @return  Item found.
	 */
	private static Item searchById(Shop shop) {
		Scanner intScanner = new Scanner(System.in);
		System.out.println("Enter item id: ");
		int itemID = intScanner.nextInt();
		Item item = shop.getInventory().searchById(itemID);
		if(item == null) {
			System.err.println("Item not found...");
			return null;
		} else {
			return item;
		}
	}

	/**
	 * Search inventory by Name. Return found item or null of no item found.
	 * @param shop Shop to search in.
	 * @return  Item found.
	 */
	private static Item searchByName(Shop shop) {
		Scanner wordScanner = new Scanner(System.in);
		System.out.println("Enter item name: ");
		String itemName = wordScanner.nextLine();
		Item item = shop.getInventory().searchByName(itemName);
		if(item == null) {
			System.err.println("Item not found...");
			return null;
		} else {
			return item;
		}
	}

	/**
	 * List all the items in inventory.
	 * @param shop Shop to list inventory from.
	 */
	private static void listTools(Shop shop) {
		System.out.println("Items: ");
		System.out.println(shop.getInventory().toString());
	}

	/**
	 * Main to create store object and call the presentMenu() function.
	 * @param args  Unused input args to the program.
	 */
	public static void main(String[] args) {
		ArrayList<Item> items = new ArrayList<>();
		Inventory inventory = new Inventory(items);
		ArrayList<Supplier> suppliers = new ArrayList<>();
		ArrayList<OrderLine> orderLines = new ArrayList<>();
		Order order = new Order(orderLines);
		Shop shop = new Shop(inventory,suppliers,order);
		shop.readSupplierFile();
		shop.readItemFile();
		presentMenu(shop);
	}

}
