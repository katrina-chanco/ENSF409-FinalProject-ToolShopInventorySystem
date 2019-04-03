package Server.Controller;

import Server.Model.Inventory;
import Server.Model.Item;
import Server.Model.Order;
import Server.Model.Supplier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ShopServer implements Constants {

    private Inventory inventory;
    private ArrayList<Supplier> suppliers;
    private Order order;


    public ShopServer(Inventory inventory, ArrayList<Supplier> suppliers, Order order) {
        this.inventory = inventory;
        this.suppliers = suppliers;
        this.order = order;
    }


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



    public Inventory getInventory() {
        return inventory;
    }


    public void addOrder(Item item, int quantity) {
        if(item.getSupplier().addOrder(item, quantity)) {
            order.addOrder(item, quantity);
        }
    }


    public Supplier searchSupplierById (int id) {
        for (Supplier s:suppliers) {
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }


    public int checkQuantity(Item item) {
        int quantity = inventory.checkQuantity(item);
        if(quantity<Constants.orderMinThreshhold) {
            int orderQuantity = Constants.orderMaxThreshhold-quantity;
            this.addOrder(item,orderQuantity);
            return inventory.checkQuantity(item);
        }
        return quantity;
    }


    public boolean addSale(Item item, int quantity) {
        return item.addSale(quantity);
    }
}
