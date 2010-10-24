package OOP;

import java.util.HashMap;

import OOP.ProductFactory.Product;


public class Warehouse {
	private HashMap<Product, Integer> stock;
	private int aktuell;
	private String name;
	
	public Warehouse(String name) {
		stock = new HashMap<Product, Integer>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public HashMap<Product, Integer> getStock() {
		return stock;
	}
	
	/**
	 * Get number of Products p in stock
	 * 
	 * @param p Product, of which the number of items in stock should be returned
	 * @return Number of Products p in stock. If p does not exist in the warehouse, this will return 0 too.
	 */
	public int getProductInStock(Product p) {
		Integer ret = stock.get(p);
		if (ret == null) 
			ret = 0;
		return ret.intValue();
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
	
	public void decrementStock(Product p, int anzahl) throws ProductException {		
		if(stock.containsKey(p)) {
			aktuell = getProductInStock(p);
			
			if(aktuell >= anzahl) {
				aktuell -= anzahl;
				stock.put(p, aktuell);
			} else {
				throw new ProductException("The are only "+aktuell+" items of "+p.getName()+" in stock, cannot take away "+anzahl+"!");
			}
		} else {
			throw new ProductException("Product " + p.getName() + "does not exist in warehouse" +name+"!");
		}
	}
}
