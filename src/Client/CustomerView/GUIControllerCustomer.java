package Client.CustomerView;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

import javax.swing.*;
import javax.swing.WindowConstants;
import Client.Controller.Client;
import org.json.JSONObject;

public class GUIControllerCustomer {

	protected GUI menu;
	protected Client client;
	protected static JSONObject userReturnJSON;

	 /**
	 * Default constructor for the class, calls the constructor from the superclass
	 * @param m GUI object
	 * @param c Client object
	 */
	public GUIControllerCustomer(GUI m, Client c) {
		menu = m;
		client = c;
	}


	/**
	 * Main function for the Client side. Starts the client connection, and starts the GUI, then sets the actionListeners for the buttons
	 */
	public static void main(String[] args) {
		//Client user = new Client("localhost", 8099);
		Client user = new Client("toolshop.krul.ca", 8099);


		GUI menu = new GUI();
		GUIControllerCustomer shop = new GUIControllerCustomer(menu, user);
		JFrame frame = new JFrame("CUSTOMER GUI");

		B1 b1 = new B1(menu, user);
		B3 b3 = new B3(menu, user);
		B4 b4 = new B4(menu, user);
		B8 b8 = new B8(menu, user);
		menu.setButton(b1);
		menu.setButton(b3);
		menu.setButton(b4);
		menu.setButton(b8);



		menu.startMainMenu();
		frame.setContentPane(menu.getMain());
		frame.setSize(650, 565);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		WindowClose closeListener = new WindowClose(menu, user);
		frame.addWindowListener(closeListener);

	}

}
