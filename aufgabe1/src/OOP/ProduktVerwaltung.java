package OOP;

import java.util.HashMap;

/**
 * This singleton class is a 
 * 
 * @author ben
 *
 */
public class ProduktVerwaltung {
	private static final ProduktVerwaltung INSTANCE = new ProduktVerwaltung();
	
	private HashMap<String, Produkt> products;

	public static ProduktVerwaltung getInstance() {
		return INSTANCE;
	}
	
	private ProduktVerwaltung() {
		products = new HashMap<String, Produkt>();
	}
	
	public Produkt createProdukt(String name, String description) throws ProductAlreadyExistsException {
		Produkt re = null;
		
		if (!products.containsKey(name)) {
			re = new Produkt(name, description);
			products.put(name, re);
		} else {
			throw new ProductAlreadyExistsException("Product "+name+" already exists!");
		}
		
		return re;
	}
	
	/* Returns a reference to the Produkt identified by name, or NULL if no such Produkt exists */
	public Produkt getProduktByName(String name) {
		return products.get(name);
	}
	
	public class Produkt implements ProduktGruppenMitglied {
		private String name, description;
		
		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}
		
		private Produkt(String name, String description) {
			this.name = name;
			this.description = description;
		}

		@Override
		public String[] listStock() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
