package Client.View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import javax.swing.*;


public class LoadingWindow extends JWindow{

	private static final long serialVersionUID = 1L;
    private JProgressBar progressBar;
    private int count;
    private Timer timer1;

    public LoadingWindow() {
    	progressBar = new JProgressBar();
        Container container = getContentPane();
        container.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EtchedBorder());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 10, 350, 150);
        panel.setLayout(null);
        container.add(panel);

        JLabel label = new JLabel("Loading");
        label.setFont(new Font("Verdana", Font.BOLD, 14));
        label.setBounds(85, 25, 280, 30);
        panel.add(label);

        progressBar.setMaximum(50);
        progressBar.setBounds(55, 180, 250, 15);
        container.add(progressBar);
        loadProgressBar();
        setSize(370, 215);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {
        	
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;

                progressBar.setValue(count);

                if (count == 100) {

//                    createFrame();

                    setVisible(false);

                    timer1.stop();
                }

            }

        
       };
       timer1 = new Timer(25, al);
       timer1.start();
    }
}
