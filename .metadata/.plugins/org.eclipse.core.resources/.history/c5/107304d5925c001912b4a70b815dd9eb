package Client.View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import Client.Controller.Client;

/**
 * Action Listener for the View Orders Button
 *
 */
public class B6 extends GUIController implements ActionListener{

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
						JSONObject orders = null;
						try {
							orders = client.viewOrder(startDate, endDate);
						} catch (IOException e1) {
							System.err.println("Unable to view orders");
						}
						if(orders == null) {
							JOptionPane.showMessageDialog(null, "No orders found");
							}
						}
				}
					
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

}
