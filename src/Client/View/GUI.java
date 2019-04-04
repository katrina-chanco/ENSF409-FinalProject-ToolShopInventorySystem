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
	
	public GUI() {
		user = new Client("localhost", 1234);
	}
	
	public JPanel getMain() {
		return mainMenu;
	}
	
	public void startMainMenu() {
		JLabel title = new JLabel();
		title.setText("Shop Manager");
		title.setHorizontalAlignment(0);
		JPanel t = new JPanel();
		t.add(title);
		t.setForeground(new Color(192, 192, 192));
		t.setBackground(new Color(217, 192, 175));
		
		ImageIcon logo = new ImageIcon("logo.jpg");
		JLabel l = new JLabel(logo);
		l.setVisible(false);
		ImageIcon dlogo = new ImageIcon("Wood Grain background.jpg");
		JLabel dl = new JLabel(dlogo);
		dl.setVisible(true);
		
		mainButtons = new JPanel();
		mainButtons.setLayout(new GridLayout(1, 5));
		JButton b1 = new JButton("List Inventory");
		JButton b2 = new JButton("Check Quantity");
		JButton b3 = new JButton("Make Sale");
		JButton b4 = new JButton("Search");
		JButton b5 = new JButton("K.E.N");
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
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("b1 pushed");
				listInv();
				
			}
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("b2 pushed");
				
			}
		});
		
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("b3 pushed");
				
			}
		});
		
		b4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("b4 pushed");
				
			}
		});
		
		b5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
		});
		
		
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
	
	public void listInv() {
		try {
			String s = user.listAllTools();
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GUI shop = new GUI();
		JFrame frame = new JFrame("GUI");
		shop.startMainMenu();
		frame.setContentPane(shop.getMain());
		frame.setSize(650, 565);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}

