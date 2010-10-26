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
	 * Speichert eine neue Bestellung und erhöt den id counter.
	 * @param o Die zu speichernde Bestellung.
	 * @return Die gespeicherte Bestellung.
	 */
	private Order saveOrder(Order o) {
		orders.put(id, o);
		id++;
		return orders.get(id-1);
		
	}
	
	/**
	 * Bestellung mit Lager als Ziel erstellen.
	 * @param supplier Ort von wo aus die Bestellung kommt
	 * @param warehouse Ort an dem die Bestellung hingeht.
	 * @param dispatch Zeitpunkt ab wann die Bestellung weggeht.
	 * @param delivery Zeitpunkt an dem die Bestellung ankommt.
	 * @return
	 */
	public Order createOrder(Location supplier, Warehouse warehouse, Calendar dispatch, Calendar delivery){
		return saveOrder(new Shipment(id, (Location)supplier, (Location)warehouse, dispatch, delivery));
	}
	
	/**
	 * Bestellung mit Lager als Quelle.
	 * @param warehouse Ort von wo aus die Bestellung kommt
	 * @param client Ort an dem die Bestellung hingeht.
	 * @param dispatch Zeitpunkt ab wann die Bestellung weggeht.
	 * @param delivery Zeitpunkt an dem die Bestellung ankommt.
	 */
	public Order createOrder(Warehouse warehouse, Location client, Calendar dispatch, Calendar delivery){
		return saveOrder(new ClientOrder(id,(Location)warehouse, (Location)client, dispatch, delivery));
	}
	
	/**
	 * Bestellung mit Lager als Ziel und Quelle f&uuml;r Verschiebungen.
	 * @param source Ort von wo aus die Bestellung kommt
	 * @param destination Ort an dem die Bestellung hingeht.
	 * @param dispatch Zeitpunkt ab wann die Bestellung weggeht.
	 * @param delivery Zeitpunkt an dem die Bestellung ankommt.
	 * @return
	 */
	public Order createOrder(Warehouse source, Warehouse destination, Calendar dispatch, Calendar delivery){
		return saveOrder(new WarehouseShift(id,(Location)source, (Location)destination, dispatch, delivery));
	}
	
	/**
	 * L&ouml;scht eine bestellung per Id. 
	 * Da aber klassen normalerweise mit der Id nix tun haben, ist diese
	 * funtion private.
	 * 
	 * @param id Id der zu l&ouml;schenden Bestellung
	 */
	private void removeOrder(long id) {
		orders.remove(id);
	}
	
	/**
	 * L&ouml;scht eine Bestellung per Objektreferenz.
	 * @param o Die zu l&ouml;schende Bestellung.
	 */
	public void removeOrder(Order o) {
		o.delete();
		removeOrder(o.getId());
	}
	
	/**
	 * Basisklasse f&uuml;r Bestellungen.
	 * @author sebastian
	 *
	 */
	public abstract class Order {
		// Subklassen brauchen die Id nicht zu kennen.
		private long id;
		
		protected Location source;
		protected Location destination;
		protected HashMap<Product, Integer> stock;
		protected Calendar dispatchCalendar;
		protected Calendar deliveryCalendar;
		
		/**
		 * Bestellungen k&ouml;nnen nur &uuml;ber die Bestellverwaltung erstellt werden.
		 */
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
		
		private long getId() {
			return this.id;
		}
		
		/**
		 * Stellt sicher, dass das es eine delete funktion in orders gibt.
		 * 
		 * Eigentlich sollte ich ein interface schreiben, aber langsam wird
		 * die Zeit knapp daher spar ich mir das mal.
		 */
		protected void delete() {}

		/**
		 * Gibt den anzahl an abzuziehenden oder hinzuzuf&uuml;genden Produkten 
		 * zur&uuml;ck, je nachdem was f&uuml;r ein Ort &uuml;bergeben wurde.
		 */
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
		
		/**
		 * Gibt die anzahl Produkten zu einem bestimmten Datum zur&uuml;ck.
		 */
		public int getQuantatiyForWarehouse(Product p, Location l, Calendar d) {
			if (l == source && d.after(dispatchCalendar)) {
				return getQuantatiyForWarehouse(p, l);
			} else if (l == destination && d.after(deliveryCalendar)) {
				return getQuantatiyForWarehouse(p, l);
			} else {
				return 0;
			}
		}
	}

	/**
	 * Klasse f&uuml;r Kundenbestellungen.
	 * @author sebastian
	 *
	 */
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
	/**
	 * Klasse f&uuml;r Lieferungen.
	 * @author sebastian
	 *
	 */
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
	
	/**
	 * Klasse f&uuml;r Lagerverschiebungen.
	 * @author sebastian
	 *
	 */
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
