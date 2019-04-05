package Client.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.Controller.Client;

public class B3 extends GUIController implements ActionListener{

	public B3(GUI m, Client c) {
		super(m, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Class B3 pushed");
		
	}

}
