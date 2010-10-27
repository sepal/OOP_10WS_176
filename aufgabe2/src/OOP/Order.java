package OOP;

import java.util.Calendar;
import java.util.HashMap;

import OOP.ProductFactory.Product;

/**
 * Basisklasse für Bestellungen.
 * @author sebastian
 *
 */
public abstract class Order extends StorageManager implements Deletable {
	private static final long serialVersionUID = 1886352518316471835L;

	// Subklassen brauchen die Id nicht zu kennen.
	private long id;
	
	protected boolean hasBeenDeleted = false;
	protected Location source;
	protected Location destination;
	protected HashMap<Product, Integer> stock;
	protected Calendar dispatchCalendar;
	protected Calendar deliveryCalendar;
	
	/**
	 * Bestellungen k&ouml;nnen nur über die Bestellverwaltung erstellt werden.
	 */
	protected Order(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
		super();
		
		this.id = id;
		stock = new HashMap<Product, Integer>();
		this.source = source;
		this.destination = destination;
		this.dispatchCalendar = dispatch;
		this.deliveryCalendar = delivery;
	}
	
	public void incrementQuantity(Product p, int quantity) {
		if(stock.containsKey(p)) {
			int q = stock.get(p);
			q += quantity;
			stock.put(p, q);
		} else {
			stock.put(p, quantity);
		}
	}
	
	public void decrementQuantity(Product p, int quantity) {
		if(stock.containsKey(p)) {
			int q = stock.get(p);
			q -= quantity;
			stock.put(p, q);
		}
	}

	public Location getSource() {
		return source;
	}

	public Location getDestination() {
		return destination;
	}
	
	protected long getId() {
		return this.id;
	}
	


	/**
	 * Gibt den anzahl an abzuziehenden oder hinzuzufügenden Produkten 
	 * zurück, je nachdem was für ein Ort übergeben wurde.
	 */
	public int getQuantatiyForWarehouse(Product p, Location l) {
		if (stock.containsKey(p)) {
			if (l == source) {
				return stock.get(p)*-1;
			} else if (l == destination) {
				return stock.get(p);
			}
		}
		return 0;
	}
	
	/**
	 * Gibt die anzahl Produkten zu einem bestimmten Datum zurück.
	 */
	public int getQuantatiyForWarehouse(Product p, Location l, Calendar d) {
		if (l == source && d.after(dispatchCalendar)) {
			return getQuantatiyForWarehouse(p, l);
		} else if (l == destination && d.after(deliveryCalendar)) {
			return getQuantatiyForWarehouse(p, l);
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean hasBeenDeleted() {
		return hasBeenDeleted;
	}

	@Override
	public void deleteLocalReferencesTo(Deletable ref) {
		// Nothing to do
	}

	@Override
	public void deleteAllReferencesTo(Deletable ref) {
		if (ref == this) {
			ConsistencyManager.deleteAllReferencesTo(ref);
		}
	}
}