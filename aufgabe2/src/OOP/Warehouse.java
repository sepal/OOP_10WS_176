package OOP;

import java.util.HashMap;

import OOP.ProductFactory.Product;


public class Warehouse {
	
	private HashMap<Product, Integer> stock;
	private int aktuell;
	
	public Warehouse() {
		stock = new HashMap<Product, Integer>();
	}
	
	public HashMap<Product, Integer> getStock() {
		return stock;
	}
	
	public Integer getProductInStock(Product p) {
		return stock.get(p);
	}
	
	public void incrementStock(Product p, int anzahl) {		
		if(stock.containsKey(p)) {
			aktuell = getProductInStock(p);
			aktuell += anzahl;
			stock.put(p, aktuell);
		} else {
			aktuell = anzahl;
			stock.put(p, aktuell);
		}
	}
	
	public void decrementStock(Product p, int anzahl) {		
		if(stock.containsKey(p)) {
			aktuell = getProductInStock(p);
			
			if(aktuell > 0) {
				aktuell -= anzahl;
				stock.put(p, aktuell);
			} else {
				throw new ProductException("The amount of product "+p+" is 0.");
			}
		} else {
			throw new ProductException("Product " + p.getName() + "does not exist yet!");
		}
	}
}
