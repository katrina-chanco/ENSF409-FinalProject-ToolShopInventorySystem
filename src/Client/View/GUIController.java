package Client.View;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

import javax.swing.*;
import javax.swing.WindowConstants;
import Client.Controller.Client;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class GUIController {

	protected GUI menu;
	protected Client client;

	 /**
	 * Default constructor for the class, calls the constructor from the superclass
	 * @param m GUI object
	 * @param c Client object
	 */
	public GUIController(GUI m, Client c) {
		menu = m;
		client = c;
	}

	/**
	 * Main function for the Client side. Starts the client connection, and starts the GUI, then sets the actionListeners for the buttons
	 * @param args
	 */
	public static void main(String[] args) {
//		LoadingWindow w = new LoadingWindow();
		Client user = new Client("localhost", 8099);
		//Client user = new Client("toolshop.krul.ca", 8099);



		//NATHAN PLEASE FIX/ORGINIZE THIS FOR YOUR CODE
		//auto login dialog
		do {
			JPanel loginPanel = new JPanel();
			loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
			JLabel labelUser = new JLabel("Enter your username:");
			JLabel labelPass = new JLabel("Enter your password:");
			JTextField userName = new JTextField();
			JPasswordField password = new JPasswordField(10);
			loginPanel.add(labelUser);
			loginPanel.add(userName);
			loginPanel.add(labelPass);
			loginPanel.add(password);
			String[] options = new String[]{"Login", "Cancel"};
			int option = JOptionPane.showOptionDialog(null, loginPanel, "Login",
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[0]);

			if(option == 0)
			{
				//login
				try {
					String passwordString = new String(password.getPassword());
					JSONObject userReturn = user.login(userName.getText(), passwordString);
					if(userReturn.getBoolean("success")) {
						JOptionPane.showMessageDialog(loginPanel,
								"Welcome "+userReturn.getString("userName"),"Success",JOptionPane.PLAIN_MESSAGE);
						break;
					} else {
						JOptionPane.showMessageDialog(loginPanel,
								"Incorrect username or password. Please try again!",
								"Login Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				//quit
				user.close();
				System.exit(0);
			}
		} while (true);


		//add user dialog MOVE THIS PLEASE NATHAN
		addUserGUI addUserGUIdialog = new addUserGUI(user);
		addUserGUIdialog.pack();
		addUserGUIdialog.setVisible(true);

		GUI menu = new GUI();
		GUIController shop = new GUIController(menu, user);
		JFrame frame = new JFrame("GUI");

		B1 b1 = new B1(menu, user);
		B2 b2 = new B2(menu, user);
		B3 b3 = new B3(menu, user);
		B4 b4 = new B4(menu, user);
		B5 b5 = new B5(menu, user);
		menu.setB1(b1);
		menu.setB2(b2);
		menu.setB3(b3);
		menu.setB4(b4);
		menu.setB5(b5);


		menu.startMainMenu();
		frame.setContentPane(menu.getMain());
		frame.setSize(650, 565);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		WindowClose closeListener = new WindowClose(menu, user);
		frame.addWindowListener(closeListener);

	}

}
