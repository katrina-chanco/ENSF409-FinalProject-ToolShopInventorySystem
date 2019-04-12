package Client.View;

//Nathan Darby - 30033588
//Katrina Chanco - 30037408
//Evan Krul - 30043180

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.json.JSONObject;

import Client.Controller.Client;

/**
 * Action Listener for the View Orders Button
 *
 */
public class B6 extends GUIController implements ActionListener{

	private JSONObject orders;
	
	public B6(GUI m, Client c) {
		super(m, c);
	}

	/**
	 * Displays a frame and asks the user to enter a range of dates to search for
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		JPanel insertFrame = new JPanel();
		frame.setLayout(new BorderLayout(0, 0));
		insertFrame.setLayout(new BorderLayout(0, 0));
		
		JLabel top = new JLabel("Enter a range of dates to search for in YYYY-MM-DD format:");
		top.setHorizontalAlignment(0);
		insertFrame.add(top, BorderLayout.NORTH);
		
		JPanel fields = new JPanel();
		fields.add((new JLabel("Start Date")));
		JTextField start = new JTextField(6);
		fields.add(start);
		fields.add((new JLabel("End Date")));
		JTextField end = new JTextField(6);
		fields.add(end);
		
		JButton viewOrders = new JButton("View Orders");
		viewOrders.addActionListener(new ActionListener() {
			
			/*
			 * This action listener is activated when the view order button is pressed
			 * The orders that are from the dates given from the user will be displayed by calling the display() method
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String startDate = start.getText();
				String endDate = end.getText();
				
				

				if(startDate.trim().equals("") && endDate.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid date");
				}else {
					orders = null;
					try {
						orders = client.viewOrder(startDate, endDate);

					} catch (IOException e1) {
						System.err.println("Unable to view orders");
					}
					if(orders == null) {
						if(userReturnJSON.getJSONObject("accessLevel").getInt("userLevelID")==4) {
							JOptionPane.showMessageDialog(null, "Are you a stupid moron? Next time try searching for an item that exists!");
						} else {
							JOptionPane.showMessageDialog(null, "No order found");
						}
					}
				}
				
				display();
				frame.setVisible(false);
			}
		});
		JButton can = new JButton("Cancel");
		can.addActionListener(new ActionListener() {
			
			/*
			 * This listener is activated when the cancel button is pressed, the view order window will then be closed
			 * 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);

			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(viewOrders);
		buttonPanel.add(can);
		insertFrame.add(buttonPanel, BorderLayout.SOUTH);
		
		insertFrame.add(fields, BorderLayout.CENTER);
		frame.setContentPane(insertFrame);
		frame.setSize(400, 200);
		frame.setVisible(true);
		
	}
	
	/**
	 * This method parses the JSON object that is returned from the client, and displays it as a table for the user
	 */
	public void display() {
		int length = 0;
		length = orders.getJSONArray("orders").length();
		String [] columnNames = {"Order ID", "Order Date", "Quantity Ordered", "Item Name", "Quantity", "Price", "Company Name", "Company Address", "Sales Contact"};
		String [][] data = new String[length][columnNames.length];
		String [] row = new String[columnNames.length];
		for(int i = 0; i < length; i++) {
			row = getRow(i);
			data[i] = row;
		}
		
		JTable table = new JTable(data, columnNames);
		table.setEnabled(false);
		JScrollPane sp = new JScrollPane(table);
		
		
		JFrame frame = new JFrame();
		frame.setTitle("Order List");
		frame.add(sp);
		frame.setMinimumSize(new Dimension(800, 300));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * This method parses the JSON Object for information from the specified index of the JSON Array
	 * @param i Index of the JSONArray to parse
	 * @return a String array to be inserted as a row into the table
	 */
	public String[] getRow(int i) {
		String [] s = new String[9];
		JSONObject item = orders.getJSONArray("orders").getJSONObject(i);
		JSONObject order = item.getJSONObject("order");
		String orderId = Integer.toString(order.getInt("orderId"));
        String orderDate = order.getString("orderDate");
        String orderQuantity = Integer.toString(order.getInt("quantityOrdered"));

        JSONObject orderItem = item.getJSONObject("item");
        String itemName = orderItem.getString("itemName");
        String itemQuantity = orderItem.getString("quantity");
        String itemPrice = orderItem.getString("price"); 
        
        JSONObject orderSupplier = item.getJSONObject("supplier");
        String supplierName = orderSupplier.getString("companyName");
        String supplierAddress = orderSupplier.getString("companyAddress");
        String supplierContact = orderSupplier.getString("salesContact");
        
        s[0] = orderId;
        s[1] = orderDate;
        s[2] = orderQuantity;
        s[3] = itemName;
        s[4] = itemQuantity;
        s[5] = itemPrice;
        s[6] = supplierName;
        s[7] = supplierAddress;
        s[8] = supplierContact;
		return s;
	}

}
