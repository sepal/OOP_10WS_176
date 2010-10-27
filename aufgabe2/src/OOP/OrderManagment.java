package OOP;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Factory Klasse, die alle Bestellungen und  auch die Bestellungsklassen 
 * enthält.
 * 
 * @author sebastian
 *
 */
public class OrderManagment extends StorageManager {
	private static final long serialVersionUID = -8159741958749782538L;
	
	private static final OrderManagment INSTANCE = new OrderManagment();
	// Ids zum identifizieren der Orders beim löschen. Ist vielleicht nicht
	// ideal(da sie nach gewisser zeit ausgehen können, aber alles geht mal
	// irgendwann mal zuende :P
	private static long id = 0;
	private HashMap<Long, Order> orders;

	/**
	 * getInstance() method gives you access to the only existing OrderManagment Object.
	 * 
	 * @return instance of OrderManagment
	 */
	public static OrderManagment getInstance() {
		return INSTANCE;
	}
	
	private OrderManagment() {
		super();
		
		orders = new HashMap<Long, Order>();
	}
	
	/**
	 * Speichert eine neue Bestellung und erhöht den id counter.
	 * 
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
	 * Bestellung mit Lager als Ziel und Quelle für Verschiebungen.
	 * @param source Ort von wo aus die Bestellung kommt
	 * @param destination Ort an dem die Bestellung hingeht.
	 * @param dispatch Zeitpunkt ab wann die Bestellung weggeht.
	 * @param delivery Zeitpunkt an dem die Bestellung ankommt.
	 * @return
	 */
	public Order createOrder(Warehouse source, Warehouse destination, Calendar dispatch, Calendar delivery){
		return saveOrder(new WarehouseTransfer(id,(Location)source, (Location)destination, dispatch, delivery));
	}
	
	/**
	 * Löscht eine bestellung per Id. 
	 * Da aber klassen normalerweise mit der Id nix tun haben, ist diese
	 * funtion private.
	 * 
	 * @param id Id der zu löschenden Bestellung
	 */
	private void removeOrder(long id) {
		orders.remove(id);
	}
	
	/**
	 * Löscht eine Bestellung per Objektreferenz.
	 * @param o Die zu löschende Bestellung.
	 */
	public void removeOrder(Order o) {
		o.delete();
		removeOrder(o.getId());
	}

}
