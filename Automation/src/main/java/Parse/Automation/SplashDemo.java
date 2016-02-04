package Parse.Automation;

/*
 * SplashDemo.java
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	SplashDemo()  {
		
		
		ImageIcon image; 
		JLabel label;
		JLabel text = new JLabel();
		Font font = new Font("Calibri", Font.BOLD,30);
		
		setLayout(new FlowLayout());
		
		
		
		text.setText("PusH \n" + parseAutomation.version);
		
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setVerticalAlignment(JLabel.CENTER);
		text.setFont(font);
		text.setForeground(Color.darkGray);
		add(text).setVisible(true);
		text.setVisible(true);
		
		image = new ImageIcon(SplashDemo.class.getResource("technology_future_hi-tech_hd-wallpaper-401653.jpg"));
		label = new JLabel(image);
		add(label);
		
		
		
		
		
		
	
	}

	
	
	public static void splashRun() {
		SplashDemo gui = new SplashDemo();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setBounds(1000,300,600,400);
		gui.setVisible(true);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gui.setVisible(false);
		
	}

}