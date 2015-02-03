import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public class Portfolio implements Collection<Stock>{
	TreeMap<String,Stock> portfolio = new TreeMap<String,Stock>();
	
	/**
	 * Implements Collection<E> interface
	 */
	@Override
	public boolean add(Stock stock) {
		portfolio.put(stock.getName(), stock);
		return true;
	}
	
	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public boolean addAll(Collection<? extends Stock> portfolio) {
		for(Stock stock:portfolio){
			this.portfolio.put(stock.getName(), stock);
		}
		return true;
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public void clear() {
		portfolio.clear();
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public boolean contains(Object stockname) {
		return portfolio.containsKey(stockname);
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public boolean containsAll(Collection<?> portfolio) {
		for (Object stock:portfolio){
			if (!this.portfolio.containsKey(stock)){
				return false;
			}
		} 
		return true;
	}
	
	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public boolean isEmpty() {
		if (portfolio.size() == 0){
			return true;
		}else{
		return false;}
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public Iterator<Stock> iterator() {
		this.portfolio.values().iterator();
		return null;
	}
	
	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public boolean remove(Object stockname) {
		portfolio.remove(stockname);
		return true;
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public boolean removeAll(Collection<?> portfolio) {
		for(Object stock:portfolio){
			portfolio.remove(stock);
		}
		return true;
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public boolean retainAll(Collection<?> portfolio) {
		for(Object stock:portfolio){
			if (!this.portfolio.containsKey(stock)){
				this.portfolio.remove(stock);
			}
		}
		return true;
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public int size() {
		return portfolio.size();
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public Object[] toArray() {
		return portfolio.values().toArray();
	}

	/**
	 * Implements Collection <E> interface
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		//TODO
		return null;
	}
	
	/**
	 * Fetches stock object by its stock name
	 * @param stockName: name of Stock object being fetched, must be string
	 * @return: Stock object corresponding to specified stock name
	 */
	public Stock get(String stockName) {
		return this.portfolio.get(stockName); 
	}


}
