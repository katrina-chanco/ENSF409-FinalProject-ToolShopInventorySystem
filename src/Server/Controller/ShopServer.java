package Server.Controller;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import Server.Model.Inventory;
import Server.Model.Item;
import Server.Model.Order;
import Server.Model.Supplier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides the Shop on the Server side of the application.
 */
public class ShopServer implements Constants {

    /**
     * Creates the inventory of the shop
     */
    private Inventory inventory;

    /**
     * Creates the list of suppliers for the shop
     */
    private ArrayList<Supplier> suppliers;

    /**
     * Creates the order for the shop
     */
    private Order order;

    /**
     * Getter for inventory
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }


    /**
     * Constructs the shop on the server side
     *
     * @param inventory the inventory
     * @param suppliers the suppliers
     * @param order the order
     */
    public ShopServer(Inventory inventory, ArrayList<Supplier> suppliers, Order order) {
        this.inventory = inventory;
        this.suppliers = suppliers;
        this.order = order;
    }


    /**
     * Read the file
     * Split into an array separated ';' delimiter
     *
     * @param fileName name of the file
     * @return ArrayList of string array of file separated by ';' and '\n'
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
     * Parse the data from text file
     * Add to the list of suppliers
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
     * Parse the data from text file
     * Populate the inventory
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
     * Add order if supplier can fulfill the request
     * @param item  item for the order
     * @param quantity quantity for the order
     */
    public void addOrder(Item item, int quantity) {
        if(item.getSupplier().addOrder(item, quantity)) {
            order.addOrder(item, quantity);
        }
    }


    /**
     * Search for supplier by the ID
     *
     * @param id id used to search with
     * @return Return the supplier if found, otherwise return null
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
     * Check the quantity of the item
     * If needed, fill an order
     * @param item item requested to check quantity of
     * @return quantity of requested item
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
     * Add sale for an item
     * @param item item to sell
     * @param quantity quantity of item requested
     * @return true if item got sold, false if quantity was not enough
     */
    public boolean addSale(Item item, int quantity) {
        return item.addSale(quantity);
    }
}
