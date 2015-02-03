import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class StockMarketApp {

	static Scanner	sc;
	public static final Portfolio portfolio = new Portfolio();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input;
		System.out.println("Welcome! What would you like to do today?");
		System.out.println("- Add Stock: type 'Add'");
		System.out.println("- Remove Stock: type 'Remove'");
		System.out.println("- Check Info: type 'Info'");
		System.out.println("- Exit: type 'Exit'");
		
		input = sc.next();
		switch(input){
		case "Add":
			addStock();
			break;
		case "Remove":
			removeStock();
			break;
		case "Info":
			System.out.println("Enter stock name to check info on");
			input = sc.next();
			infoStock(input);
			break;
		case "Exit":
			System.exit(0);
		default:
			System.out.println("Invalid command. Please try again:");
			}

	}
	
	/**
	 * Asks user for stock name, displays price, asks user for number of stock to add, adds stock
	 * If stock exists, loads stock, otherwise creates new stock object.
	 */
	private static void addStock() {
		System.out.println("What is the name of the stock you would like to add?");
		String stockName = sc.next();
		String symbol = symbol(stockName);
		double price = getPrice(symbol);
		System.out.println("Current price of "+stockName+" stock is: "+price);
		System.out.println("How many of " + stockName + " stock would you like to add?");
		String quant = sc.next();
		int quantity = Integer.parseInt(quant);
		if (!portfolio.contains(stockName)){
			Stock stock = new Stock(stockName, quantity, price);
			portfolio.add(stock);
		}else{
		Stock stock = portfolio.get(stockName);
		stock.setQuantity(stock.getQuantity() + quantity);
		portfolio.add(stock);
		}
		double total = price * quantity;
		System.out.println("Your cost for this transaction is: $"+total);
	}
	
	/**
	 * Asks user for stock name, displays current price, asks user for number of stock to remove, removes stock
	 * If user asks for more than available number of stock to be removed, removes all stock
	 */
	private static void removeStock() {
		System.out.println("What is the name of the stock you would like to remove?");
		String stockName = sc.next();
		String symbol = symbol(stockName);
		double price = getPrice(symbol);
		System.out.println("Current price of "+stockName+" stock is: "+price);
		System.out.println("How much of " + stockName + "stock would you like to remove?");
		String quant = sc.next();
		int quantity = Integer.parseInt(quant);
		Stock stock = portfolio.get(stockName);
		if(stock.getQuantity()<quantity){
			System.out.println("Warning! More stock than you have. Removing all stock");
			quantity = stock.getQuantity();
		}
		stock.setQuantity(stock.getQuantity() - quantity);
		stock.setPrice(price);
		portfolio.add(stock);
	} 
	
	/**
	 * Prints quantity of specified stock held by user along with total profit/loss since purchase in dollars.
	 * @param stockName: User-specified name of stock, must be type String
	 **/
	private static void infoStock(String stockName) {
		Stock stock = portfolio.get(stockName);
		String name = stock.getName();
		String symbol = symbol(stockName);
		int quantity = stock.getQuantity();
		double price = stock.getPrice();
		double cPrice = getPrice(symbol);
		double profit = cPrice*quantity - price*quantity;
		if (profit > 0) {
			System.out.println("You have "+quantity+" of "+name+" stock and have made $"+profit+" since you last bought its stock.");
		}else{
			System.out.println("You have "+quantity+" of "+stockName+" stock and have lost $"+Math.abs(profit)+" since you last bought its stock.");
		}
		
	}
	
	/**
	 * Converts specified stock name into its trading symbol
	 * @param stockName: User-specified name of stock, must be type String
	 * @return: Specified stock's trading symbol, must be type String
	 */
	private static String symbol(String stockName) {
		Document doc = Jsoup.parse("http://d.yimg.com/autoc.finance.yahoo.com/autoc?query="+stockName+"&callback=YAHOO.Finance.SymbolSuggest.ssCallback");
		String str = doc.toString();
		String delims = "[.,:]+";
		String[] terms = str.split(delims);
		return terms[8];
	}
	
	/**
	 * Fetches latest closing price of specified stock (symbol) using Google Finance and JSoup 
	 * @param symbol: Trading symbol for user-specified stock, must be type String
	 * @return: Current closing price of specified stock in dollars, must be type Double
	 */
	private static double getPrice(String symbol) {
		Document doc = Jsoup.parse("http://finance.google.com/finance/info?client=ig&q=NASDAQ%3a"+symbol);
		String str = doc.toString();
		String delims = "[:,]+";
		String[]terms = str.split(delims);
		return Double.parseDouble(terms[7]);
		
	}
			
}


