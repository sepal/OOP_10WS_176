package OOP;

import java.util.Calendar;

/**
 * Klasse für Kundenbestellungen.
 * @author sebastian
 *
 */
public class ClientOrder extends Order {
	private static final long serialVersionUID = -6799496149001907928L;

	protected ClientOrder(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
		super(id, source, destination, dispatch, delivery);
		Warehouse wh = (Warehouse)source;
		wh.addOrder(this);
	}
	
	public void delete() {
		Warehouse wh = (Warehouse)source;
		wh.removeOrder(this);
		
		deleteAllReferencesTo(this);
		hasBeenDeleted = true;
	}
}