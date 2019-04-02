package Client.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.text.StyledEditorKit.FontSizeAction;

public class GUI {

	private JPanel mainMenu;
	private JPanel mainButtons;
	
	public JPanel getMain() {
		return mainMenu;
	}
	
	public void startMainMenu() {
		JLabel title = new JLabel();
		title.setText("Shop Manager");
		title.setHorizontalAlignment(0);
		title.setForeground(new Color(192, 192, 192));
		title.setBackground(new Color(0, 0, 0));
		
		
		mainButtons = new JPanel();
		mainButtons.setLayout(new GridLayout(1, 5));
		JButton b1 = new JButton("List Inventory");
//		b1.setBackground(new Color());
//		set background colors if we want
		JButton b2 = new JButton("Check Quantity");
		JButton b3 = new JButton("Make Sale");
		JButton b4 = new JButton("Search");
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("b1 pushed");
				
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
		
		
		mainButtons.add(b1);
		mainButtons.add(b2);
		mainButtons.add(b3);
		mainButtons.add(b4);
		
		mainMenu = new JPanel();
		mainMenu.setBackground(new Color(75, 75, 75));
		mainMenu.setLayout(new BorderLayout(0, 0));
		mainMenu.setMinimumSize(new Dimension(500, 500));
		mainMenu.add(mainButtons, BorderLayout.SOUTH);
		mainMenu.add(title, BorderLayout.NORTH);
		mainMenu.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		GUI shop = new GUI();
		JFrame frame = new JFrame("GUI");
		shop.startMainMenu();
		frame.setContentPane(shop.getMain());
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}

