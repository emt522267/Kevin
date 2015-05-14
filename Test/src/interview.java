import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class interview {

	protected Shell shlLightmakerCensusBureau;
	protected Shell requirements;
	private Text txtFirst;
	private Text txtLast;
	private Text txtDOB;
	private Text txtSSN;
	private Text txtAddress1;
	private Text txtAddress2;
	private Text txtCity;
	private Text txtState;
	private Text txtZip;
	private Text txtEmployed;
	public String name;
	protected Shell shell;
	private Text txtDefects;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			interview window = new interview();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		JFrame frame = new JFrame("Lightmaker QA");
		name = JOptionPane.showInputDialog(frame, "Please enter your name...");
		Display display = Display.getDefault();
		createContents();
		shlLightmakerCensusBureau.open();
		requirements.open();
		shell.open();
		shlLightmakerCensusBureau.layout();
		while (!shlLightmakerCensusBureau.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLightmakerCensusBureau = new Shell();
		shlLightmakerCensusBureau.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_BLACK));
		shlLightmakerCensusBureau.setSize(690, 438);
		shlLightmakerCensusBureau.setText("Lightmaker Census Bureau");
		
		requirements = new Shell();
		requirements.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_BLACK));
		requirements.setSize(900, 300);
		requirements.setText("Requirements");
		
		Label lblRequirements = new Label(requirements, SWT.NONE);
		lblRequirements.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_YELLOW));
		lblRequirements.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_BLACK));
		lblRequirements.setAlignment(SWT.LEFT);
		lblRequirements.setBounds(10, 10, 900, 980);
		lblRequirements.setText("Requirements:" +  System.getProperty("line.separator") +  "--------------------------------------------------------------------" +  System.getProperty("line.separator") + 
				"- All Required fields will be marked with an astrisk. If a user forgets to enter in required information, an error will display and not allow the user to continue" + System.getProperty("line.separator") + 
				"- User will know if they have completed the survey by a success message" + System.getProperty("line.separator") + 
				"- Color Pallette will match Lightmaker's corporate colors of Yellow and Black" + System.getProperty("line.separator") + 
				"- Gender (Male, Female) and Employed (Employed, Self Employed, Unemployed) boxes will be comboboxes" + System.getProperty("line.separator") + 
				"- If the client would like to remove their name from the census, they will be able to click a button to change their name to 'Name Withheld'" + System.getProperty("line.separator") +
				"- There will be a button to clear all input fields");
		
		
		shell = new Shell();
		shell.setSize(709, 421);
		shell.setText("Defect Logging");
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		txtDefects = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		txtDefects.setBounds(10, 10, 673, 294);
		txtDefects.setText("PLEASE ENTER DEFECTS HERE. YOU MAY ONLY SUBMIT ONCE." );
		
		
		Button btnSubmitD = new Button(shell, SWT.NONE);
		btnSubmitD.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("null")
			@Override
			public void widgetSelected(SelectionEvent e) {
				String outputText = txtDefects.getText();
				btnSubmitD.setEnabled(false);
				Writer writer = null;

				try {
					File file = new File(
							"X:\\Departments\\QA\\Test\\defects" + name
									+ ".txt");
					BufferedWriter output = new BufferedWriter(
							new FileWriter(file, true));
					output.write(outputText);
					output.close();
					JOptionPane.showMessageDialog(null,
							"Thank you for submitting.  Please let Kevin or James know that you are done.", "Thank you!", JOptionPane.DEFAULT_OPTION);
				} catch (IOException ex) {
					// JOptionPane.showMessageDialog(null, ex);
				} finally {

					try {
						writer.close();
						
					} catch (Exception ex) {
						// JOptionPane.showMessageDialog(null, ex);
					}
				}
				
			}
		});
		btnSubmitD.setBounds(307, 329, 75, 25);
		btnSubmitD.setText("Submit");

		Label lblLightmakerCensusBe = new Label(shlLightmakerCensusBureau,
				SWT.NONE);
		lblLightmakerCensusBe.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_YELLOW));
		lblLightmakerCensusBe.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_BLACK));
		lblLightmakerCensusBe.setFont(SWTResourceManager.getFont("Arial", 13,
				SWT.BOLD));
		lblLightmakerCensusBe.setAlignment(SWT.CENTER);
		lblLightmakerCensusBe.setBounds(191, 10, 302, 26);
		lblLightmakerCensusBe.setText("LIGHTMAKER CENSUS BUREAU");

		txtFirst = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtFirst.setBounds(83, 72, 152, 21);

		txtLast = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtLast.setBounds(83, 99, 152, 21);

		Label lblFirstName = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblFirstName.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_YELLOW));
		lblFirstName
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblFirstName.setBounds(10, 75, 67, 15);
		lblFirstName.setText("First Name*");

		Label lblLastName = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblLastName
				.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblLastName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblLastName.setBounds(10, 102, 67, 15);
		lblLastName.setText("Last Name*");

		txtDOB = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtDOB.setBounds(83, 126, 152, 21);

		Label lblDob = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblDob.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblDob.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblDob.setBounds(10, 129, 55, 15);
		lblDob.setText("DOB*");

		txtSSN = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtSSN.setBounds(83, 153, 152, 21);

		Label lblSsn = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblSsn.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblSsn.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblSsn.setBounds(10, 156, 55, 15);
		lblSsn.setText("SSN*");

		txtEmployed = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtEmployed.setBounds(83, 180, 152, 21);

		Label lblEmployed = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblEmployed
				.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblEmployed.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblEmployed.setBounds(10, 183, 67, 15);
		lblEmployed.setText("Employed?*");

		Label label = new Label(shlLightmakerCensusBureau, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		label.setBounds(0, 322, 664, 2);

		Label label_1 = new Label(shlLightmakerCensusBureau, SWT.SEPARATOR
				| SWT.VERTICAL);
		label_1.setBounds(341, 42, 2, 282);

		String genders[] = { "Male", "Female" };
		Combo cboGender = new Combo(shlLightmakerCensusBureau, SWT.NONE);
		cboGender.setBounds(463, 70, 91, 23);
		cboGender.setItems(genders);

		Label lblGender = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblGender.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblGender.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblGender.setBounds(359, 75, 55, 15);
		lblGender.setText("Gender*");

		txtAddress1 = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtAddress1.setBounds(463, 99, 201, 21);

		Label lblAddress = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblAddress.setBounds(359, 102, 55, 15);
		lblAddress.setText("Address 1*");

		txtAddress2 = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtAddress2.setBounds(463, 126, 201, 21);

		Label lblAddress_1 = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblAddress_1.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_YELLOW));
		lblAddress_1
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblAddress_1.setBounds(359, 129, 55, 15);
		lblAddress_1.setText("Address 2");

		txtCity = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtCity.setBounds(463, 153, 201, 21);

		Label lblCity = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblCity.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblCity.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblCity.setBounds(359, 156, 55, 15);
		lblCity.setText("City*");

		txtState = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtState.setBounds(463, 180, 39, 21);

		Label lblState = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblState.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblState.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblState.setBounds(359, 183, 55, 15);
		lblState.setText("State");

		txtZip = new Text(shlLightmakerCensusBureau, SWT.BORDER);
		txtZip.setBounds(588, 180, 76, 21);

		Label lblZipCode = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblZipCode.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblZipCode.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblZipCode.setBounds(527, 183, 55, 15);
		lblZipCode.setText("Zip Code*");

		Label lblError = new Label(shlLightmakerCensusBureau, SWT.NONE);
		lblError.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblError.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblError.setAlignment(SWT.CENTER);
		lblError.setFont(SWTResourceManager.getFont("Arial", 15, SWT.BOLD));
		lblError.setBounds(83, 375, 499, 26);

		Button btnSubmit = new Button(shlLightmakerCensusBureau, SWT.NONE);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				int i = 0;
				if (txtFirst.getText() == "") {
					i++;
					lblError.setText("Please Enter Your First Name");

				}

				if (txtLast.getText() == "") {
					i++;
					lblError.setText("Please Enter Your Last Name");

				}
				if (txtLast.getText().compareToIgnoreCase("PRIVATE") == 0) { // DEFECT
					i++;
					lblError.setText("Please Enter Your Last Name");

				}

				if (txtDOB.getText() == "") {
					i++;
					lblError.setText("Please Enter Your Date of Birth");
				}
				if (txtSSN.getText() == "") {
					i++;
					lblError.setText("Please Enter Your Social Secutity Nuber"); // DEFECT
				}
				if (txtAddress1.getText() == "") {
					i++;
					lblError.setText("Please Enter Your Street Address");
				}
				if (txtState.getText() == "") {
					i++;
					lblError.setText("Please Enter Your State of Residence");
				}
				if (txtZip.getText() == "") {
					i++;
					lblError.setText("Please Enter Your Zip Code");
				}
				if (txtCity.getText() == "") {
					i++;
					lblError.setText("Please Enter Your City of Residence");
				}
				if (txtEmployed.getText() == "") {
					i++;
					lblError.setText("Please Enter Your Employment Status");
				}
				if (cboGender.getSelectionIndex() == -1) {
					i++;
					lblError.setText("Please Select Your Gender");
				}
				if (i == 0) {
					String outputText = txtFirst.getText() + "  "
							+ txtLast.getText() + "  " + txtDOB.getText()
							+ "  " + txtSSN.getText() + "  "
							+ txtEmployed.getText() + "  "
							+ cboGender.getText() + "  "
							+ txtAddress1.getText() + "  "
							+ txtAddress2.getText() + "  " + txtCity.getText()
							+ "  " + txtState.getText() + "  "
							+ txtZip.getText()
							+ System.getProperty("line.separator");

					Writer writer = null;

					try {
						File file = new File(
								"X:\\Departments\\QA\\Test\\interview" + name
										+ ".txt");
						BufferedWriter output = new BufferedWriter(
								new FileWriter(file, true));
						output.write(outputText);
						output.close();
						JOptionPane.showMessageDialog(null,
								"Submit Successful", "Success",
								JOptionPane.ERROR_MESSAGE);
					} catch (IOException ex) {
						// JOptionPane.showMessageDialog(null, ex);
					} finally {

						try {
							writer.close();
							
						} catch (Exception ex) {
							// JOptionPane.showMessageDialog(null, ex);
						}
					}

					txtFirst.setText("");
					txtLast.setText("");
					txtAddress1.setText("");
					txtAddress2.setText("");
					txtZip.setText("");
					txtCity.setText("");
					txtState.setText("");
					txtEmployed.setText("");
					txtDOB.setText("");
					cboGender.setItems(genders);
					txtSSN.setText("");
					lblError.setText("");
				}
			}
		});
		btnSubmit.setBounds(152, 344, 75, 25);
		btnSubmit.setText("Submit");

		Button btnClear = new Button(shlLightmakerCensusBureau, SWT.NONE);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				txtFirst.setText("");
				txtLast.setText("");
				txtAddress1.setText("");
				// txtAddress2.setText(""); //DEFECT
				txtZip.setText("");
				txtCity.setText("");
				txtState.setText("");
				txtEmployed.setText("");
				txtDOB.setText("");
				cboGender.setItems(genders);
				txtSSN.setText("");
				lblLightmakerCensusBe.setText("Census Bureau"); // DEFECT
				lblError.setText("");
			}
		});
		btnClear.setBounds(455, 344, 75, 25);
		btnClear.setText("Clear");

		Button btnIWouldLike = new Button(shlLightmakerCensusBureau, SWT.NONE);
		btnIWouldLike.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtFirst.setText("PRIVATE");
				txtLast.setText("PRIVATE");
			}
		});
		btnIWouldLike.setBounds(426, 291, 238, 25);
		btnIWouldLike.setText("I would like to keep my name private");

	}
}
