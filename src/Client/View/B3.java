package Client.View;
<<<<<<< HEAD
=======

>>>>>>> bdef2a6919cf7641c4a9150e7fdfdd87c8fd0e0f
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180

<<<<<<< HEAD
=======

>>>>>>> bdef2a6919cf7641c4a9150e7fdfdd87c8fd0e0f

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
		try {
			client.decreaseQuantity(1000, 5);
		} catch(IOException e3){

		}


	}

}
