package OOP;

import java.io.Serializable;
import java.util.HashMap;

public class ProductFactory extends StorageManager {
	private static final long serialVersionUID = -2130363835418960019L;

	private static final ProductFactory INSTANCE = new ProductFactory();
	
	private HashMap<String, Product> products;

	/**
	 * (precondition) There has to be a valid ProductFactory instance in INSTANCE
	 * (since its static, there always should be one, unless its deleted)
	 */
	public static ProductFactory getInstance() {
		return INSTANCE;
	}
	
	private ProductFactory() {
		super();
		
		products = new HashMap<String, Product>();
	}
	
	/**
	 * (post-condition) Caller has to call the Products deleteAllReferences method
	 * to ensure that all references to this product are erased aswell.
	 */
	private void deleteProduct(Product ref) {
		if (products.get(ref.getName()) != null) {
			products.remove(ref.getName());
		}
	}
	
	/**
	 * (precondition) Name of new product has to be unique (not yet used). Int values shouldn't be negative.
	 */
	public Product createProduct(String name, String description, int baseprice, int marketprice, double volume, int storagecosts) throws ProductException {
		Product re = null;
		
		if (!products.containsKey(name)) {
			re = new Product(name, description, baseprice, marketprice, volume, storagecosts);
			products.put(name, re);
		} else {
			throw new ProductException("Product "+name+" already exists!");
		}
		
		return re;
	}
	
	/**
	 *  (precondition) Product with this name has to exist, or null will be returned.
	*/
	public Product getProductByName(String name) {
		return products.get(name);
	}
	
	public class Product implements ProductGroupMember, Serializable, Deletable{
		private static final long serialVersionUID = -6740504451127235776L;
		
		private boolean hasBeenDeleted;
		private String name, description;
		private int baseprice = 0; //price buy-in
		private int marketprice = 0; //price for sale
		private double volume =0;
		private int storagecosts = 0; //lagerhaltungskosten
		
		// (precondition) Name has been set before call.
		public String getName() {
			return name;
		}
		// (precondition) Desc. has been set before call.
		public String getDescription() {
			return description;
		}
		
		/**
		 * (precondition) product and baseprice must exist
		 * (postcondition) returns baseprice
		 */
		public int getBaseprice() {
			return baseprice;
		}

		/**
		 * (precondition) product and marketprice must exist
		 * (postcondition) returns marketprice
		 */
		public int getMarketprice() {
			return marketprice;
		}
		
		/**
		 * (precondition) product and volume must exist
		 * (postcondition) returns volume
		 */
		public double getVolume() {
			return volume;
		}

		/**
		 * (precondition) product (with volume) and warehouse must exist
		 * (postcondition) returns the calculated storagecosts of a product, formula: (price/m^3 * volume of product) * time * inventory
		 */
		public int getStoragecosts(Warehouse w) {
			int storageprice = 2; //NOTE: 2euro/m^3
			int time = 10; //NOTE: product stays exactly 10 days in our warehouse
			
			storagecosts = (int) ((storageprice * getVolume()) * time * w.getProductInStock(this));
			
			return storagecosts;
		}
		
		/**
		 * (precondition) Name has to be unique. Prices, int values should not be negative
		 */
		private Product(String name, String description, int baseprice, int marketprice, double volume, int storagecosts) {
			this.name = name;
			this.description = description;
			this.baseprice = baseprice;
			this.marketprice = marketprice;
			this.volume = volume;
			this.storagecosts = storagecosts;
		}

		/**
		 * (invariant) This method will always return a string array as expected by listStock in ProductGroup.
		 * If this product does not exist in warehouse, it will just return quantity 0.
		 */
		public String[] listStock(Warehouse warehouse) {
			Integer cnt = warehouse.getProductInStock(this);
			int erg=0;
			
			// If getProductInStock returned null, meaning this Product is not in stock at all
			// use default value zero
			if (cnt != null) {
				erg = cnt.intValue();
			}
			
			String[] retval = new String[1];
			retval[0] = this.name + " - " + erg + " Stueck";
			
			return retval;
		}

		/**
		 * (precondition) product exists
		 * (postcondition) returns cheapest product
		 * NOTE: we can't find cheapest product, if there exists only one --> just necessary because of interface
		 */
		public Product getCheapest() {
			return null;
		}
		
		public String toString() {
			return name + ": " + description;
		}

		@Override
		public boolean hasBeenDeleted() {
			return hasBeenDeleted;
		}

		@Override
		public void delete() {
			deleteProduct(this);
			ConsistencyManager.deleteAllReferencesTo(this);
			hasBeenDeleted = true;
		}

		@Override
		public void deleteLocalReferencesTo(Deletable ref) {
			// Nothing to do
		}

		@Override
		public void deleteAllReferencesTo(Deletable ref) {
			if (ref == this) delete();
		}
	}
}
