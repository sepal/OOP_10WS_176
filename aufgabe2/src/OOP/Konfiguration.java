package OOP;

import java.util.HashMap;

import OOP.ProduktVerwaltung.Produkt;

/**
 * Represents a Konfiguration consisting of products (and their quantity).
 *
 */
public class Konfiguration {
	private HashMap<ProduktGruppenMitglied, Integer> products;
	
	public Konfiguration() {
		products = new HashMap<ProduktGruppenMitglied, Integer>();
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
	
	//public void addProduktGruppe()??
	
	/**
	 * Verringert den Lagerbestand im angegebenen Lager.
	 */
	public void removeFromStock(Lager l) {
		for (ProduktGruppenMitglied p: products.keySet()) {
			if(p instanceof Produkt) {
				l.decrementStock((Produkt) p, products.get(p));
			} else if(p instanceof ProduktGruppe) {
				Produkt pc = p.getCheapest(l);
				l.decrementStock(pc, products.get(p));
			}
		}
	}
}
