package Client.View;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Client.Controller.Client;

/*
 * This class acts as an action listener for the main GUI window
 * This listener allow the sockets from the client class to be close when the user exits the main GUI
 * Only the windowClosing method is used for this listener
 */
public class WindowClose extends GUIController implements WindowListener {

	public WindowClose(GUI m, Client c) {
		super(m, c);
	}
	@Override
	public void windowOpened(WindowEvent e) {
		
		
	}

	/*
	 * Method to close the sockets in the client class when the GUI window is closed by the user
	*/
	@Override
	public void windowClosing(WindowEvent e) {
		client.close();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
