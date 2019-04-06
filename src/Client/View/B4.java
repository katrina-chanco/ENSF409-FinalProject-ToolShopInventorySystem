// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

package Client.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import Client.Controller.Client;

/**
 * 
 * Action Listener for the Search button, inherits from GUIController and uses the ActionListener interface
 *
 */
public class B4 extends GUIController implements ActionListener{
	/**
	 * Default constructor, calls the constructor for the superclass
	 * @param m GUI object
	 * @param c Client object
	 */
	public B4(GUI m, Client c) {
		super(m, c);
	}
	
	/**
	 * Overrides the actionPerformed method from ActionListener
	 * Displays a JOptionPane that lets the user choose to pick to search by ID or name
	 * When the user chooses, the search method is called with the name or id specified
	 * @Override
	 */
	public void actionPerformed(ActionEvent e) {
		JPanel p = new JPanel();
		String [] s = {"Seach Name", "Search ID"};
		int i = JOptionPane.showOptionDialog(null, p,"Search for a tool by name or ID?" ,JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, s, null);
		if(i == 0) {
			String nameSearch = JOptionPane.showInputDialog("Please enter the name of a tool to search for");
			searchName(nameSearch);
		}else if(i == 1) {
			String idSearch = JOptionPane.showInputDialog("Please enter the ID of a tool to search for");
			int id = Integer.parseInt(idSearch);
			searchID(id);
		}
	}
	/**
	 * Takes a String and calls the search method from the client to search for the tool
	 * The client returns a JSON object, which is then parsed and displayed for the user
	 * If the tool is not found, it displays a message for the user
	 * @param s Tool Name to search for
	 */
	public void searchName(String s) {
		JSONObject search = null;
		try {
			search = client.searchToolName(s);
		} catch (IOException e) {
			System.err.println("Unable to search");
		}
		if(search == null) {
			JOptionPane.showMessageDialog(null, "No item found");
		}else {
			String itemName = search.getString("itemName");
	        String quantity = Integer.toString(search.getInt("quantity"));
	        String price = Integer.toString(search.getInt("price"));

	        JSONObject supplier = search.getJSONObject("supplier");
	        String companyName = supplier.getString("companyName");
	        String supplierId = Integer.toString(supplier.getInt("id"));
	        String itemId = Integer.toString(search.getInt("id"));
	        String message = "Item Name: "+itemName+"  Quantity: "+quantity+"  Price: "+price+"  Supplier: "+companyName+"  Supplier ID: "+supplierId+"  Item ID: "+itemId;
	        JOptionPane.showMessageDialog(null, message);
		}
	}
	/**
	 * Takes an integer and calls the search method from the client to search for the tool
	 * The client returns a JSON object, which is then parsed and displayed for the user
	 * If the tool is not found, it displays a message for the user
	 * @param id ID of the tool to search for
	 */
	public void searchID(int id) {
		JSONObject search = null;
		try {
			search = client.searchToolId(id);
		} catch (IOException e) {
			System.err.println("Unable to search");
		}
		if(search.getString("nullType").equals(null)) {
			JOptionPane.showMessageDialog(null, "No item found");
		}else {
			String itemName = search.getString("itemName");
	        String quantity = Integer.toString(search.getInt("quantity"));
	        String price = Integer.toString(search.getInt("price"));

	        JSONObject supplier = search.getJSONObject("supplier");
	        String companyName = supplier.getString("companyName");
	        String supplierId = Integer.toString(supplier.getInt("id"));
	        String itemId = Integer.toString(search.getInt("id"));
	        String message = "Item Name: "+itemName+"  Quantity: "+quantity+"  Price: "+price+"  Supplier: "+companyName+"  Supplier ID: "+supplierId+"  Item ID: "+itemId;
	        JOptionPane.showMessageDialog(null, message);
		}
	}

}
