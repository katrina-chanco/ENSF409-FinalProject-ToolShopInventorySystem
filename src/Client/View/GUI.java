package Client.View;
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
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private B1 lb1;
	private B2 lb2;
	private B3 lb3;
	private B4 lb4;
	private B5 lb5;
	private B6 lb6;
	private B7 lb7;
	private B8 lb8;
	
	
	public JPanel getMain() {return mainMenu;}
	public JLabel getL() { return l;}
	public JLabel getDL() {return dl;}
	public JButton getB1() {return b1;}
	public JButton getB2() {return b2;}
	public JButton getB3() {return b3;}
	public JButton getB4() {return b4;}
	public JButton getB5() {return b5;}
	public JButton getB6() {return b6;}
	public JButton getB7() {return b7;}
	public JButton getB8() {return b8;}
	public void setButton(B1 b) {lb1 = b;}
	public void setButton(B2 b) {lb2 = b;}
	public void setButton(B3 b) {lb3 = b;}
	public void setButton(B4 b) {lb4 = b;}
	public void setButton(B5 b) {lb5 = b;}
	public void setButton(B6 b) {lb6 = b;}
	public void setButton(B7 b) {lb7 = b;}
	public void setButton(B8 b) {lb8 = b;}
	
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
		mainButtons.setLayout(new GridLayout(2, 4));
		b1 = new JButton("List Inventory");
		b2 = new JButton("Check Quantity");
		b3 = new JButton("Make Sale");
		b4 = new JButton("Search");
		b5 = new JButton("K.E.N");
		b6 = new JButton("View Orders");
		b7 = new JButton("Add User");
		b8 = new JButton("Quit");
		
		b1.setBackground(new Color(110, 168, 122));
		b1.setForeground(new Color(0, 0, 0));
		b2.setBackground(new Color(39, 162, 164));
		b2.setForeground(new Color(0, 0, 0));
		b3.setBackground(new Color(163, 115, 160));
		b3.setForeground(new Color(0, 0, 0));
		b4.setBackground(new Color(80, 230, 30));
		b4.setForeground(new Color(0, 0, 0));
		b5.setBackground(new Color(201, 86, 79));
		b5.setForeground(new Color(0, 0, 0));
		b6.setBackground(new Color(23, 189, 252));
		b6.setForeground(new Color(0, 0, 0));
		b7.setBackground(new Color(142, 51, 255));
		b7.setForeground(new Color(0, 0, 0));
		b8.setBackground(new Color(227, 35, 20));
		b8.setForeground(new Color(0, 0, 0));
		
		b1.addActionListener(lb1);
		b2.addActionListener(lb2);
		b3.addActionListener(lb3);
		b4.addActionListener(lb4);
		b5.addActionListener(lb5);	
		b6.addActionListener(lb6);
		b7.addActionListener(lb7);
		b8.addActionListener(lb8);
		
		mainButtons.add(b1);
		mainButtons.add(b2);
		mainButtons.add(b3);
		mainButtons.add(b5);
		mainButtons.add(b4);
		mainButtons.add(b6);
		mainButtons.add(b7);
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

