package Client.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.Controller.Client;

/**
 * Action Listener for the Quit button, closes the application when pressed
 *
 */
public class B8 extends GUIControllerCustomer implements ActionListener{

	public B8(GUI m, Client c) {
		super(m, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
		
	}

}
