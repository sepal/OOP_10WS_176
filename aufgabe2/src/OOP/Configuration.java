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
	 * Fuegt ein neues Produkt hinzu.
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
	 * Verringert den Lagerbestand im angegebenen Lager.
	 */
	public void removeFromStock(Warehouse l) {
		for (Product p: products.keySet()) {
			l.decrementStock(p, products.get(p));
		}
	}
}
