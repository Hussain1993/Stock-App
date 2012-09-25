package com.Hussain.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.Hussain.CompanyInformation;
import com.Hussain.StockInformation;
/**
 * This is the class where keyboard buttons
 * will be made this class also implements the action
 * listener for the buttons.
 *
 * @author Hussain
 *
 */
public class KeyboardButtons extends JButton implements ActionListener{
	/*
	 * The textField will be used in the action listener, when a button is pressed
	 * the appropriate text will be displayed, the other
	 * reason why the textField is passed to this class 
	 * is for the purpose of validation where the length 
	 * of the text can not be greater than 8 characters long.
	 * The textLabel is also passed as a parameter to this class
	 * so that the retrieved data can be displayed to the user
	 */
	private JTextField textField;
	private JLabel textLabel;
	
	/**
	 * This is the constructor for the class KeyboardButtons
	 * @param buttonLabel This is the name of the button, the string that
	 * is passed to this constructor will be used to display the character
	 * or action that this particular instance of the button will perform.
	 * For example if this variable contains the string "1", "1" will
	 * be displayed as the the label of the button 
	 * 
	 * @param textField This is the text field that will be used to display what
	 * the user has currently typed in.
	 * 
	 * @param textLabel This JLabel will be used for output of the data.
	 */
	public KeyboardButtons(String buttonLabel, JTextField textField, JLabel textLabel){
		super(buttonLabel);//This sets the character or String of characters 
		/*
		 * This sets the name of the button to the same character(s) that are
		 * shown on the button label this property will be used
		 * later in the action listener for the class, where the name of
		 * the button will determine which button was pressed and what action should
		 * be taken next.
		 */
		this.setName(buttonLabel);
		this.textField = textField;
		this.textLabel = textLabel;
		/*
		 * Add this class as an action listener for the new
		 * button that has been created.
		 */
		addActionListener(this);
	}//end constructor for the class KeyboardButtons
	
	/**
	 * This is the method for deleting one character from the user
	 * input text field.
	 */
	public void delete(){
		String currentText = this.textField.getText();
		String result =  currentText.substring(0, currentText.length()-1);
		this.textField.setText(result);
	}//end method delete
	
    /**
     * This is the action listener which has been implemented 
     * for this class.
     */
	@Override
	public void actionPerformed(ActionEvent event) {
		String currentText = this.textField.getText();
		if(this.getName().equals("Del"))//This is the event handler when the 'Delete' button has been pressed
		{
			try{
				/*
				 * Check that there is text to delete.
				 */
				if (currentText.isEmpty())
				{
					/*
					 * Throw a new Exception if there is no text
					 * in the text field. This exception is handled 
					 * in catch statement by the use of a JOptionPane
					 * where the user will be alerted with the following
					 * error message:
					 */
					throw new Exception("The input field is empty");
				}
				else
				{
					delete();
				}
			}
			catch(Exception exception){
				/*
				 * This catch statement is where the exception handling is done, a new
				 * instance of a JOptionPane is made, the ".showMessageDialog" shows a message to the 
				 * user by the use of a dialog box with a single "OK" button. The message that will be displayed on
				 * the dialog box will be the one that was thrown by the Exception and this is shown to the
				 * user by the use of "exception.getMessage()". The String "Error" is the window title of
				 * dialog box. "JOptionPane.ERRORMESSAGE" denotes the icon that will be displayed
				 * on the dialog box
				 */
				JOptionPane.showMessageDialog(null, exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}//end if
		else if(this.getName().equals("Enter"))//This is the event handler when the "Enter" button is pressed
		{
			try{
				if(currentText.isEmpty())
				{
					throw new Exception("The input is empty.");
				}
				else if(currentText.length() > 8)
				{
					throw new Exception("The symbol "+currentText+" is greater than 8");
				}
				else
				{
					/*
					 * Make a new instance of the class StockInformation
					 * so that Yahoo! servers can be queried for the data and
					 * the data can be displayed to the user
					 */
					StockInformation company = new CompanyInformation();
					company.getCompanyInformation(currentText);//Pass the current text the user has typed in as the company ticker
					display(company);
				}
			}//end try
			catch(Exception exception){
				JOptionPane.showMessageDialog(null, exception.getMessage(),"Error message",JOptionPane.ERROR_MESSAGE);
			}//end catch
			textField.setText("");//Clear the text field every time the "Enter" button is pressed
		}//end else if
		else
		{
			/*
			 * This else statement is when the other buttons on the keyboard
			 * have been pressed which are the characters buttons, number
			 * buttons and the full stop button.
			 */
			try{
				if(currentText.length() >= 8)
				{
					throw new Exception("The input can not be greater than 8");
				}
				else
				{
					textField.setText(currentText+this.getName());
				}
			}
			catch(Exception exception){
				JOptionPane.showMessageDialog(null, exception.getMessage(), "Error message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}//end method actionPerformed
	
	/**
	 * This is the method where the information will
	 * be displayed to the user, Note that this method
	 * will only be called if the company exists.
	 * @param quote This is the parameter which is holding 
	 * all the company information and I will use the public 
	 * methods of the StockInformation class to gain
	 * access to this information and display it to the user
	 */
	private void display(StockInformation quote){
		/*
		 * I am using HTML to display the information
		 * to the user so extra formatting features like
		 * making text bold and changing the colour
		 * of text can be achieved 
		 */
		this.textLabel.setText("<html><font size =\"5\" face = \"Lucida Grande\"><b>Company Name: </b>"+quote.getCompanyName()+"<br><b>Price: </b>"+quote.getLastTradingPrice()+quote.getCurrency()+"<br><b>Change: </b><font color ="+quote.getColour()+">"+quote.getPriceChange()+" ("+quote.getPercentileChange()+"%)</font><br><b>Dividend: </b>"+quote.getDividend()+"<br><b>Market Cap: </b>"+quote.getMarketCap()+"<br><b>Name of stock exchange: </b>"+quote.getNameOfStockExchange()+"</font></html>");
	}//end method display
}//end class KeyboardButtons
