package OOP;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

import OOP.ProductFactory.Product;

public class Warehouse extends Location implements Deletable {
	private static final long serialVersionUID = 5143415940118316820L;

	private HashMap<Product, Integer> stock;
	private HashSet<Order> orders;
	private boolean hasBeenDeleted;
	private int aktuell;

	public Warehouse(String name) {
		super(name);

		stock = new HashMap<Product, Integer>();
		orders = new HashSet<Order>();
	}

	/**
	 * (precondition) warehouse must exist
	 * (postcondition) returns the whole stock, listing the products and their amount
	 */
	public HashMap<Product, Integer> getStock() {
		return stock;
	}

	/**
	 * (precondition) warehouse and product must exist
	 * (postcondition) returns the product, searched by name, otherwise throws exception
	 */
	public Product getProduct(String namep) throws ProductException {
		for (Product p : stock.keySet()) {
			if (p.getName().equals(namep)) {
				return p;
			}
		}
		throw new ProductException("Product " + namep
				+ " does not exist in warehouse " + name + "!");
	}

	
	/**
	 * (precondition) p should be not null.
	 */
	public int getProductInStock(Product p) {
		return getProductInStock(p, new GregorianCalendar());
	}
	
	/**
	 * (precondition) p and d should no be null.
	 * (post-condition) returns 0 if the product is not in stock.
	 */
	public int getProductInStock(Product p, Calendar d) {
		Integer ret = stock.get(p);
		if (ret == null)
			ret = 0;

		for (Order o : orders) {
			ret += o.getQuantatiyForWarehouse(p, this, d);
		}
		return ret.intValue();
	}

	/**
	 *(precondition) warehouse must exist
	 *(postcondition) adds the order to the warehouse orderset
	 * ERROR: there should be exception handling for the case if the order already exists
	 */
	public void addOrder(Order o) {
		orders.add(o);
	}

	/**
	 * (precondition) warehouse and order must exist
	 * (postcondition) removes order from warehouse orderset
	 * ERROR: there should be exception handling for the case if the order doesn't exist
	 */
	public void removeOrder(Order o) {
		orders.remove(o);
	}

	/**
	 * (precondition) warehouse must exist
	 * (postcondition) increments the stock of the mentioned product by the specific amount
	 * if already exists, otherwise adds the product with the amount to the stock
	 */
	public void incrementStock(Product p, int anzahl) {
		if (stock.containsKey(p)) {
			stock.put(p, getProductInStock(p) + anzahl);
		} else {
			aktuell = anzahl;
			stock.put(p, aktuell);
		}
	}

	/**
	 * (precondition) warehouse and product must exist
	 * (postcondition) decrements stock of the mentioned product by the specific amount 
	 * if exists and specific amount <= actual amount, otherwise throws exception
	 */
	public void decrementStock(Product p, int anzahl) throws ProductException {
		if (stock.containsKey(p)) {
			aktuell = getProductInStock(p);

			if (aktuell >= anzahl) {
				aktuell -= anzahl;
				stock.put(p, aktuell);
			} else {
				throw new ProductException("The are only " + aktuell
						+ " items of " + p.getName()
						+ " in stock, cannot take away " + anzahl + "!");
			}
		} else {
			throw new ProductException("Product " + p.getName()
					+ " does not exist in warehouse " + name + "!");
		}
	}

	@Override
	public void deleteLocalReferencesTo(Deletable ref) {
		if (ref instanceof Product) {
			stock.remove((Product) ref);
		} else if (ref instanceof Order) {
			orders.remove((Order) ref);
		}
	}

	@Override
	public void deleteAllReferencesTo(Deletable ref) {
		if ((ref instanceof Product) || (ref instanceof Order)) {
			deleteLocalReferencesTo(ref);
			ConsistencyManager.deleteAllReferencesTo(ref);
		}
	}

	@Override
	public void delete() {
		deleteAllReferencesTo(this);
		stock = null;
		orders = null;
		hasBeenDeleted = true;
	}

	@Override
	public boolean hasBeenDeleted() {
		return hasBeenDeleted;
	}
}
