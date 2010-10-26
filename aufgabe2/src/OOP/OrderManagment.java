package OOP;

import java.util.HashMap;
import java.util.HashSet;

import OOP.ProductFactory.Product;

public class OrderManagment {
	private static final OrderManagment INSTANCE = new OrderManagment();
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
	
	private Order createOrder(Location source, Location destination) {
		orders.put(id, new WarehouseShift(id, source, destination));
		id++;
		return orders.get(id-1);
		
	}
	
	public Order createOrder(Location supplier, Warehouse warehouse){
		return createOrder((Location)supplier, (Location)warehouse);
	}
	
	public Order createOrder(Warehouse warehouse, Location client){
		return createOrder((Location)warehouse, (Location)client);
	}
	
	public Order createOrder(Warehouse source, Warehouse destination){
		return createOrder((Location)source, (Location)destination);
	}
	
	private void removeOrder(long id) {
		orders.remove(id);
	}
	
	public void removeOrder(Order o) {
		removeOrder(o.id);
	}
	
	public abstract class Order {
		private long id;
		protected Location source;
		protected Location destination;
		protected HashMap<Product, Integer> stock;
			
		protected Order(long id, Location source, Location destination) {
			this.id = id;
			stock = new HashMap<Product, Integer>();
			this.source = source;
			this.destination = destination;
			
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
				q += quantity;
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


		public int getQuantatiyForWarehouse(Product p, Location w) {
			if (stock.containsKey(p)) {
				if (w.name == this.source.name) {
					return -stock.get(p);
				} else if (w.name == this.destination.name) {
					return stock.get(p);
				}
			}
			return 0;
		}
	}

	public class ClientOrder extends Order {
		protected ClientOrder(long id, Location source, Location destination) {
			super(id, source, destination);
			Warehouse wh = (Warehouse)source;
			wh.addOrder(this);
		}
		
		protected void delete() {
			Warehouse wh = (Warehouse)source;
			wh.removeOrder(this);
		}
	}
	
	public class Shipment extends Order {

		protected Shipment(long id, Location source, Location destination) {
			super(id, source, destination);
			Warehouse wh = (Warehouse)destination;
			wh.addOrder(this);
		}

		protected void delete() {
			Warehouse wh = (Warehouse)destination;
			wh.removeOrder(this);
		}
	}
	
	public class WarehouseShift extends Order {

		protected WarehouseShift(long id, Location source, Location destination) {
			super(id, source, destination);
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
