package Client.View;

import javax.swing.*;
import javax.swing.WindowConstants;

import Client.Controller.Client;

public class GUIController {
	
	protected GUI menu;
	protected Client client;

	
	public GUIController(GUI m, Client c) {
		menu = m;
		client = c;
	}

	public static void main(String[] args) {
		Client user = new Client("localhost", 1234);
		GUI menu = new GUI();
		GUIController shop = new GUIController(menu, user);
		
//		shop.setMenu(menu, user);
		B1 b1 = new B1(menu, user);
		B5 b5 = new B5(menu, user);
		menu.setB1(b1);
		menu.setB5(b5);
		JFrame frame = new JFrame("GUI");		
		menu.startMainMenu();
		frame.setContentPane(menu.getMain());
		frame.setSize(650, 565);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

}
