// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180


package Client.View;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import Client.Controller.Client;

public class B1 extends GUIController implements ActionListener{
	/**
	 * A JSONObject that will receive data from the Client and then be parsed to display the information to the user
	 */
	private JSONObject obj;
	
	
	/**
	 * Default constructor for the B2 class, calls the constructor from the superclass
	 * @param m GUI object
	 * @param c Client object
	 */
	public B1(GUI m, Client c) {
		super(m, c);
	}

	
	/**
	 * Overrides the actionPerformed method from ActionListener
	 * Receives a JSONObject from the client, and then parses the information
	 * A table is created and the information from the JSONObject is added, then the table is displayed to the screen for the user
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			obj = new JSONObject();
			obj = client.listAllTools();
		} catch (IOException e1) {
			System.err.println("Unable to print tools");
		}
		int length = 0;
		length = obj.getJSONArray("itemList").length();
		String [] columnNames = {"Item Name", "Quantity", "Price", "Supplier", "Supplier ID", "Item ID"};
		String [][] data = new String[length][6];
		String [] row = new String[6];
		for(int i = 0; i < length; i++) {
			row = getRow(i);
			data[i] = row;
		}
		
		JTable table = new JTable(data, columnNames);
		JScrollPane sp = new JScrollPane(table);
		
		
		JFrame frame = new JFrame();
		frame.setTitle("Inventory List");
		frame.add(sp);
		frame.setMinimumSize(new Dimension(700, 600));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
		
	}
	
	/**
	 * Parses an individual row of data from an array of JSONObjects, returns the data as a String array
	 * @param i Index of the data to be parsed
	 * @return The data from the index of the JSONArray as a String array
	 */
	public String[] getRow(int i) {
		String [] s = new String[6];
		JSONObject item = obj.getJSONArray("itemList").getJSONObject(i);
		String itemName = item.getString("itemName");
        String quantity = Integer.toString(item.getInt("quantity"));
        String price = Integer.toString(item.getInt("price"));

        JSONObject supplier = item.getJSONObject("supplier");
        String companyName = supplier.getString("companyName");
        String supplierId = Integer.toString(supplier.getInt("id"));
        String itemId = Integer.toString(item.getInt("id")); 
        s[0] = itemName;
        s[1] = quantity;
        s[2] = price;
        s[3] = companyName;
        s[4] = supplierId;
        s[5] = itemId;
		return s;
	}

}
