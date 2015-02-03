public class Stock {
	
	private final String name;
	private int quantity;
	private double price;
	
	/**
	 * Stock object
	 * @param name: user-defined name of Stock, must be String
	 * @param quantity: number of name stock user owns, must be integer 
	 * @param price: price of stock when added, must be double
	 */
	public Stock(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	/**
	 * Gets name of stock
	 * @return: name of stock, must be String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets quantity of stock user owns
	 * @return: number of stock user owns, must be integer
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Gets price of stock when it was added to portfolio
	 * @return: price of stock when it was added, must be double
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Changes quantity of stock owned by user
	 * @param quantity: number of stock user owns, must be integer
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Changes price stock was bought at (if more stock is bought)
	 * @param price: price in dollars most recent purchase of this stock was at, must be double
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
