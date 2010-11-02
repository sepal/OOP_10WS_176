package OOP;

import java.util.Calendar;

/**
 * Klasse f&uuml;r Lieferungen.
 * @author sebastian
 *
 */
public class Shipment extends Order {
	private static final long serialVersionUID = 429221110251696384L;

	/**
	 * (precondition) id should be unique, source, destination, dispatch and delivery should not be null. dispatch date < delivary date.
	 */
	protected Shipment(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
		super(id, source, destination, dispatch, delivery);
		Warehouse wh = (Warehouse)destination;
		wh.addOrder(this);
	}

	/**
	 * (precondition) Deletes orders from the warehouse destination.
	 */
	public void delete() {
		Warehouse wh = (Warehouse)destination;
		wh.removeOrder(this);
		
		deleteAllReferencesTo(this);
		hasBeenDeleted = true;
	}
}