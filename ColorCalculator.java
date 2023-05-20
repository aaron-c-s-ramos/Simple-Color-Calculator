//RAMOS, AARON CHRISTOPHER S.
//1ITG - Monday, 12PM to 3PM - Thursday, 1PM to 4PM
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;

// The Color Calculator is a simple GUI application for creating and displaying
// colors based on user input for Red, Green, Blue, and Alpha values.
public class ColorCalculator
{
		private JFrame frame;
		private JPanel pTop, pBottom;

		private JLabel label1, label2, label3, label4;
		private JTextField textField1, textField2, textField3, textField4;
		private JButton btnCompute, btnClear;

		// Constructs and initializes the User Interface components.
		private ColorCalculator()
		{
			frame = new JFrame();

			pTop = new JPanel();
			pBottom = new JPanel();

			label1 = new JLabel("Red:");
			label2 = new JLabel("Green:");
			label3 = new JLabel("Blue:");
			label4 = new JLabel("Alpha:");

			textField1 = new JTextField("0", 12);
			textField2 = new JTextField("0", 12);
			textField3 = new JTextField("0", 12);
			textField4 = new JTextField("0", 12);

			btnCompute = new JButton("Compute");
			btnClear = new JButton("Clear");
		}

		// Sets up the application and adds event listeners to the UI components.
		private void startApp()
		{
			frame.setTitle("Aaron's Color Calculator");
			frame.setBounds(0, 0, 350, 400);

			pBottom = new JPanel()
			{
				private static final long serialVersionUID = -4536560645441024715L;

				@Override
				protected void paintComponent(Graphics g)
			    {
			        g.setColor(getBackground());
			        g.fillRect(0, 0, getWidth(), getHeight());
			        super.paintComponent(g);
			    }
			};

			pBottom.setOpaque(false);

			setup();

			// Add event listener for closing the application.
			frame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent we)
				{
					System.exit(0);
				}
			});

			// Add event listeners for text fields.
			textField1.addFocusListener(new FocusCheckAdapter(textField1, label1));
			textField2.addFocusListener(new FocusCheckAdapter(textField2, label2));
			textField3.addFocusListener(new FocusCheckAdapter(textField3, label3));
			textField4.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseExited(MouseEvent me)
				{
					textFieldCheck(textField4, label4);
				}
			});

			// Add action listeners for buttons.
			btnCompute.addActionListener(
			ae -> {
					try
					{
						pBottom.setBackground(new Color(
								parseInt(textField1.getText()),
								parseInt(textField2.getText()),
								parseInt(textField3.getText()),
								parseInt(textField4.getText())));
					}
					catch (Exception e)
					{
						if (e instanceof NumberFormatException)
						{
							showMessageDialog(null, "Please enter a Whole number for all inputs", "Not a Number", ERROR_MESSAGE);
						}
						else if (e instanceof IllegalArgumentException)
						{
							showMessageDialog(null, "Please enter a number from 1 to 255 for all inputs", "Out of Range", ERROR_MESSAGE);
						}
					}
			});

			btnClear.addActionListener(
			ae -> {
					textField1.setText("0");
					textField2.setText("0");
					textField3.setText("0");
					textField4.setText("0");
					pBottom.setBackground(new Color(0, 0, 0, 0));
			});
		}

		// Validates the input in the given JTextField and displays an
		// error message if the input is not a valid color component value.
		private static void textFieldCheck(JTextField checkField, JLabel checkLabel)
		{
			try
			{
				new Color(parseInt(checkField.getText()), 0, 0);
			}
			catch (Exception e)
			{
				if (e instanceof NumberFormatException)
				{
					showMessageDialog(null, "Please enter a Whole number for " + checkLabel.getText().substring(0, checkLabel.getText().length() - 1), "Not a Number", ERROR_MESSAGE);
				}
				else if (e instanceof IllegalArgumentException)
				{
					showMessageDialog(null, "Please enter a number from 1 to 255 for " + checkLabel.getText().substring(0, checkLabel.getText().length() - 1), "Out of Range", ERROR_MESSAGE);
				}
				else
				{
					e.getStackTrace();
					e.printStackTrace();
				}

				checkField.setText("0");
				checkField.requestFocus();
			}
		}

		// A FocusAdapter class that checks the validity
		// of a text field's value and handles exceptions.
		private class FocusCheckAdapter extends FocusAdapter
		{
			JTextField focusFieldCheck;
			JLabel focusLabelCheck;

			private FocusCheckAdapter(JTextField focusFieldCheck, JLabel focusLabelCheck)
			{
				this.focusFieldCheck = focusFieldCheck;
				this.focusLabelCheck = focusLabelCheck;
			}

			@Override
			public void focusLost(FocusEvent fe)
			{
				textFieldCheck(focusFieldCheck, focusLabelCheck);
			}
		}

		// Sets up the GUI components and layout,
		// and adds the UI components to the frame.
		private void setup()
		{
			frame.setLayout(new GridLayout(2, 1));
			frame.add(pTop);
			frame.add(pBottom);
			frame.setVisible(true);

			pTop.setLayout(new GridLayout(5, 2));
			pTop.add(label1);
			pTop.add(textField1);
			pTop.add(label2);
			pTop.add(textField2);
			pTop.add(label3);
			pTop.add(textField3);
			pTop.add(label4);
			pTop.add(textField4);
			pTop.add(btnCompute);
			pTop.add(btnClear);
		}

		// Starts the Color Calculator application with a design
		// only mode where event listeners are not enabled.
		private void startAppDesign()
		{
			frame.setTitle("No Event Color Calculator");
			frame.setBounds(350, 0, 350, 400);

			setup();
		}

		//The main method of the Color Calculator that starts the application.
		public static void main(String args[])
		{
			ColorCalculator cc = new ColorCalculator();
			cc.startApp();

			ColorCalculator ccd = new ColorCalculator();
			ccd.startAppDesign();
		}
}