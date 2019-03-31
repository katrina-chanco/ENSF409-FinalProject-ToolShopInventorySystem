import java.util.ArrayList;

/**
 * Inventory management class for the shop.
 */
public class Inventory {
	/**
	 * List of items.
	 */
	private ArrayList<Item> itemList;

	/**
	 * Constructs inventory from list of items.
	 * @param itemList itemList.
	 */
	public Inventory(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * Add an item to the inventory.
	 * @param item Item to add inventory.
	 */
	public void addItem(Item item) {
		itemList.add(item);
	}

	/**
	 * Remove an item from inventory.
	 * @param item Item to remove.
	 */
	public void removeItem(Item item) {
		itemList.remove(item);
	}

	/**
	 * Search for item in inventory by name.
	 * @param itemName Name of item to search for.
	 * @return Item object if found else null.
	 */
	public Item searchByName(String itemName) {
		for(Item i:itemList) {
			if(itemName.equals(i.getItemName())) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Search for item in inventory by id.
	 * @param itemId Id of item to search for.
	 * @return Item object if found else null.
	 */
	public Item searchById(int itemId) {
		for(Item i:itemList) {
			if(itemId == i.getId()) {
				return i;
			}
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
		String returnString = new String();
		for (Item item:itemList) {
			returnString+=item.toString();
		}
		return returnString;
	}
}
