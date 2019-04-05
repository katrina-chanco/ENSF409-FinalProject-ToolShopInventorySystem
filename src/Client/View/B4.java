package Client.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import Client.Controller.Client;

/*
 * Listener for the Search button
 */
public class B4 extends GUIController implements ActionListener{

	public B4(GUI m, Client c) {
		super(m, c);
	}

	@Override
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
	
	public void searchID(int id) {
		JSONObject search = null;
		try {
			search = client.searchToolId(id);
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

}
