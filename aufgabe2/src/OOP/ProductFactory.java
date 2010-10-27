package OOP;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This singleton class manages the product range and makes sure, that you can
 * not create duplicate products etc. It (sort of) works like a factory, including
 * a member class with a private constructor, so the only way to create a product
 * is through the product manager.
 * 
 * @version 19-10-2010
 *
 */
public class ProductFactory extends StorageManager {
	private static final long serialVersionUID = -2130363835418960019L;

	private static final ProductFactory INSTANCE = new ProductFactory();
	
	private HashMap<String, Product> products;

	/**
	 * getInstance() method gives you access to the only existing ProductFactory Object.
	 * 
	 * @return instance of ProductFactory
	 */
	public static ProductFactory getInstance() {
		return INSTANCE;
	}
	
	private ProductFactory() {
		super();
		
		products = new HashMap<String, Product>();
	}
	
	/**
	 * Creates a new Product with the name and description given through the parameters.
	 * If a Product by the same name already exists, an exception is thrown.
	 * 
	 * @param name The name of the new Product. Has to be unique.
	 * @param description Short description of the Product.
	 * @return Reference to the new Product.
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
	 *  Returns a reference to the Product identified by name, or null if no such Product exists.
	*/
	public Product getProductByName(String name) {
		return products.get(name);
	}
	
	/**
	 * This class represents a Product
	 */
	public class Product implements ProductGroupMember, Serializable {
		private static final long serialVersionUID = -6740504451127235776L;
		
		private String name, description;
		private int baseprice = 0; //price buy-in
		private int marketprice = 0; //price for sale
		private double volume =0;
		private int storagecosts = 0; //lagerhaltungskosten
		
		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}
		
		@Override
		public int getBaseprice() {
			return baseprice;
		}

		@Override
		public int getMarketprice() {
			return marketprice;
		}
		
		public double getVolume() {
			return volume;
		}

		@Override
		public int getStoragecosts(Warehouse w) {
			//Formel: ((Preis/m^3)*Volumen d produkts)*zeit*bestand
			int storageprice = 2; //2�/m^3
			int time = 10; //product stays exactly 10 days in our warehouse
			
			storagecosts = (int) ((storageprice * getVolume()) * time * w.getProductInStock(this));
			
			return storagecosts;
		}
		
		private Product(String name, String description, int baseprice, int marketprice, double volume, int storagecosts) {
			this.name = name;
			this.description = description;
			this.baseprice = baseprice;
			this.marketprice = marketprice;
			this.volume = volume;
			this.storagecosts = storagecosts;
		}

		/** 
		 * Implementation of the listStock method. The Product checks the given storage/Lager
		 * for how much of this Product is in stock and returns a String array containing a
		 * single string with its name and number of items in stock.
		 * 
		 * @return String array containing one string listing the name and number of items in stock. 
		 * This method returns an array, so it compatible to the listStock method in ProductGruppe, which returns a list of results gathered by this very method.
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

		@Override
		public Product getCheapest() {
			return null;
		}
		
		public String toString() {
			return name + ": " + description;
		}
	}
}
