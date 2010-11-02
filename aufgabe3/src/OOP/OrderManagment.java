package OOP;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Factory class for Orders.
 * 
 * @author sebastian
 *
 */
public class OrderManagment extends StorageManager implements Deletable {
	private static final long serialVersionUID = -8159741958749782538L;
	
	private static final OrderManagment INSTANCE = new OrderManagment();
	private static long id = 0;
	private HashMap<Long, Order> orders;


	public static OrderManagment getInstance() {
		return INSTANCE;
	}
	
	private OrderManagment() {
		super();
		orders = new HashMap<Long, Order>();
	}
	
	/**
	 * (precondition) Order must exist.
	 */
	private Order saveOrder(Order o) {
		orders.put(id, o);
		id++;
		return orders.get(id-1);
	}
	
	/**
	 * (precondition)source, destination, dispatch and delivery should not be null. dispatch date < delivary date.
	 * GOOD:
	 * Used dynamic binding to determine if the order is a Shipment, a Clientorder, or a WarehouseTransfer
	 */
	public Order createOrder(Location supplier, Warehouse warehouse, Calendar dispatch, Calendar delivery){
		return saveOrder(new Shipment(id, (Location)supplier, (Location)warehouse, dispatch, delivery));
	}
	
	/**
	 * (precondition) source, destination, dispatch and delivery should not be null. dispatch date < delivary date.
	 * GOOD:
	 * Used dynamic binding to determine if the order is a Shipment, a Clientorder, or a WarehouseTransfer
	 */
	public Order createOrder(Warehouse warehouse, Location client, Calendar dispatch, Calendar delivery){
		return saveOrder(new ClientOrder(id,(Location)warehouse, (Location)client, dispatch, delivery));
	}
	

	
	/**
	 * (precondition) source, destination, dispatch and delivery should not be null. dispatch date < delivary date.
	 * GOOD:
	 * Used dynamic binding to determine if the order is a Shipment, a Clientorder, or a WarehouseTransfer
	 */
	public Order createOrder(Warehouse source, Warehouse destination, Calendar dispatch, Calendar delivery){
		return saveOrder(new WarehouseTransfer(id,(Location)source, (Location)destination, dispatch, delivery));
	}

	
	/**
	 * (precondition) id must belong to an existing order.
	 */
	private void removeOrder(long id) {
		orders.remove(id);
	}
	
	/**
	 * (precondition) Order has to have an id.
	 */
	public void removeOrder(Order o) {
		o.delete();
		removeOrder(o.getId());
	}

	@Override
	public boolean hasBeenDeleted() {
		return false;
	}

	@Override
	public void delete() {
		// Nothing to do
	}

	@Override
	public void deleteLocalReferencesTo(Deletable ref) {
		if (ref instanceof Order) {
			orders.remove(((Order) ref).getId());
		}
	}

	@Override
	public void deleteAllReferencesTo(Deletable ref) {
		if (ref instanceof Order) {
			deleteLocalReferencesTo(ref);
			ConsistencyManager.deleteAllReferencesTo(ref);
		}
	}

}
