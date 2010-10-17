package OOP;

import java.util.HashMap;

import OOP.ProduktVerwaltung.Produkt;

/**
 * 
 * @author sebastian
 *
 */
public class Konfiguration {
	private HashMap<Produkt, Integer> products;
	
	Konfiguration() {
		products = new HashMap<Produkt, Integer>();
	}
	
	/**
	 * Fuegt ein neues Produkt hinzu.
	 */
	public void addProduct(Produkt p, int quantity) {
		if (products.containsKey(p)) {
			int current = products.get(p);
			current += quantity;
			products.put(p, current);
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
			int quantity = products.get(p);
			l.decrementStock(p, quantity);
		}
	}
}
