package OOP;

import java.util.HashMap;

import OOP.ProduktVerwaltung.Produkt;


public class Lager {
	
	private HashMap<Produkt, Integer> stock;
	private int aktuell;
	
	public Lager() {
		stock = new HashMap<Produkt, Integer>();
	}
	
	public HashMap<Produkt, Integer> getStock() {
		return stock;
	}
	
	public Integer getProduktInStock(Produkt p) {
		return stock.get(p);
	}
	
	public void incrementStock(Produkt p, int anzahl) {		
		if(stock.containsKey(p)) {
			aktuell = getProduktInStock(p);
			aktuell += anzahl;
			stock.put(p, aktuell);
		} else {
			aktuell = anzahl;
			stock.put(p, aktuell);
		}
	}
	
	public void decrementStock(Produkt p, int anzahl) {		
		if(stock.containsKey(p)) {
			aktuell = getProduktInStock(p);
			
			if(aktuell > 0) {
				aktuell -= anzahl;
				stock.put(p, aktuell);
			} else {
				throw new ProduktException("The amount of product "+p+" is 0.");
			}
		} else {
			throw new ProduktException("Product "+p+ "does not exist yet!");
		}
	}
}
