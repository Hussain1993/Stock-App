package com.Hussain;
/**
 * This is the class where the Yahoo! servers will be queried
 * for the data for the company that the user has entered into the
 * text field. This class extends the abstract class <code>StockInformation</code> and this
 * class provides a concrete implementation of the method <code>getCompanyInformation()</code>
 * @author Hussain
 *
 */
public class CompanyInformation extends StockInformation {
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.Hussain.StockInformation#getCompanyInformation(java.lang.String)
	 */
	public void getCompanyInformation(String companyTicker) throws Exception {
		/*
		 * This is where the company information is being received from the 
		 * Yahoo! servers, Yahoo! returns the information requested in the order 
		 * that is specified, this is the order in which I am receiving the 
		 * data: 
		 *  1. Last Trading Price
		 *  2. Previous Trading Price
		 *  3. Dividend
		 *  4. Market Cap
		 *  5. Name of stock exchange that the company is listed
		 *  6. Error Indication that is returned from  the Yahoo! servers this will
		 *  be used to check if the company exists
		 *  7. Finally the last piece of data that is retrieved is the company
		 *  name, The reason the company name is last is because of companies that 
		 *  themselves contain a comma "," in their name, using the simple
		 *  split function will not work, there are two ways of doing the
		 *  split function one that only takes one parameter which is the
		 *  regular expression which will be used to split the string. The other
		 *  method has two parameters, as before the first parameter is the regular
		 *  expression but the other parameter is an Integer where this integer
		 *  represents how many times this split function should be done to the string
		 *  and because the company name is the last thing in the string I can 
		 *  use the second split method telling it to stop after the 
		 *  penultimate comma, therefore if a company has a comma in the name
		 *  the split function will not reach this comma as it has already stopped. This method still
		 *  works if the company does not contain a comma for example Google
		 */
		String data = URLReader.readURL("http://finance.yahoo.com/d/quotes.csv?s="+companyTicker+"&f=l1pdj1xe1n");
		if(data != null)
		{
			/*
			 * Replace all the quotation marks and the "\n" form feed
			 * from the string with the empty String literal "" 
			 */
			data = data.replaceAll("\"", "").replaceAll("\n", "");
			/*
			 * Split the data that has been returned by the server whenever a
			 * "," (comma) is found, the protected String array that was 
			 * declared in the class StockInformation will be populated
			 * with the company information, The integer value 
			 * that is passed as a parameter is the number
			 * of times the split function should be carried out on
			 * the particular string
			 */
			this.companyData = data.split(",",7);
			/*
			 * Check that the company exists, this method will
			 * throw an exception if the company is not found
			 */
			this.checkExists(companyTicker);
		}
		else
		{
			/*
			 * This section of the code will only be entered
			 * if the data returned by the servers is a null, there maybe
			 * many reasons why the server returned a null but one
			 * possibility is that they are not connected to the 
			 * Internet so this error message tells the user that
			 * no information was received and they should check their
			 * Internet connection.
			 */
			throw new Exception("There was an error reciving the data, check the internet connnection");
		}
		/*
		 * All the exceptions that thrown by this method (getCompanyInformation()) and the 
		 * checkExists() will be passed to the caller of the method, in this
		 * case it will be the class KeyboardButtons where these methods will be called 
		 * from the try-catch of the "Enter" button event handler and this will
		 * be handled by the catch statement where the JOptionPane will show the 
		 * relevant error message
		 */
	}//end method getCompanyInformation
}//end class CompanyInformation
