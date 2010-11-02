package OOP;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import OOP.ProductFactory.Product;

/**
 * Base class for Orders.
 * 
 * @author sebastian
 * 
 */
public abstract class Order extends StorageManager implements Deletable {
	private static final long serialVersionUID = 1886352518316471835L;

	private long id;

	protected boolean hasBeenDeleted = false;
	protected Location source;
	protected Location destination;
	protected HashMap<Product, Integer> stock;
	protected Calendar dispatchCalendar;
	protected Calendar deliveryCalendar;
	protected boolean dispatchDone;
	protected boolean delivaryhDone;

	/**
	 * (precondition) id should be unique, source, destination, dispatch and delivery should not be null. dispatch date < delivary date.
	 */
	protected Order(long id, Location source, Location destination,
			Calendar dispatch, Calendar delivery) {
		super();

		this.id = id;
		stock = new HashMap<Product, Integer>();
		this.source = source;
		this.destination = destination;
		this.dispatchCalendar = dispatch;
		this.deliveryCalendar = delivery;
	}

	/**
	 * (precondition) p must exist.
	 * (invariant) stock of p => 0
	 */
	public void incrementQuantity(Product p, int quantity) {
		if (stock.containsKey(p)) {
			int q = stock.get(p);
			q += quantity;
			stock.put(p, q);
		} else {
			stock.put(p, quantity);
		}
	}
	
	/**
	 * (precondition) p must exist.
	 * (invariant) stock of p => 0
	 */
	public void decrementQuantity(Product p, int quantity) {
		if (stock.containsKey(p)) {
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
	
	/**
	 * (precondition) Should only be called Ordermanagment and subclasses.
	 */
	protected long getId() {
		return this.id;
	}
	
	/**
	 * (precondition) P and l must not be null.
	 * (post-condition) returns 0 if product is not in order.
	 */
	public int getQuantatiyForWarehouse(Product p, Location l) {
		if (stock.containsKey(p)) {
			if (l == source) {
				return stock.get(p) * -1;
			} else if (l == destination) {
				return stock.get(p);
			}
		}
		return 0;
	}

	
	/**
	 * (precondition) P and l must not be null.
	 * (post-condition) returns 0 if product is not in order.
	 */
	protected boolean isTimeToReduceSource(Product p, Warehouse w, Calendar d) {
		if (d.after(new GregorianCalendar()) && !dispatchDone) {
			w.decrementStock(p, stock.get(p));
			return this.dispatchDone = true;
		}
		return false;
	}
	
	/**
	 * (precondition) p, w and d must not be null and Warehouse must have the p.
	 * (post-condition) return true if the the stock of the warehouse has to be increased.
	 */
	protected boolean isTimeToReduceDestination(Product p, Warehouse w,
			Calendar d) {
		if (d.after(new GregorianCalendar()) && !delivaryhDone) {
			w.incrementStock(p, stock.get(p));
			return this.delivaryhDone = true;
		}
		return false;
	}
	
	/**
	 * (precondition) p, l and l should not be null..
	 * (post-condition) automatically returns 0 if the Warehouse stock has to be increased.
	 *  BAD:
	 *  Location has to be proved for the type.
	 *  A solution would be to break the function down into several functions and override them in the subclasses.
	 */
	public int getQuantatiyForWarehouse(Product p, Location l, Calendar d) {
		if (l == source && d.after(dispatchCalendar)) {
			if (l.getClass().toString() == "Warehouse") {
				if (!isTimeToReduceSource(p, (Warehouse) l, d))
					return getQuantatiyForWarehouse(p, l);
				else
					return 0;
			} else {
				return getQuantatiyForWarehouse(p, l);
			}
		} else if (l == destination && d.after(deliveryCalendar)) {
			if (l.getClass().toString() == "Warehouse") {
				if (!isTimeToReduceDestination(p, (Warehouse) l, d))
					return getQuantatiyForWarehouse(p, l);
				else
					return 0;
			} else {
				return getQuantatiyForWarehouse(p, l);
			}
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