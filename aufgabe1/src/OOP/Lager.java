package OOP;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import OOP.ProduktVerwaltung.Produkt;


public class Lager {
	
	private HashMap<Produkt, Integer> stock;
	private int aktuell;
	
	private Lager() {
		stock = new HashMap<Produkt, Integer>();
	}
	
	/*muss besser gehen...*/
	public Set<Produkt> getStock() {
		return stock.keySet();
	}
	
	public Integer getProduktInStock(Produkt p) {
		return stock.get(p);
	}
	
	public void incrementStock(Produkt p, int anzahl) {
		aktuell = getProduktInStock(p);
		
		if(stock.containsKey(p)) {
			aktuell += anzahl;
			stock.put(p, aktuell);
		} else {
			throw new ProductDoesNotExistException("Product "+p+ "does not exist yet!");
		}
	}
	
	public void decrementStock(Produkt p, int anzahl) {
		aktuell = getProduktInStock(p);
		
		if(stock.containsKey(p)) {
			aktuell -= anzahl;
			stock.put(p, aktuell);
		} else {
			throw new ProductDoesNotExistException("Product "+p+ "does not exist yet!");
		}
	}
	
//TODO: incrementStock(Produkt, int Anzahl)
//TODO: decrementStock(Produkt, int Anzahl)
//TODO: getStock(Produkt), return Hashmap
}
