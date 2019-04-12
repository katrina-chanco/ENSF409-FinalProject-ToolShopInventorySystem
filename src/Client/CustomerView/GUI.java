package Client.CustomerView;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Client.Controller.Client;

public class GUI {

	private JPanel mainMenu; 
	private JPanel mainButtons;
	private Client user;
	private JLabel l;
	private JLabel dl;
	private JButton b1;
    private JButton b3;
	private JButton b4;
	private JButton b8;
	private B1 lb1;
	private B3 lb3;
	private B4 lb4;
	private B8 lb8;
	
	
	public JPanel getMain() {return mainMenu;}
	public JLabel getL() { return l;}
	public JLabel getDL() {return dl;}
	public JButton getB1() {return b1;}
	public JButton getB3() {return b3;}
	public JButton getB4() {return b4;}
	public JButton getB8() {return b8;}
	public void setButton(B1 b) {lb1 = b;}
	public void setButton(B3 b) {lb3 = b;}
	public void setButton(B4 b) {lb4 = b;}
	public void setButton(B8 b) {lb8 = b;}
	
	/**
	 * Displays the main menu for the GUI
	 * The buttons and background colors are set here, also the layout of the main page of the GUI is set
	 * Action listeners are set by the setters in the methods above
	 */
	public void startMainMenu() {

		JLabel title = new JLabel();
		title.setText("Shop Manager Customer");
		title.setHorizontalAlignment(0);
		JPanel t = new JPanel();
		t.add(title);
		t.setForeground(new Color(192, 192, 192));
		t.setBackground(new Color(217, 192, 175));
		
		ImageIcon logo = new ImageIcon("logo.jpg");
		l = new JLabel(logo);
		l.setVisible(false);
		ImageIcon dlogo = new ImageIcon("Wood Grain background.jpg");
		dl = new JLabel(dlogo);
		dl.setVisible(true);
		
		mainButtons = new JPanel();
		mainButtons.setLayout(new GridLayout(2, 4));
		b1 = new JButton("List Inventory");
		b3 = new JButton("Make a Purchase");
		b4 = new JButton("Search");
		b8 = new JButton("Quit");
		
		b1.setBackground(new Color(110, 168, 122));
		b1.setForeground(new Color(0, 0, 0));
		b3.setBackground(new Color(163, 115, 160));
		b3.setForeground(new Color(0, 0, 0));
		b4.setBackground(new Color(80, 230, 30));
		b4.setForeground(new Color(0, 0, 0));
		b8.setBackground(new Color(227, 35, 20));
		b8.setForeground(new Color(0, 0, 0));
		
		b1.addActionListener(lb1);
		b3.addActionListener(lb3);
		b4.addActionListener(lb4);
		b8.addActionListener(lb8);
		
		mainButtons.add(b1);
		mainButtons.add(b3);
		mainButtons.add(b4);
		mainButtons.add(b8);
		
		mainMenu = new JPanel();
		mainMenu.setBackground(new Color(0, 0, 0));
		mainMenu.setLayout(new BorderLayout(0, 0));
		mainMenu.setMinimumSize(new Dimension(2000, 2000));
		mainMenu.add(mainButtons, BorderLayout.SOUTH);
		mainMenu.add(t, BorderLayout.NORTH);
		mainMenu.add(dl, BorderLayout.CENTER);
		mainMenu.setVisible(true);
		
	}

}

