package Server.Model;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import java.util.ArrayList;

/**
 * Supplier class for shop.
 */
public class Supplier {
	/**
	 * Items the supplier supplies.
	 */
	private ArrayList<Item> items;
	/**
	 * Id of the supplier.
	 */
	public int id;
	/**
	 * Name of supplier.
	 */
	private String companyName;
	/**
	 * Address of supplier.
	 */
	private String address;
	/**
	 * Sales contact of supplier.
	 */
	private String salesContact;

	/**
	 * Constructs supplier.
	 * @param id Id of supplier.
	 * @param companyName Name of supplier.
	 * @param address Address of supplier.
	 * @param salesContact sales contact of supplier.
	 */
	public Supplier(int id, String companyName, String address, String salesContact) {
		this.id = id;
		this.companyName = companyName;
		this.address = address;
		this.salesContact = salesContact;
	}

	/**
	 * Getter for id.
	 * @return supplierId.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for name.
	 * @return companyName.
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Adds and order from an Item.
	 * Just returns true but logic can be implemented to check with the supplier and make the order.
	 * @param item item to order.
	 * @param quantity quantity to order.
	 * @return true.
	 */
	public boolean addOrder(Item item, int quantity) {
		return true;
	}
}
