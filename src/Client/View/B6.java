package Client.View;

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
			 * This action listener is activated when the make sale button is pressed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String startDate = start.getText();
				String endDate = end.getText();
				
				

				if(startDate.trim().equals("")) {
					if(endDate.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter a Name or ID");
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
							}							}
						}
				}
				
				display();
				frame.setVisible(false);
			}
		});
		JButton can = new JButton("Cancel");
		can.addActionListener(new ActionListener() {
			
			/*
			 * This listener is activated when the cancel button is pressed, the insert window will then be closed
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
	
	public void display() {
		int length = 0;
		System.out.println(orders.toString());
		//length = orders.getJSONArray("orders").length();
		length = 0;
		String [] columnNames = {"Item Name", "Quantity", "Price", "Supplier", "Supplier ID", "Item ID"};
		String [][] data = new String[length][6];
		String [] row = new String[6];
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
		frame.setMinimumSize(new Dimension(700, 600));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public String[] getRow(int i) {
		String [] s = new String[6];
		JSONObject item = orders.getJSONArray("itemList").getJSONObject(i);
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
