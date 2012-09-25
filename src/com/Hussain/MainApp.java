package com.Hussain;
import com.Hussain.View.StockAppGUI;

/**
 * This is the class where the main method of the application
 * is located and it is called to make a new instance
 * of the application where the Graphical User Interface will be
 * created and it will be set to visible so that the user can see 
 * the window
 * @author Hussain
 *
 */
public class MainApp {
	public static void main(String [] args){
		new StockAppGUI().setVisible(true);
	}//end main
}//end class
