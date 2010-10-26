package OOP;

import java.util.HashMap;

import OOP.ProductFactory.Product;

/**
 * Represents a Konfiguration consisting of products (and their quantity).
 *
 */
public class Konfiguration {
	private HashMap<ProductGroupMember, Integer> products;
	
	public Konfiguration() {
		products = new HashMap<ProductGroupMember, Integer>();
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
	
	//public void addProduktGruppe()??
	
	/**
	 * Verringert den Lagerbestand im angegebenen Lager.
	 */
	public void removeFromStock(Warehouse w) {
		for (ProductGroupMember p: products.keySet()) {
			if(p instanceof Product) {
				w.decrementStock((Product) p, products.get(p));
			} else if(p instanceof ProductGroup) {
				Product pc = p.getCheapest(w);
				w.decrementStock(pc, products.get(p));
			}
		}
	}
}
