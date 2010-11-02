package OOP;

import java.util.Calendar;

/**
 * Class for customer orders.
 * @author sebastian
 *
 */
public class ClientOrder extends Order {
	private static final long serialVersionUID = -6799496149001907928L;
	
	/**
	 * (precondition) id should be unique, source, destination, dispatch and delivery should not be null. dispatch date < delivary date.
	 */
	protected ClientOrder(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
		super(id, source, destination, dispatch, delivery);
		Warehouse wh = (Warehouse)source;
		wh.addOrder(this);
	}
	
	/**
	 * (precondition) Deletes orders from the warehouse source.
	 */
	public void delete() {
		Warehouse wh = (Warehouse)source;
		wh.removeOrder(this);
		
		deleteAllReferencesTo(this);
		hasBeenDeleted = true;
	}
}