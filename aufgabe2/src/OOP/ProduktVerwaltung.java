package OOP;

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
public class ProduktVerwaltung {
	private static final ProduktVerwaltung INSTANCE = new ProduktVerwaltung();
	
	private HashMap<String, Produkt> products;

	/**
	 * getInstance() method gives you access to the only existing ProduktVerwaltung Object.
	 * 
	 * @return instance of ProduktVerwaltung
	 */
	public static ProduktVerwaltung getInstance() {
		return INSTANCE;
	}
	
	private ProduktVerwaltung() {
		products = new HashMap<String, Produkt>();
	}
	
	/**
	 * Creates a new Produkt with the name and description given through the parameters.
	 * If a Produkt by the same name already exists, an exception is thrown.
	 * 
	 * @param name The name of the new Produkt. Has to be unique.
	 * @param description Short description of the Produkt.
	 * @return Reference to the new Produkt.
	 */
	public Produkt createProdukt(String name, String description, int baseprice, int marketprice, double volume, int storagecosts) throws ProduktException {
		Produkt re = null;
		
		if (!products.containsKey(name)) {
			re = new Produkt(name, description, baseprice, marketprice, volume, storagecosts);
			products.put(name, re);
		} else {
			throw new ProduktException("Product "+name+" already exists!");
		}
		
		return re;
	}
	
	/**
	 *  Returns a reference to the Produkt identified by name, or throws exception if no such Produkt exists.
	*/
	public Produkt getProduktByName(String name) {
		if (! products.containsKey(name)) {
			throw new ProduktException("Product " + name + " ist not available.");
		}
		return products.get(name);
	}
	
	/**
	 * This class represents a Produkt
	 */
	public class Produkt implements ProduktGruppenMitglied {
		private String name, description;
		private int baseprice = 0; //price buy-in
		private int marketprice = 0; //price for sale
		private int storagecosts = 0; //lagerhaltungskosten
		private double volume = 0; //size of product
		
		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}
		
		public int getBaseprice() {
			return baseprice;
		}
		
		public int getMarketprice() {
			return marketprice;
		}
		
		public double getVolume() {
			return volume;
		}
		
		@Override
		public int getStoragecosts(Lager l) {
			//Formel: ((Preis/m^3)*Volumen d produkts)*zeit*bestand
			int storageprice = 2; //2Ä/m^3
			int time = 10; //product stays exactly 10 days in our warehouse
			
			storagecosts = (int) ((storageprice * getVolume()) * time * l.getProduktInStock(this));
			
			return storagecosts;
		}
		
		private Produkt(String name, String description, int baseprice, int marketprice, double volume, int storagecosts) {
			this.name = name;
			this.description = description;
			this.baseprice = baseprice;
			this.marketprice = marketprice;
			this.volume = volume;
			this.storagecosts = storagecosts;
			
		}

		/** 
		 * Implementation of the listStock method. The Produkt checks the given storage/Lager
		 * for how much of this Produkt is in stock and returns a String array containing a
		 * single string with its name and number of items in stock.
		 * 
		 * @return String array containing one string listing the name and number of items in stock. 
		 * This method returns an array, so it compatible to the listStock method in ProduktGruppe, which returns a list of results gathered by this very method.
		 */
		@Override
		public String[] listStock(Lager lager) {
			Integer cnt = lager.getProduktInStock(this);
			int erg=0;
			
			// If getProduktInStock returned null, meaning this Produkt is not in stock at all
			// use default value zero
			if (cnt != null) {
				erg = cnt.intValue();
			}
			
			String[] retval = new String[1];
			retval[0] = this.name + " - " + erg + " St√ºck";
			
			return retval;
		}

		@Override
		public Produkt getCheapest(Lager l) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
