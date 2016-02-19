package Parse.Automation;

/*
 * SplashDemo.java
 *
 */

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SplashDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	SplashDemo()  {
		
		
		ImageIcon image; 
		JLabel label;
		JLabel text = new JLabel();
		Font font = new Font("Cooper Black", Font.BOLD,30);
		

		JFrame x = new JFrame();
		
		image = new ImageIcon(SplashDemo.class.getResource("technology-flightscope-2.jpg"));
		label = new JLabel(image);
		label.setBackground(Color.BLACK);
		x.setLayout(new FlowLayout());
		x.setBackground(Color.BLACK);
		x.setForeground(Color.BLACK);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x.setBounds(1000,300,580,375);
		x.setVisible(true);
		x.setBackground(Color.BLACK);
		x.setResizable(false);
			
		
		text.setText("PusH \n" + parseAutomation.version);
		
//		text.setHorizontalAlignment(JLabel.CENTER);
//		text.setVerticalAlignment(JLabel.CENTER);
		text.setFont(font);
		text.setForeground(Color.BLUE);
		text.setBackground(Color.BLACK);
		x.add(text).setVisible(true);
		text.setVisible(true);
		
		
		x.add(label);
		
		
		
		
		
		
	
	}

	
	
	public static void splashRun() {
		SplashDemo gui = new SplashDemo();
		/*gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setBounds(1000,300,580,375);
		gui.setVisible(true);
		gui.setBackground(Color.BLACK);
		gui.setResizable(false);*/
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gui.setVisible(false);
		
	}

}