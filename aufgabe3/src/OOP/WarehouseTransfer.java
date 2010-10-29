package OOP;

import java.util.Calendar;

/**
 * Klasse für Lagerverschiebungen.
 * @author sebastian
 *
 */
public class WarehouseTransfer extends Order {
	private static final long serialVersionUID = -7773090783226656010L;

	public boolean hasBeenDeleted = false;
	
	protected WarehouseTransfer(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
		super(id, source, destination, dispatch, delivery);
		Warehouse wh = (Warehouse)source;
		wh.addOrder(this);
		wh = (Warehouse)destination;
		wh.addOrder(this);
	}

	public void delete() {
		Warehouse wh = (Warehouse)source;
		wh.removeOrder(this);
		wh = (Warehouse)destination;
		wh.removeOrder(this);
		
		deleteAllReferencesTo(this);
		hasBeenDeleted = true;
	}
}