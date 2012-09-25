package com.Hussain;
import java.math.BigDecimal;

/**
 * This is the abstract class for receiving the company data
 * from the Yahoo! servers. In this this class there are methods 
 * for retrieving the information from the array once the server 
 * has been queried, there are also other concrete methods like
 * <code>getPriceChange()</code> that will calculate the price change of the
 * company and return the result as a string. The reason
 * why the method <code>getCompanyInformation</code> is an abstract 
 * method is because there may be different implementations of this
 * method for example for a single company or for multiple companies
 * @author Hussain
 *
 */
public abstract class StockInformation {
	/*
	 * This is the String array that will be populated 
	 * with the company data, it is protected because there
	 * will be a subclass of this class which will need access
	 * this variable.
	 */
	protected String [] companyData;
	/**
	 * This is the method for querying the Yahoo! servers and receiving the
	 * data and populating the companyData array.
	 * @param companyTicker This is the user input that they have entered into the
	 * text field that is located on the top level window.
	 * @throws Exception This method will throw an exception if the
	 * data that is returned by the servers is a null this may indicate
	 * that there is problem with the Internet connection.
	 */
	public abstract void getCompanyInformation(String companyTicker)throws Exception;
	
	/**
	 * This is the method to return the company name that 
	 * is returned by the servers
	 * @return Company Name
	 */
	public String getCompanyName(){
		return this.companyData[6];
	}//end method getCompanyName
	
	/**
	 * This method returns the last trading
	 * price of the company, I have noticed that sometimes
	 * that the Yahoo! servers will return a last price that
	 * is rounded to more than 2 decimal places for example it will
	 * return 600.123, this is the not the case every time that the
	 * server is queried but to stay consistent with the way that the
	 * price change will be displayed (2 decimal places) this method
	 * will also round the last trading price to 2 decimal places.
	 * @return The last trading price of the company, that is
	 * gained from the Yahoo! servers.
	 */
	public String getLastTradingPrice(){
		return this.roundNumber(Double.parseDouble(companyData[0]));
	}//end method getLastTradingPrice
	
	/**
	 * This returns the previous price of the
	 * company
	 * @return Previous Trading price
	 */
	public String getPreviousPrice(){
		return this.companyData[1];
	}//end method getPreviousPrice
	
	/**
	 * This will return the dividend for the company
	 * from the array companyData
	 * @return Dividend of the company
	 */
	public String getDividend(){
		return this.companyData[2];
	}//end method getDividend
	
	/**
	 * This is the Market Cap for the company
	 * @return The market capitalisation for the company
	 */
	public String getMarketCap(){
		return this.companyData[3];
	}//end method getMarketCap
	
	/**
	 * This will return the name of the
	 * stock exchange that the company is listed on
	 * @return The name of the stock exchange that the
	 * company is listed on
	 */
	public String getNameOfStockExchange(){
		return this.companyData[4];
	}//end method getNameOfStockExchange
	
	/**
	 * This is the error indication that returned
	 * by the server, it will be used to check to see
	 * if the company exists
	 * @return The error indication that is returned by the
	 * server
	 */
	public String getErrorIndication(){
		return this.companyData[5];
	}//end method getErrorIndication
	
	/**
	 * This method calculates the price change for the company
	 * @return The price change of the company as a string, the price
	 * will also be rounded to 2 decimal places.
	 */
	public String getPriceChange(){
		double currentPrice = Double.parseDouble(this.getLastTradingPrice());
		double previousPrice = Double.parseDouble(this.getPreviousPrice());
		double priceChange = currentPrice - previousPrice;
		return roundNumber(priceChange);//Round the number
	}//end method getPriceChange
	
	/**
	 * This method will calculate the percentile change
	 * for the company
	 * @return The percentile change rounded to 2 decimal
	 * places as a String
	 */
	public String getPercentileChange(){
		double currentPrice = Double.parseDouble(this.getLastTradingPrice());
		double previousPrice = Double.parseDouble(this.getPreviousPrice());
		double percentileChange = ((currentPrice - previousPrice)/previousPrice)*100;
		return roundNumber(percentileChange);
	}//end method getPercentileChange
	
	/**
	 * This method will round a number to 2 decimal places
	 * @param numberToRound This is the number that should be rounded to 2 decimal places
	 * @return The string representation of the number after it has been
	 * rounded to 2 decimal places.
	 */
	public String roundNumber(double numberToRound){
		/*
		 * This method uses a class from the Java Math class
		 * called the BigDecimal, this class allows numbers
		 * to be rounded to the desired number of places by using 
		 * the method setScale().
		 */
		BigDecimal number = new BigDecimal(numberToRound);//Make numberToRound into an BigDecimal
		/*
		 * This will round the BigDecimal 'number' to 2 decimal places and
		 * the static field of the BigDecimal class ROUND_HALF_UP is the normal rounding
		 * method i.e. 0.5 will be rounded to 1 and 0.4 will not be rounded.
		 * and return the rounded number as a String
		 */
		return number.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}//end method roundNumber
	
	/**
	 * This is will return the currency of the
	 * stock price
	 * @return The currency of the price share in relation to the
	 * location of the stock exchange that the company is listed
	 */
	public String getCurrency(){
		String currency = "";
		if(this.getNameOfStockExchange().equals("NasdaqNM") || this.getNameOfStockExchange().equals("NYSE"))
		{
			currency = "$";
		}
		else if(this.getNameOfStockExchange().equals("Brussels") || this.getNameOfStockExchange().equals("Paris"))
		{
			currency = "EUR";
		}
		else if(this.getNameOfStockExchange().equals("HKSE"))
		{
			currency = "HK$";
		}
		else if(this.getNameOfStockExchange().equals("London"))
		{
			currency = "p(ence)";
		}
		else if(this.getNameOfStockExchange().equals("SES"))
		{
			currency = "S$";
		}
		return currency;
	}//end method getCurrency
	
	/**
	 * This method will be used when the data is being displayed
	 * to the user, where colour of the text will depend on the
	 * price change, whether it is positive, negative or there was no
	 * change
	 * @return This returns the a colour depending on the price change
	 * of the company, this property will be used when displaying the output
	 * to the user, this will be done by the use of HTML. The method will
	 * return "Red" if the price change is negative, "Green" if the price
	 * change is positive and "Black" if there has been no price change.
	 */
	public String getColour(){
		double priceChange = Double.parseDouble(this.getPriceChange());//Get the price change
		String colour = "";
		if(priceChange < 0)
		{
			colour = "#FF0000";//This is the colour "Red" in HTML
		}
		else if(priceChange > 0)
		{
			colour = "#00FF00";//This is the colour "Green" in HTML
		}
		else
		{
			colour = "#000000";//This is the colour "Black" in HTML
		}
		return colour;
	}//end method getColour
	
	/**
	 * This is the method that will be used
	 * to check if a company exists.
	 * @param companyTicker This is the company ticker that the user has typed into
	 * input text field
	 * @throws Exception This method throws an exception if the company ticker
	 * is not found
	 */
	public void checkExists(String companyTicker)throws Exception{
		/*
		 * When the Yahoo! servers are queried, when a company that does not exists the server
		 * will return the following message: "No such ticker symbol"
		 */
		if(this.getErrorIndication().contains("No such ticker symbol."))
		{
			/*
			 * Throw an exception to the user alerting them
			 * that the company ticker that they are trying to pass
			 * does not exists
			 */
			throw new Exception("The symbol "+companyTicker+" was not found");
		}
	}//end method checkExists
}//end class StockInformation
