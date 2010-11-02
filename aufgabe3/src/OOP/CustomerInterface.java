package OOP;

import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedList;

import OOP.ProductFactory.Product;

/**
 * Easy to use interface for customers.
 * 
 * @author ben
 *
 */
public class CustomerInterface {
	/**
	 * (precondition) At least one Warehouse has been created (and thus added to StorageManager).
	 * 
	 * BAD:
	 * Actually "BUG". There needs to be a check for null pointer. getCreatedObjectsOfType can return null if
	 * no Warehouse exists.
	 */
	static public Warehouse[] getWarehouses() {
		@SuppressWarnings("unchecked")
		LinkedList<Warehouse> ll = (LinkedList<Warehouse>) StorageManager.getCreatedObjectsOfType(Warehouse.class);
		Warehouse[] whs = new Warehouse[ll.size()];
		return ll.toArray(whs);
	}
	
	/**
	 * (precondition) Warehouses and Products have to exist, or an empty list will
	 * be returned. (and getWarehouses should not choke on a null-PointerException)
	 */
	static public String[] getAvailableProducts() {
		HashSet<Product> products = new HashSet<Product>();
		
		Warehouse[] whs = CustomerInterface.getWarehouses();
		for (Warehouse wh : whs) {
			for (Product p : wh.getStock().keySet()) {
				products.add(p);
			}
		}
		
		String[] res = new String[products.size()];
		int i = 0;
		for (Iterator<Product> it = products.iterator(); it.hasNext(); i++) {
			res[i] = it.next().getName();
		}
		
		return res;
	}
	
	/**
	 * (precondition) Product with name pname has to exist, or a ProductException will be thrown.
	 */
	static public int getPriceOfProduct(String pname) throws ProductException {
		Product p = ProductFactory.getInstance().getProductByName(pname);
		if (p == null) {
			throw new ProductException("Product "+pname+" does not exist!");
		}
		
		return p.getMarketprice();
	}
	
	/**
	 * (precondition) Product with name pname has to exist, or a ProductException will be thrown.
	 * (invariant) A valid int value will be returned, if the product exists at all. If it is not
	 * in the warehouse w, 0 will be returned.
	 */
	static public int getProductStock(String pname, Warehouse w) throws ProductException {
		Product p = ProductFactory.getInstance().getProductByName(pname);
		if (p == null) {
			throw new ProductException("Product "+pname+" does not exist!");
		}
		
		return w.getProductInStock(p);
	}
}
