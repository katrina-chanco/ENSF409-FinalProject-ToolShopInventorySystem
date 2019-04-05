package Client.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Client.Controller.Client;

public class GUI {

	private JPanel mainMenu; 
	private JPanel mainButtons;
	private Client user;
	private JLabel l;
	private JLabel dl;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private B1 lb1;
	private B2 lb2;
	private B3 lb3;
	private B4 lb4;
	private B5 lb5;
	
	
	public JPanel getMain() {return mainMenu;}
	public JLabel getL() { return l;}
	public JLabel getDL() {return dl;}
	public JButton getB1() {return b1;}
	public JButton getB2() {return b2;}
	public JButton getB3() {return b3;}
	public JButton getB4() {return b4;}
	public JButton getB5() {return b5;}
	public void setB1(B1 b) {lb1 = b;}
	public void setB2(B2 b) {lb2 = b;}
	public void setB3(B3 b) {lb3 = b;}
	public void setB4(B4 b) {lb4 = b;}
	public void setB5(B5 b) {lb5 = b;}
	
	/**
	 * Displays the main menu for the GUI
	 * The buttons and background colors are set here, also the layout of the main page of the GUI is set
	 * Action listeners are set by the setters in the methods above
	 */
	public void startMainMenu() {
		JLabel title = new JLabel();
		title.setText("Shop Manager");
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
		mainButtons.setLayout(new GridLayout(1, 5));
		b1 = new JButton("List Inventory");
		b2 = new JButton("Check Quantity");
		b3 = new JButton("Make Sale");
		b4 = new JButton("Search");
		b5 = new JButton("K.E.N");
		b1.setBackground(new Color(110, 168, 122));
		b1.setForeground(new Color(0, 0, 0));
		b2.setBackground(new Color(39, 162, 164));
		b2.setForeground(new Color(0, 0, 0));
		b3.setBackground(new Color(163, 115, 160));
		b3.setForeground(new Color(0, 0, 0));
		b4.setBackground(new Color(201, 121, 69));
		b4.setForeground(new Color(0, 0, 0));
		b5.setBackground(new Color(201, 86, 79));
		b5.setForeground(new Color(0, 0, 0));
		
		b1.addActionListener(lb1);
		b2.addActionListener(lb2);
		b3.addActionListener(lb3);
		b4.addActionListener(lb4);
		b5.addActionListener(lb5);	
		
		mainButtons.add(b1);
		mainButtons.add(b2);
		mainButtons.add(b3);
		mainButtons.add(b4);
		mainButtons.add(b5);
		
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

