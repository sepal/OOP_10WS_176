package OOP;

/**
 * &uuml;berklasse f&uuml;r Warehouse, damit in Order f&uuml;r from und to auch andere Klassen 
 * wie Kunde oder Lieferant(die zur zeit nicht existieren) benutzt werden
 * können.
 * @author sebastian
 *
 */
public class Location {
	// Damit die Klasse nicht ganz leer ist...
	protected String name;
	protected String address;
	
	public Location(String name, String address) {
		this.setName(name);
		this.setAddress(address);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
}
