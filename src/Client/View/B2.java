package Client.View;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import Client.Controller.Client;

/**
 * Listener for the Check Quantity button
 */
public class B2 extends GUIController implements ActionListener{
	/**
	 * Default constructor for the B2 class, calls the constructor from the superclass
	 * @param m GUI object
	 * @param c Client object
	 */
	public B2(GUI m, Client c) {
		super(m, c);
	}

	
	/**
	 * Overrides the actionPerformed method from ActionListener
	 * Creates a panel that asks for the user to choose to search by name or ID, then calls the appropriate search method
	 */
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
	/**
	 * Takes a string as the name of a tool to search for and passes it to the client
	 * The client will return a JSONObject which will then be parsed and the quantity will be displayed to the user
	 * If the client cannot find the specified tool, a message will be displayed to the user stating that the tool was not found
	 * @param s The name of the tool to search for as a String
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
	        String itemId = Integer.toString(search.getInt("id"));
	        String message = "Item Name: "+itemName+"  Item ID: "+ itemId+"  Quantity: "+quantity;
	        JOptionPane.showMessageDialog(null, message);
		}
	}
	/**
	 * Takes an integer as the ID of a tool to search for and passes it to the client
	 * The client will return a JSONObject which will then be parsed and the quantity will be displayed to the user
	 * If the client cannot find the specified tool, a message will be displayed to the user stating that the tool was not found
	 * @param id The ID of a tool to search for as an integer
	 */
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
	        String itemId = Integer.toString(search.getInt("id"));
	        String message = "Item Name: "+itemName+"  Item ID: "+ itemId+"  Quantity: "+quantity;
	        JOptionPane.showMessageDialog(null, message);
		}
	}

}
