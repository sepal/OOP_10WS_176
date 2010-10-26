package OOP;

import java.util.Calendar;
import java.util.HashMap;

import OOP.ProductFactory.Product;

/**
 * Factory Klasse, die alle Bestellungen und  auch die Bestellungsklassen 
 * enth&auml;lt.
 * @author sebastian
 *
 */
public class OrderManagment {
	private static final OrderManagment INSTANCE = new OrderManagment();
	// Ids zum identifizieren der Orders beim l&ouml;schen. Ist vielleicht nicht
	// ideal(da sie nach gewisser zeit ausgehen k&ouml;nnen, aber alles geht mal
	// irgendwann mal zuende :P
	private static long id = 0;
	private HashMap<Long, Order> orders;

	/**
	 * getInstance() method gives you access to the only existing ProductVerwaltung Object.
	 * 
	 * @return instance of ProductVerwaltung
	 */
	public static OrderManagment getInstance() {
		return INSTANCE;
	}
	
	private OrderManagment() {
		orders = new HashMap<Long, Order>();
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param dispatch
	 * @param delivery
	 * @return
	 */
	private Order createOrder(Location source, Location destination, Calendar dispatch, Calendar delivery) {
		orders.put(id, new WarehouseShift(id, source, destination, dispatch, delivery));
		id++;
		return orders.get(id-1);
		
	}
	
	public Order createOrder(Location supplier, Warehouse warehouse, Calendar dispatch, Calendar delivery){
		return createOrder((Location)supplier, (Location)warehouse, dispatch, delivery);
	}
	
	public Order createOrder(Warehouse warehouse, Location client, Calendar dispatch, Calendar delivery){
		return createOrder((Location)warehouse, (Location)client, dispatch, delivery);
	}
	
	public Order createOrder(Warehouse source, Warehouse destination, Calendar dispatch, Calendar delivery){
		return createOrder((Location)source, (Location)destination, dispatch, delivery);
	}
	
	private void removeOrder(long id) {
		orders.remove(id);
	}
	
	public void removeOrder(Order o) {
		removeOrder(o.id);
		o.delete();
	}
	
	public abstract class Order {
		private long id;
		protected Location source;
		protected Location destination;
		protected HashMap<Product, Integer> stock;
		protected Calendar dispatchCalendar;
		protected Calendar deliveryCalendar;
		
		
		protected Order(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
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
		
		protected void delete() {}

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
		
		public int getQuantatiyForWarehouse(Product p, Location l, Calendar d) {
			if (l == source && d.after(dispatchCalendar)) {
				return getQuantatiyForWarehouse(p, l);
			} else if (l == destination && d.after(deliveryCalendar)) {
				return getQuantatiyForWarehouse(p, l);
			} else {
				System.out.println(dispatchCalendar.getTime() + " - " + d.getTime());
				return 0;
			}
		}
	}

	public class ClientOrder extends Order {
		protected ClientOrder(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
			super(id, source, destination, dispatch, delivery);
			Warehouse wh = (Warehouse)source;
			wh.addOrder(this);
		}
		
		protected void delete() {
			Warehouse wh = (Warehouse)source;
			wh.removeOrder(this);
		}
	}
	
	public class Shipment extends Order {

		protected Shipment(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
			super(id, source, destination, dispatch, delivery);
			Warehouse wh = (Warehouse)destination;
			wh.addOrder(this);
		}

		protected void delete() {
			Warehouse wh = (Warehouse)destination;
			wh.removeOrder(this);
		}
	}
	
	public class WarehouseShift extends Order {

		protected WarehouseShift(long id, Location source, Location destination, Calendar dispatch, Calendar delivery) {
			super(id, source, destination, dispatch, delivery);
			Warehouse wh = (Warehouse)source;
			wh.addOrder(this);
			wh = (Warehouse)destination;
			wh.addOrder(this);
		}

		protected void delete() {
			Warehouse wh = (Warehouse)source;
			wh.removeOrder(this);
			wh = (Warehouse)destination;
			wh.removeOrder(this);
		}
	}
}
