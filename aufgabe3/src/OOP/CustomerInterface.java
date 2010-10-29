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
	static public Warehouse[] getWarehouses() {
		@SuppressWarnings("unchecked")
		LinkedList<Warehouse> ll = (LinkedList<Warehouse>) StorageManager.getCreatedObjectsOfType(Warehouse.class);
		Warehouse[] whs = new Warehouse[ll.size()];
		return ll.toArray(whs);
	}
	
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
	
	static public int getPriceOfProduct(String pname) throws ProductException {
		Product p = ProductFactory.getInstance().getProductByName(pname);
		if (p == null) {
			throw new ProductException("Product "+pname+" does not exist!");
		}
		
		return p.getMarketprice();
	}
	
	static public int getProductStock(String pname, Warehouse w) throws ProductException {
		Product p = ProductFactory.getInstance().getProductByName(pname);
		if (p == null) {
			throw new ProductException("Product "+pname+" does not exist!");
		}
		
		return w.getProductInStock(p);
	}
}
