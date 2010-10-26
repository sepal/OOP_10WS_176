package OOP;

import java.util.HashMap;

import OOP.ProductFactory.Product;

/**
 * Represents a Konfiguration consisting of products (and their quantity).
 *
 */
public class Configuration {
	private HashMap<Product, Integer> products;
	
	public Configuration() {
		products = new HashMap<Product, Integer>();
	}
	
	/**
	 * Adds a new product
	 */
	public void addProduct(Product p, int quantity) {
		if (products.containsKey(p)) {
			products.put(p, products.get(p) + quantity);
		}
		else {
			products.put(p, quantity);
		}
	}
	
	/**
	 * Decreases the stock of the products in the configuration in the
	 * selected warehouse
	 */
	public void removeFromStock(Warehouse w) {
		for (Product p : products.keySet()) {
			w.decrementStock(p, products.get(p));
		}
	}
	
	/**
	 * Returns the number of times this configuration could be assembled
	 * considering the amount of available products in the warehouse
	 * 
	 * @param w Warehouse from which the products should be taken
	 * @return The number of configurations that could be built
	 */
	public int getConfigurationStock(Warehouse w) {
		int ret = Integer.MAX_VALUE, warecnt=0, pneed=0;
		
		for (Product p : products.keySet()) {
			warecnt = w.getProductInStock(p);
			pneed = products.get(p);
			if (warecnt < pneed) {
				// If one item is not in stock, we can build 0 configurations
				return 0;
				
			/* The item (times amount needed) that is in stock the least, 
			   determines how often you can build the configuration */
			} else if ((warecnt / pneed) < ret) {
				ret = warecnt / pneed;
			}
		}
		
		return ret;
	}
}
