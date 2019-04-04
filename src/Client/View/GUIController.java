package Client.View;

import javax.swing.*;
import javax.swing.WindowConstants;

import Client.Controller.Client;

public class GUIController {
	
	private Client user;
	
	public void setUser(String host, int port) {
		user = new Client(host, port);
	}

	public static void main(String[] args) {
		GUIController shop = new GUIController();
		GUI menu = new GUI();
		JFrame frame = new JFrame("GUI");
		shop.setUser("localhost", 1234);
		menu.startMainMenu();
		frame.setContentPane(menu.getMain());
		frame.setSize(650, 565);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

}
