package OOP;

import java.util.Calendar;

/**
 * Klasse für Lagerverschiebungen.
 * @author sebastian
 *
 */
public class WarehouseTransfer extends Order {
	private static final long serialVersionUID = -7773090783226656010L;

	/**
	 * Shoud not be public.
	 */
	public boolean hasBeenDeleted = false;

	/**
	 * (precondition) id should be unique, source, destination, dispatch and delivery should not be null. dispatch date < delivary date.
	 */
	protected WarehouseTransfer(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
		super(id, source, destination, dispatch, delivery);
		Warehouse wh = (Warehouse)source;
		wh.addOrder(this);
		wh = (Warehouse)destination;
		wh.addOrder(this);
	}

	/**
	 * (precondition) Deletes orders from the warehouses.
	 */
	public void delete() {
		Warehouse wh = (Warehouse)source;
		wh.removeOrder(this);
		wh = (Warehouse)destination;
		wh.removeOrder(this);
		
		deleteAllReferencesTo(this);
		hasBeenDeleted = true;
	}
}