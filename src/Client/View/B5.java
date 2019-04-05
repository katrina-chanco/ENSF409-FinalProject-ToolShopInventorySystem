package Client.View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.json.JSONObject;

import Client.Controller.Client;

/**
 * ActionListener for the button that starts the Easter Egg
 *
 */
public class B5 extends GUIController implements ActionListener{

	/**
	 * Default constructor for the B5 class, calls the constructor from the superclass
	 * @param m GUI object
	 * @param c Client object
	 */
	public B5(GUI m, Client c) {
		super(m, c);
	}
	
	private JPanel mainMenu;
	private JLabel l;
	private JLabel dl;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	
	public void setMainMenu() {
		mainMenu = menu.getMain();
		l = menu.getL();
		dl = menu.getDL();
		b1 = menu.getB1();
		b2 = menu.getB2();
		b3 = menu.getB3();
		b4 = menu.getB4();
		b5 = menu.getB5();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setMainMenu();	
		if(!l.isVisible()) {
			dl.setVisible(false);
			l.setVisible(true);
			mainMenu.add(l, BorderLayout.CENTER);
			b5.setText("Quit");
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			return;
		}
		
		String message ="";
		message += JOptionPane.showInputDialog("I'm sorry Dave, I'm afraid I can't do that");
		if(message.equals("quit")) {
			System.exit(1);
		}
		else if(message.equals("") || message.equals(null)) {
			return;
		}
		else if(message.equals("What's the problem?")) {
			message = JOptionPane.showInputDialog("I think you know what the problem is just as well as I do.");
			if(message.equals("What are you talking about?")) {
				JOptionPane.showMessageDialog(null, "This application is too important for me to allow you to jeopardize it.");
				
			}
		}
	}

}
