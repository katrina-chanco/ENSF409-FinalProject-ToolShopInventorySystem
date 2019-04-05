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

	public B1(GUI m, Client c) {
		super(m, c);
	}

	private JSONObject obj;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			obj = new JSONObject();
			obj = client.listAllTools();
			System.out.println(obj.toString());
		} catch (IOException e1) {
			System.out.println("Unable to print tools");
		}
		
		String [] columnNames = {"Item Name", "Quantity", "Price", "Supplier", "Supplier ID", "Item ID"};
		String [][] data = new String[100][6];
		
		int i = 0;
		String [] row = new String[6];
		row = getRow(0);
		data[0] = row;
//		while(!(row = getRow(i)).equals(null)) {
//			data[i] = row;
//		}
		
		JTable table = new JTable(data, columnNames);
		JScrollPane sp = new JScrollPane(table);
		
		
		JFrame frame = new JFrame();
		frame.setTitle("Inventory List");
		frame.add(sp);
		frame.setMinimumSize(new Dimension(500, 500));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
		
	}
	
	public String[] getRow(int i) {
		String [] s = new String[6];
		JSONObject item = obj.getJSONArray("itemList").getJSONObject(0);// Iterating through the itemList (the first "INDEX" of the itemList)
		System.out.println(item.toString());
		String itemName = item.getString("itemName");
        String quantity = Integer.toString(item.getInt("quantity"));
        String price = Integer.toString(item.getInt("price"));

        JSONObject supplier = item.getJSONObject("supplier");
        String companyName = supplier.getString("companyName"); // Accesses the information within the supplier list
        String supplierId = Integer.toString(supplier.getInt("id"));
        String itemId = Integer.toString(item.getInt("id")); //THEY ARE BOTH THE KEY "id" so that might become a problem?
        s[0] = itemName;
        s[1] = quantity;
        s[2] = price;
        s[3] = companyName;
        s[4] = supplierId;
        s[5] = itemId;
		return s;
	}

}
