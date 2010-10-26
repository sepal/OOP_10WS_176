package OOP;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import OOP.OrderManagment.Order;
import OOP.ProductFactory.Product;


public class Warehouse extends Location{

	private HashMap<Product, Integer> stock;
	private HashSet<Order> orders;
	
	private int aktuell;
	
	public Warehouse(String name) {
		super(name);
		stock = new HashMap<Product, Integer>();
		orders = new HashSet<Order>();
	}
	
	public HashMap<Product, Integer> getStock() {
		return stock;
	}
	
	public Product getProduct(String namep) throws ProductException{		
		for(Product p: stock.keySet()) {
			if(p.getName().equals(namep)) {
				return p;
			} 
		}
		throw new ProductException("Product " + namep + "does not exist in warehouse" +name+"!");
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
	
	/**
	 * Gibt die Anzahl von einem Produkt zu einem bestimmten Zeitpunkt zur&uuml;ck.
	 * @param p
	 * @param d
	 * @return
	 */
	public int getProductInStock(Product p, Calendar d) {
		Integer ret = stock.get(p);
		if (ret == null) 
			ret = 0;
		
		for (Order o: orders) {
			ret += o.getQuantatiyForWarehouse(p, this, d);
		}
		return ret.intValue();
	}
	
	/**
	 * Eine Bestellungsreferenz hinzuf&uuml;gen.
	 * @param o
	 */
	public void addOrder(Order o) {
		orders.add(o);
	}
	
	/**
	 * Eine Bestellungsreferenz l&ouml;schen.
	 * @param o
	 */
	public void removeOrder(Order o) {
		orders.remove(o);
	}
	
	public void incrementStock(Product p, int anzahl) {
		if(stock.containsKey(p)) {
			stock.put(p, getProductInStock(p)+anzahl);
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
