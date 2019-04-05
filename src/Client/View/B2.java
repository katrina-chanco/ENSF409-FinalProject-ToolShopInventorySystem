package Client.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.Controller.Client;

/*
 * Listener for the Check Quantity button
 */
public class B2 extends GUIController implements ActionListener{

	public B2(GUI m, Client c) {
		super(m, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Class B2 pushed");
		
	}

}
