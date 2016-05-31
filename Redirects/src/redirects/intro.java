package redirects;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class intro extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	Redirects b = new Redirects();
	JLabel textArea = new JLabel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					intro frame = new intro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public intro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblHeader = new JLabel("Please select an option:");
		lblHeader.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblHeader);

		JRadioButton rdbtnIWantTo = new JRadioButton(
				"I want to upload the original and redirected URLs and verify that the redirected URL is correct");
		buttonGroup.add(rdbtnIWantTo);
		JRadioButton radioButton = new JRadioButton(
				"I only want to upload the orginal URLs and see the return code and redirected URL");
		buttonGroup.add(radioButton);

		contentPane.add(rdbtnIWantTo);

		contentPane.add(radioButton);

		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (radioButton.isSelected() == true) {
					Redirects.oneOrTwo = 1;
					setupResults();
					try {
						b.getURLs();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					

				}
				if (rdbtnIWantTo.isSelected() == true) {
					Redirects.oneOrTwo = 2;
					setupResults();
					try {
						b.getURLs();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					

				}

				
			}
		});
		contentPane.add(btnStart);
	}

	public void setupResults() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		
		contentPane.add(textArea);
		

		JButton output1 = new JButton("Output to CSV");
		output1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					b.writeCSV();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		contentPane.add(output1);
	}
	
	public void addText(String t){
		textArea.setText(t);
	}
}
