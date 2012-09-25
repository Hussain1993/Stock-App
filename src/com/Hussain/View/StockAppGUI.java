package com.Hussain.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * TODO LIST:
 * DO COMMENTS
 * REMOVE THE TO DO LIST
 * 
 * This is the class for the main Graphical User Interface
 * for the stock application, In this this class I will
 * declare the buttons for the on-screen keyboard and then displaying
 * them on the screen with a grid layout. In this class I will
 * also declare a number of panels that will be used for the layout of
 * overall application. Note that this class extends the class JFrame.
 * @author Hussain
 *
 */
public class StockAppGUI extends JFrame{
	/*
	 * The textField will be used to hold the user
	 * input, and the textLabel will be used to output 
	 * the data to the user.
	 */
	private JTextField textField;
	private JLabel textLabel;
	private JScrollPane pane;
	/*
	 * Declarations for the panels of the user 
	 * interface that will be used for layout of
	 * the application
	 */
	private JPanel panelCenter;
	private JPanel panelSouth;
	
    /**
     * This is the constructor for the
     * class StockAppGUI.
     */
	public StockAppGUI(){
		super("Simple Stock Application");
		this.setPreferredSize(new Dimension(700,400));//Set a preferred for the window 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		initWidgets();//This method is called to add all the widgets to the top level window
		pack();
	}//end constructor for the class
	
	/**
	 * This is the method that will be called from
	 * the constructor of the class @see StockAppGUI. This is 
	 * the method where all the required widgets will be
	 * created and added to the top level window
	 */
	public void initWidgets(){
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		this.add(panelSouth,BorderLayout.SOUTH);
		this.add(panelCenter,BorderLayout.CENTER);
		
		panelCenter.setLayout(new BorderLayout());
		panelSouth.setLayout(new GridLayout(4,11));//This is the Grid Layout for the Keyboard
		
		textField = new JTextField();
		textLabel = new JLabel();
		pane = new JScrollPane(textLabel);
		/*
		 * Set the alignment position of the text of the 
		 * text label so that it more natural other than the text
		 * being in the center of the label, also set the background
		 * colour of the text label to white
		 */
		textLabel.setVerticalAlignment(JLabel.TOP);
		textLabel.setBackground(Color.WHITE);
	
		textField.setEditable(false);
		panelCenter.add(pane,BorderLayout.CENTER);
		panelCenter.add(textField,BorderLayout.SOUTH);
		
		/*
		 * This is the section of code where I am adding the buttons
		 * to the user interface, The reason why there are buttons
		 * that are not set to visible is to make sure that the 
		 * layout of the keyboard follows the natural layout of an
		 * actual keyboard the buttons that are not set to visible 
		 *  act as a kind of 'padding'.
		 */
		panelSouth.add(new JButton()).setVisible(false);
		/*
		 * These buttons don't need access to the text label
		 * they only need access to the text field, this is the
		 * reason why the third parameter is set to the "null", but
		 * on the other hand the "Enter" button does need access
		 * to the text label that will be used for output
		 * as the class KeyboardButtons contains a method to
		 * display the information to the user so in this case
		 * the third parameter will not be set to null 
		 */
		panelSouth.add(new KeyboardButtons("1", textField,null));
		panelSouth.add(new KeyboardButtons("2", textField,null));
		panelSouth.add(new KeyboardButtons("3", textField,null));
		panelSouth.add(new KeyboardButtons("4", textField,null));
		panelSouth.add(new KeyboardButtons("5", textField,null));
		panelSouth.add(new KeyboardButtons("6", textField,null));
		panelSouth.add(new KeyboardButtons("7", textField,null));
		panelSouth.add(new KeyboardButtons("8", textField,null));
		panelSouth.add(new KeyboardButtons("9", textField,null));
		panelSouth.add(new KeyboardButtons("0", textField,null));
		panelSouth.add(new JButton()).setVisible(false);
		panelSouth.add(new JButton()).setVisible(false);
		panelSouth.add(new KeyboardButtons("Q", textField,null));
		panelSouth.add(new KeyboardButtons("W", textField,null));
		panelSouth.add(new KeyboardButtons("E", textField,null));
		panelSouth.add(new KeyboardButtons("R", textField,null));
		panelSouth.add(new KeyboardButtons("T", textField,null));
		panelSouth.add(new KeyboardButtons("Y", textField,null));
		panelSouth.add(new KeyboardButtons("U", textField,null));
		panelSouth.add(new KeyboardButtons("I", textField,null));
		panelSouth.add(new KeyboardButtons("O", textField,null));
		panelSouth.add(new KeyboardButtons("P", textField,null));
		panelSouth.add(new KeyboardButtons("Del", textField,null));
		panelSouth.add(new JButton()).setVisible(false);
		panelSouth.add(new KeyboardButtons("A", textField,null));
		panelSouth.add(new KeyboardButtons("S", textField,null));
		panelSouth.add(new KeyboardButtons("D", textField,null));
		panelSouth.add(new KeyboardButtons("F", textField,null));
		panelSouth.add(new KeyboardButtons("G", textField,null));
		panelSouth.add(new KeyboardButtons("H", textField,null));
		panelSouth.add(new KeyboardButtons("J", textField,null));
		panelSouth.add(new KeyboardButtons("K", textField,null));
		panelSouth.add(new KeyboardButtons("L", textField,null));
		panelSouth.add(new KeyboardButtons("Enter", textField,textLabel));
		panelSouth.add(new JButton()).setVisible(false);
		panelSouth.add(new JButton()).setVisible(false);
		panelSouth.add(new JButton()).setVisible(false);
		panelSouth.add(new KeyboardButtons("Z", textField,null));
		panelSouth.add(new KeyboardButtons("X", textField,null));
		panelSouth.add(new KeyboardButtons("C", textField,null));
		panelSouth.add(new KeyboardButtons("V", textField,null));
		panelSouth.add(new KeyboardButtons("B", textField,null));
		panelSouth.add(new KeyboardButtons("N", textField,null));
		panelSouth.add(new KeyboardButtons("M", textField,null));
		panelSouth.add(new KeyboardButtons(".", textField,null));
	}//end method initWidgets
}//end class
