package OOP;

import java.util.HashMap;

public class ProduktVerwaltung {
	private static final ProduktVerwaltung INSTANCE = new ProduktVerwaltung();
	
	private HashMap<String, Produkt> products;

	public static ProduktVerwaltung getInstance() {
		return INSTANCE;
	}
	
	private ProduktVerwaltung() {
		products = new HashMap<String, Produkt>();
	}
	
	public class Produkt implements ProduktGruppenMitglied {
		private Produkt() {
			
		}

		@Override
		public String[] listStock() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
