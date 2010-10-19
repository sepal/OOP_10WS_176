package OOP;

import java.util.HashMap;

import OOP.ProduktVerwaltung.Produkt;

/**
 * Represents a Konfiguration consisting of products (and their quantity).
 *
 */
public class Konfiguration {
	private HashMap<Produkt, Integer> products;
	
	public Konfiguration() {
		products = new HashMap<Produkt, Integer>();
	}
	
	/**
	 * Fuegt ein neues Produkt hinzu.
	 */
	public void addProduct(Produkt p, int quantity) {
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
	public void removeFromStock(Lager l) {
		for (Produkt p: products.keySet()) {
			l.decrementStock(p, products.get(p));
		}
	}
}
