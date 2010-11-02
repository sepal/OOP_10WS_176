package OOP;

import java.util.HashMap;

import OOP.ProductFactory.Product;

/**
 * Represents a Configuration consisting of products (and their quantity).
 *
 */
public class Configuration extends StorageManager implements Deletable {	
	private static final long serialVersionUID = 5362452001942924695L;
	
	private boolean hasBeenDeleted = false;
	private HashMap<Product, Integer> products;
	
	public Configuration() {
		super();
		
		products = new HashMap<Product, Integer>();
	}
	
	/**
	 *(precondition) product must exist
	 *(postcondition) amount of product increases by mentioned quantity
	 */
	public void addProduct(Product p, int quantity) {
		if (products.containsKey(p)) {
			products.put(p, products.get(p) + quantity);
		}
		else {
			products.put(p, quantity);
		}
	}
	
	/**
	 * (precondition) warehouse and product must exist
	 * (postcondition) decreases the stock of the products in the configuration in the
	 * selected warehouse
	 */
	public void removeFromStock(Warehouse w) {
		for (Product p : products.keySet()) {
			w.decrementStock(p, products.get(p));
		}
	}
	
	/**
	 * (precondition) warehouse must exist
	 * (postcondition) returns the number of times this configuration could be assembled
	 * considering the amount of available products in the warehouse
	 */
	public int getConfigurationStock(Warehouse w) {
		int ret = Integer.MAX_VALUE, warecnt=0, pneed=0;
		
		for (Product p : products.keySet()) {
			warecnt = w.getProductInStock(p);
			pneed = products.get(p);
			if (warecnt < pneed) {
				return 0; // NOTE: If one item is not in stock, we can build 0 configurations

				
			// NOTE: The item (times amount needed) that is in stock the least,
			// determines how often you can build the configuration
			} else if ((warecnt / pneed) < ret) {
				ret = warecnt / pneed;
			}
		}
		
		return ret;
	}

	@Override
	public boolean hasBeenDeleted() {
		return hasBeenDeleted;
	}

	@Override
	public void delete() {
		deleteAllReferencesTo(this);
		products = null;
		hasBeenDeleted = true;
	}

	@Override
	public void deleteLocalReferencesTo(Deletable ref) {
		if (ref instanceof Product) {
			products.remove(ref);
		}
	}

	@Override
	public void deleteAllReferencesTo(Deletable ref) {
		if (ref instanceof Product || ref instanceof Configuration) {
			deleteLocalReferencesTo(ref);
			ConsistencyManager.deleteAllReferencesTo(ref);
		}
	}
}
