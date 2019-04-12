package Client.CustomerView;

import java.awt.BorderLayout;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import org.json.JSONObject;

import Client.Controller.Client;

/*
 * Listener for the Make Sale button
 */
public class B3 extends GUIControllerCustomer implements ActionListener{
	/**
	 * Default constructor for the B3 class, calls the constructor from the superclass
	 * @param m GUI object
	 * @param c Client object
	 */
	public B3(GUI m, Client c) {
		super(m, c);
	}
	/**
	 * This method is not operational yet, for testing purposes it prints a message to the console
	 * This method will be updated for Milestone 3
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		JPanel insertFrame = new JPanel();
		frame.setLayout(new BorderLayout(0, 0));
		insertFrame.setLayout(new BorderLayout(0, 0));

		JPanel fields = new JPanel();
		fields.add((new JLabel("ID")));
		JTextField idLabel = new JTextField(6);
		fields.add(idLabel);
		fields.add((new JLabel("Name")));
		JTextField nameLabel = new JTextField(6);
		fields.add(nameLabel);
		fields.add((new JLabel("Amount")));
		JTextField amountLabel = new JTextField(6);
		fields.add(amountLabel);

		JButton makeSale = new JButton("Make Sale");
		makeSale.addActionListener(new ActionListener() {
			
			/*
			 * This action listener is activated when the make sale button is pressed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idLabel.getText();
				String name = nameLabel.getText();
				String amount = amountLabel.getText();
				
				

				if(id.trim().equals("")) {
					if(name.trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter a Name or ID");
					}else {
					JSONObject search = null;
					try {
						search = client.searchToolName(name);
					} catch (IOException e1) {
						System.err.println("Unable to search");
					}
					if(search == null) {
						if(userReturnJSON.getJSONObject("accessLevel").getInt("typeId")==4) {
							JOptionPane.showMessageDialog(null, "Next time try searching for an item that exists!");
						} else {
							JOptionPane.showMessageDialog(null, "No item found");
						}					}else {
						int itemId = search.getInt("id");
						try{
							int amountToDecrease = Integer.parseInt(amount);
							client.decreaseQuantity(itemId, amountToDecrease);
						}catch(NumberFormatException n){
							JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a number");
						} catch (IOException e1) {
							System.err.println("Unable to read number");
						}
					}
				}
					
				}else {
					JSONObject search = null;
					int idToDecrease = -1;
					try {
						idToDecrease = Integer.parseInt(id);
						search = client.searchToolId(idToDecrease);
					} catch (IOException e1) {
						System.err.println("Unable to search");
					} catch(NumberFormatException n){
						JOptionPane.showMessageDialog(null, "Invalid ID. Please enter valid ID");
					}
					if(search == null) {
						if(userReturnJSON.getJSONObject("accessLevel").getInt("typeId")==4) {
							JOptionPane.showMessageDialog(null, "Next time try searching for an item that exists!");
						} else {
							JOptionPane.showMessageDialog(null, "No item found");
						}
					}else {
						
					}
					try{
						int amountToDecrease = Integer.parseInt(amount);
						client.decreaseQuantity(idToDecrease, amountToDecrease);
					}catch(NumberFormatException n){
						JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a number");
					} catch (IOException e1) {
						System.err.println("Unable to read numbers");
					}
					
				}
				JOptionPane.showMessageDialog(null, "Sale Completed");
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
		buttonPanel.add(makeSale);
		buttonPanel.add(can);
		insertFrame.add(buttonPanel, BorderLayout.SOUTH);
		insertFrame.add(fields, BorderLayout.CENTER);
		JLabel top = new JLabel("Enter the ID or Name and Amount");
		top.setHorizontalAlignment(0);
		insertFrame.add(top, BorderLayout.NORTH);
		frame.setContentPane(insertFrame);
		frame.setSize(400, 200);
		frame.setVisible(true);
	

	}

}



