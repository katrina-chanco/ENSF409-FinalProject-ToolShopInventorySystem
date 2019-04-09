package Client.View;

// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.Controller.Client;

/*
 * Listener for the Make Sale button
 */
public class B3 extends GUIController implements ActionListener{
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
		System.out.println("Class B3 pushed");
		
	}

}
