package Client.View;


// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180


import javax.swing.*;
import javax.swing.WindowConstants;

import Client.Controller.Client;

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
