package Client.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.Controller.Client;

/**
 * Action Listener for the Add User Button
 *
 */
public class B7 extends GUIController implements ActionListener{

	public B7(GUI m, Client c) {
		super(m, c);
	}

	/**
	 * Starts the procedure for adding a user
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		addUserGUI addUserGUIdialog = new addUserGUI(client);
		addUserGUIdialog.pack();
		addUserGUIdialog.setVisible(true);
	}

}
