package Client.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI {

	private JPanel mainMenu;
	private JPanel mainButtons;
	
	public JPanel getMain() {
		return mainMenu;
	}
	
	public void startMainMenu() {
		mainButtons = new JPanel();
		mainButtons.setLayout(new GridLayout(1, 5));
		JButton b1 = new JButton("text1");
		JButton b2 = new JButton("text2");
		JButton b3 = new JButton("text3");
		
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
		
		
		mainButtons.add(b1);
		mainButtons.add(b2);
		mainButtons.add(b3);
		
		mainMenu = new JPanel();
		mainMenu.setLayout(new BorderLayout(0, 0));
		mainMenu.setMinimumSize(new Dimension(500, 500));
		mainMenu.add(mainButtons, BorderLayout.SOUTH);
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

