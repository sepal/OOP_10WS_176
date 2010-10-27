package OOP;

import java.util.Calendar;

/**
 * Klasse für Lieferungen.
 * @author sebastian
 *
 */
public class Shipment extends Order {
	private static final long serialVersionUID = 429221110251696384L;

	protected Shipment(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
		super(id, source, destination, dispatch, delivery);
		Warehouse wh = (Warehouse)destination;
		wh.addOrder(this);
	}
	
	public void delete() {
		Warehouse wh = (Warehouse)destination;
		wh.removeOrder(this);
		
		deleteAllReferencesTo(this);
		hasBeenDeleted = true;
	}
}