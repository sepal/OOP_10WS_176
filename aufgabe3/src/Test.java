import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import OOP.Configuration;
import OOP.CustomerInterface;
import OOP.Location;
import OOP.Order;
import OOP.OrderManagment;
import OOP.PersistenceManager;
import OOP.ProductException;
import OOP.ProductFactory;
import OOP.ProductFactory.Product;
import OOP.ProductGroup;
import OOP.Shipment;
import OOP.StorageManager;
import OOP.Warehouse;

/**
 * This App tests the functionality of exercise 1
 * 
 */
public class Test {

	public static void main(String args[]) {
		System.out
				.println("*** Starting test of exercise 2 app - Lagerverwaltung 2 ***");

		Test t = new Test();
		t.testPhase1();

		System.gc();

		t.testPhase2();

		System.out.println("\n*** All tests done ***");
	}

	public Test() {
		// do nothing
	}

	/**
	 * Putting the test in a method, so we can easily and quickly drop all the
	 * object references and non-static data
	 */
	public void testPhase1() {

		ProductFactory pf = ProductFactory.getInstance();
		Warehouse vitech = new Warehouse("Vitech");
		System.out.println("*** Creating new Lager \"Vitech\" ***");
		Warehouse mediamarkt = new Warehouse("Mediamarkt");
		System.out.println("*** Creating new Lager \"Mediamarkt\" ***");
		Warehouse saturn = new Warehouse("Saturn");
		System.out.println("*** Creating new Lager \"Saturn\" ***");

		/******************** ProduktGruppen start ********************/
		System.out.println("\n*** Creating new ProductGruppen... ***");

		ProductGroup comp = new ProductGroup("Components");

		ProductGroup cpus = new ProductGroup("CPUs");
		ProductGroup mainboards = new ProductGroup("Mainboards");
		ProductGroup gracas = new ProductGroup("Video cards");
		ProductGroup displays = new ProductGroup("Displays");

		ProductGroup nsidia = new ProductGroup("Nsidia");
		ProductGroup amt = new ProductGroup("Amt");

		System.out
				.println("Created ProduktGruppen Components, CPUs, Mainboards, Video Cards, Displays, Nsidia and Amt.");

		System.out.println("\n*** Adding groups to groups ***");

		// Test group in group.
		comp.addMember(cpus);
		comp.addMember(mainboards);
		comp.addMember(gracas);
		comp.addMember(displays);

		System.out
				.println("CPUs, Mainboards, Video cards, Displays added to group Components");

		// Test group in group in group.
		gracas.addMember(nsidia);
		gracas.addMember(amt);

		System.out.println("nsidia and amt added to group Video Cards");
		/******************** ProduktGruppen end ********************/

		/******************** Products @vitech start ********************/
		System.out
				.println("\n*** Creating new Products and adding them to warehouse vitech***");

		// General product test.
		for (int i = 0; i <= 5; i++) {
			Product cpu = pf.createProduct("CPU " + i, "CPU desc " + i, 100,
					120, 0, 002);
			cpus.addMember(cpu);
			vitech.incrementStock(cpu, 10);
		}

		System.out
				.println("Created \"CPU i\" with i from 0 to 5, added 10 pieces to warehouse each.");

		for (int i = 0; i <= 5; i++) {
			Product monitor = pf.createProduct("Monitor " + (19 + i) + " Zoll",
					"LCD Monitor, groesse " + (19 + i) + " Zoll", 200, 240, 0,
					005);
			displays.addMember(monitor);
			vitech.incrementStock(monitor, 10);
		}

		System.out
				.println("Created \"Monitor i\" with i from 19 to 24, added 10 pieces to warehouse each.");

		// Adding more products for configurations.
		for (int i = 0; i <= 3; i++) {
			Product p = pf.createProduct("Mainboard " + i, "Mainboard desc "
					+ i, 300, 360, 0, 002);
			mainboards.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out
				.println("Created \"Mainboard i\" with i from 0 to 3, added 10 pieces to warehouse each.");

		// Adding more products for configurations.
		for (int i = 0; i <= 5; i++) {
			Product p = pf.createProduct("GraCa nsidia " + i,
					"Grafic card desc " + i, 200, 240, 0, 001);
			nsidia.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out
				.println("Created \"GraCa nsidia i\" with i from 0 to 5, added 10 pieces to warehouse each.");

		// Test product exists in configuration and lager but stock is zero.
		Product amtGraca = pf.createProduct("GraCa amt", "Grafic card desc",
				200, 240, 0, 001);
		amt.addMember(amtGraca);
		vitech.incrementStock(amtGraca, 0);

		System.out
				.println("Created \"GraCa amt\", added 0 pieces to warehouse.");

		// Test product exists in configuration but not in Lager.
		Product monitor2 = pf.createProduct("Monitor 2", "Monitor desc 2", 300,
				360, 0, 005);
		displays.addMember(monitor2);

		System.out
				.println("Created \"Monitor 2\", but did NOT add it to warehouse!");
		/******************** Products @vtech end ********************/

		/******************* Products @mediamarkt start ********************/
		System.out
				.println("\n*** Creating new Products and adding them to warehouse mediamarkt ***");

		// General product test.
		for (int i = 0; i <= 6; i++) {
			Product cpu = pf.createProduct("CPU2 " + i, "CPU2 desc " + i, 300,
					360, 0, 002);
			cpus.addMember(cpu);
			vitech.incrementStock(cpu, 10);
		}

		System.out
				.println("Created \"CPU i\" with i from 0 to 6, added 10 pieces to warehouse mediamarkt each.");

		for (int i = 0; i <= 5; i++) {
			Product monitor1 = pf.createProduct(
					"Monitor2" + (19 + i) + " Zoll", "LCD Monitor, groesse "
							+ (19 + i) + " Zoll", 400, 480, 0, 005);
			displays.addMember(monitor1);
			vitech.incrementStock(monitor1, 10);
		}

		System.out
				.println("Created \"Monitor2 i\" with i from 19 to 24, added 10 pieces to warehouse mediamarkt each.");

		// Adding more products for configurations.
		for (int i = 0; i <= 3; i++) {
			Product p = pf.createProduct("Mainboard2 " + i, "Mainboard desc "
					+ i, 600, 820, 0, 002);
			mainboards.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out
				.println("Created \"Mainboard2 i\" with i from 0 to 3, added 10 pieces to warehouse mediamarkt each.");

		// Adding more products for configurations.
		for (int i = 0; i <= 5; i++) {
			Product p = pf.createProduct("GraCa2 nsidia " + i,
					"Grafic card desc " + i, 400, 480, 0, 001);
			nsidia.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out
				.println("Created \"GraCa2 nsidia i\" with i from 0 to 5, added 10 pieces to warehouse mediamarkt each.");

		// Test product exists in configuration and lager but stock is zero.
		Product amtGraca2 = pf.createProduct("GraCa2 amt", "Grafic card desc",
				400, 480, 0, 001);
		amt.addMember(amtGraca2);
		vitech.incrementStock(amtGraca2, 0);

		System.out
				.println("Created \"GraCa2 amt\", added 0 pieces to warehouse mediamarkt.");

		// Test product exists in configuration but not in Lager.
		Product monitor3 = pf.createProduct("Monitor 3", "Monitor desc 3", 300,
				360, 0, 005);
		displays.addMember(monitor3);

		System.out
				.println("Created \"Monitor 3\", but did NOT add it to warehouse mediamarkt!");
		/******************* Products @mediamarkt end ********************/

		/******************* Products @saturn start ********************/
		System.out
				.println("\n*** Creating new Products and adding them to warehouse saturn ***");

		// General product test.
		for (int i = 0; i <= 5; i++) {
			Product cpu3 = pf.createProduct("CPU3 " + i, "CPU desc " + i, 90,
					109, 0, 002);
			cpus.addMember(cpu3);
			vitech.incrementStock(cpu3, 10);
		}

		System.out
				.println("Created \"CPU3 i\" with i from 0 to 5, added 10 pieces to warehouse saturn each.");

		for (int i = 0; i <= 5; i++) {
			Product monitor4 = pf.createProduct("Monitor3 " + (19 + i)
					+ " Zoll", "LCD Monitor, groesse " + (19 + i) + " Zoll",
					230, 340, 0, 005);
			displays.addMember(monitor4);
			vitech.incrementStock(monitor4, 10);
		}

		System.out
				.println("Created \"Monitor4 i\" with i from 19 to 24, added 10 pieces to warehouse saturn each.");

		// Adding more products for configurations.
		for (int i = 0; i <= 3; i++) {
			Product p = pf.createProduct("Mainboard3 " + i, "Mainboard desc "
					+ i, 100, 150, 0, 002);
			mainboards.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out
				.println("Created \"Mainboard3 i\" with i from 0 to 3, added 10 pieces to warehouse saturn each.");

		// Adding more products for configurations.
		for (int i = 0; i <= 5; i++) {
			Product p = pf.createProduct("GraCa3 nsidia " + i,
					"Grafic card desc " + i, 240, 360, 0, 001);
			nsidia.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out
				.println("Created \"GraCa3 nsidia i\" with i from 0 to 5, added 10 pieces to warehouse saturn each.");

		// Test product exists in configuration and lager but stock is zero.
		Product amtGraca3 = pf.createProduct("GraCa3 amt", "Grafic card desc",
				120, 150, 0, 001);
		amt.addMember(amtGraca3);
		vitech.incrementStock(amtGraca3, 0);

		System.out
				.println("Created \"GraCa3 amt\", added 0 pieces to warehouse saturn.");

		// Test product exists in configuration but not in Lager.
		Product monitor5 = pf.createProduct("Monitor 5", "Monitor desc 2", 250,
				340, 0, 005);
		displays.addMember(monitor5);

		System.out
				.println("Created \"Monitor 5\", but did NOT add it to warehouse saturn!");
		/******************* Products @saturn end ********************/

		/******************** Group listing Test start ********************/
		String[] list = comp.listStock(vitech);
		System.out
				.println("\n*** Testing 'Auflisten des Gruppeninhaltes mit Lagerbestand' Vitech ***\n");
		for (String line : list) {
			System.out.println(line);
		}

		String[] list2 = comp.listStock(mediamarkt);
		System.out
				.println("\n*** Testing 'Auflisten des Gruppeninhaltes mit Lagerbestand' Mediamarkt ***\n");
		for (String line : list2) {
			System.out.println(line);
		}

		String[] list3 = comp.listStock(saturn);
		System.out
				.println("\n*** Testing 'Auflisten des Gruppeninhaltes mit Lagerbestand' Saturn ***\n");
		for (String line : list3) {
			System.out.println(line);
		}
		/******************** Group listing Test end ********************/

		/******************** Warehouse Test start ********************/
		System.out.println("\n*** Testing warehouse get ammount in stock ***");
		Product p = pf.getProductByName("Monitor 19 Zoll");
		int stock = vitech.getProductInStock(p);
		System.out.print(p.getName() + " - ");
		System.out.println(p.getDescription());
		System.out.println(stock + " items in stock");

		System.out.println("*** Testing warehouse decrement stock ***");
		vitech.decrementStock(p, 5);
		System.out.print(p.getName() + " - ");
		System.out.println(p.getDescription());
		System.out.println(vitech.getProductInStock(p) + " items in stock");

		System.out.println("*** Testing warehouse increment stock ***");
		vitech.incrementStock(p, 5);
		System.out.print(p.getName() + " - ");
		System.out.println(p.getDescription());
		System.out.println(vitech.getProductInStock(p) + " items in stock");
		/******************** Warehouse Test end ********************/

		/******************** Configuration Test start ********************/
		System.out.println("\n*** Testing normal configuration ***");
		Configuration k1 = new Configuration();
		k1.addProduct(pf.getProductByName("CPU 1"), 1);
		k1.addProduct(pf.getProductByName("Mainboard 3"), 1);
		k1.addProduct(pf.getProductByName("GraCa nsidia 5"), 1);
		k1.addProduct(pf.getProductByName("Monitor 20 Zoll"), 1);
		System.out
				.println("Created Configuration1 and added 1 \"CPU 1\", 1 \"Mainboard 3\", 1 \"GraCa nsidia 5\","
						+ " 1 \"Monitor 20 Zoll\"");
		System.out
				.println("*** Testing Configuration1 - getConfigurationStock ***");
		System.out.println("Number of Configuration1s that can be built: "
				+ k1.getConfigurationStock(vitech));
		System.out
				.println("*** Testing configuration 1 - removeFromStock  ***");
		k1.removeFromStock(vitech);
		System.out.println("Removed one from stock, can still build: "
				+ k1.getConfigurationStock(vitech));

		System.out
				.println("\n*** Testing configuration with zero items in stock ***");
		Configuration k2 = new Configuration();
		k2.addProduct(pf.getProductByName("CPU 1"), 1);
		k2.addProduct(pf.getProductByName("Mainboard 2"), 1);
		k2.addProduct(pf.getProductByName("GraCa amt"), 1);
		k2.addProduct(pf.getProductByName("Monitor 19 Zoll"), 1);
		System.out
				.println("Created Configuration2 with 1 \"CPU 1\", 1 \"Mainboard 2\", 1 \"GraCa amt\", "
						+ "1 \"Monitor 19 Zoll\"");
		System.out
				.println("*** Testing configuration 2 - removeFromStock  ***");
		try {
			k2.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}

		System.out
				.println("\n*** Testing configuration with product not existing in warehouse ***");
		// Configuration with product with stock 0.
		Configuration k3 = new Configuration();
		k3.addProduct(pf.getProductByName("CPU 5"), 2);
		k3.addProduct(pf.getProductByName("Mainboard 3"), 1);
		k3.addProduct(pf.getProductByName("GraCa nsidia 1"), 2);
		k3.addProduct(pf.getProductByName("Monitor 2"), 1);
		System.out
				.println("Created Configuration3 with 2 \"CPU 5\", 1 \"Mainboard 3\", 2 \"GraCa nsidia 1\""
						+ ", 1 \"Monitor 2\"");
		System.out
				.println("*** Testing configuration 3 - removeFromStock (should fail) ***");
		try {
			k3.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}

		System.out.println("\n*** Testing remove from stock working ***");
		System.out.println("Could build Configuration1 "
				+ k1.getConfigurationStock(vitech) + " times.");
		// GraCa nsidia 5 should still be available.
		k1.removeFromStock(vitech);
		System.out.println("Built one, can still build: "
				+ k1.getConfigurationStock(vitech));

		System.out.println("\n*** Testing remove from stock failing ***");
		// GraCa nsidia 5 should not be available.
		System.out
				.println("Removing all \"GraCa nsidia 5\" cards, so Configuartion1 cannot be built anymore.");
		Product ns5 = pf.getProductByName("GraCa nsidia 5");
		vitech.decrementStock(ns5, vitech.getProductInStock(ns5));
		System.out.println("Configuration1 can be built "
				+ k1.getConfigurationStock(vitech)
				+ " times. Trying it anyway...");
		try {
			k1.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}
		/******************** Configuration Test end ********************/

		/********** Prices Test start ***************/
		System.out
				.println("\n*** Testing Baseprices of Products and ProductGroups ***");
		System.out.println("Name: " + cpus.getName() + ", Baseprice: "
				+ cpus.getBaseprice());
		System.out.println("Name: " + p.getName() + ", Baseprice: "
				+ p.getBaseprice());

		System.out.println("\n*** Testing Marketprices of ProductGroups ***");
		System.out.println("Name: " + mainboards.getName() + ", Marketprice: "
				+ mainboards.getMarketprice());

		System.out.println("\n*** Testing Cheapest of ProductGroups ***");
		System.out.println("Name: " + mainboards.getName() + ", Cheaptest: "
				+ mainboards.getCheapest());
		/********** Prices Test end ***************/

		/******************** Order Test start ********************/
		OrderManagment om = OrderManagment.getInstance();
		Product cpu1 = pf.getProductByName("CPU 1");

		System.out.println("\n*** Testing Order - Shipment ***");
		Order shipment = om.createOrder(new Location("Intel"), mediamarkt,
				new GregorianCalendar(2011, Calendar.OCTOBER, 31),
				new GregorianCalendar(2011, Calendar.NOVEMBER, 2));
		shipment.incrementQuantity(cpu1, 2);
		System.out.println("Shipping "
				+ shipment.getQuantatiyForWarehouse(cpu1, mediamarkt)
				+ " CPU1 to " + shipment.getDestination().getName());
		System.out
				.println("Stock for CPU1 of mediamarkt before the product is delivered: "
						+ mediamarkt.getProductInStock(cpu1,
								new GregorianCalendar(2011, Calendar.NOVEMBER,
										1)));
		System.out
				.println("Stock for CPU1 of mediamarkt after the product is delivered:  "
						+ mediamarkt.getProductInStock(cpu1,
								new GregorianCalendar(2011, Calendar.NOVEMBER,
										3)));

		System.out.println("\n*** Testing Order - ClientOrder ***");
		Order order = om.createOrder(vitech, new Location("Kunde XY"),
				new GregorianCalendar(2011, Calendar.OCTOBER, 31),
				new GregorianCalendar(2011, Calendar.NOVEMBER, 2));
		order.incrementQuantity(cpu1, 1);
		System.out.println("Delivering "
				+ order.getQuantatiyForWarehouse(cpu1, vitech) + " CPU1 from "
				+ order.getSource().getName());
		System.out
				.println("Stock for CPU1 of vitech before the product is shipped: "
						+ vitech.getProductInStock(cpu1, new GregorianCalendar(
								2011, Calendar.OCTOBER, 30)));
		System.out
				.println("Stock for CPU1 of vitech after the product is shipped:  "
						+ vitech.getProductInStock(cpu1, new GregorianCalendar(
								2011, Calendar.NOVEMBER, 5)));

		System.out
				.println("\n*** Testing Order - Warehouse transfer and deleting order ***");
		System.out.println("Deleting client order");
		om.removeOrder(order);
		Order shift = om.createOrder(vitech, saturn, new GregorianCalendar(
				2011, Calendar.NOVEMBER, 5), new GregorianCalendar(2011,
				Calendar.NOVEMBER, 8));
		shift.incrementQuantity(cpu1, 3);
		System.out.println("Transfering "
				+ shift.getQuantatiyForWarehouse(cpu1, saturn) + " CPU1 from "
				+ shift.getSource().getName() + " to "
				+ shift.getDestination().getName());
		System.out.println("Vitech stock for CPU1 before 5th of november: "
				+ vitech.getProductInStock(cpu1, new GregorianCalendar(2011,
						Calendar.NOVEMBER, 3)));
		System.out.println("Vitech stock for CPU1 after 5th of november: "
				+ vitech.getProductInStock(cpu1, new GregorianCalendar(2011,
						Calendar.NOVEMBER, 6)));
		System.out.println("Saturn stock for CPU1 before 8th of november: "
				+ saturn.getProductInStock(cpu1, new GregorianCalendar(2011,
						Calendar.NOVEMBER, 6)));
		System.out.println("Saturn stock for CPU1 after 8th of november: "
				+ saturn.getProductInStock(cpu1, new GregorianCalendar(2011,
						Calendar.NOVEMBER, 9)));

		System.out
				.println("\n*** Testing Order - Reduce Order if package dispatched ***");

		order = om.createOrder(vitech, new Location("Kunde XY"),
				new GregorianCalendar(2010, Calendar.OCTOBER, 1),
				new GregorianCalendar(2011, Calendar.OCTOBER, 3));
		order.incrementQuantity(cpu1, 1);
		System.out.println("Delivering "
				+ order.getQuantatiyForWarehouse(cpu1, vitech) + " CPU1 from "
				+ order.getSource().getName());
		System.out
				.println("Stock for CPU1 of vitech before the product is shipped: "
						+ vitech.getProductInStock(cpu1, new GregorianCalendar(
								2010, Calendar.SEPTEMBER, 30)));
		System.out
				.println("Current stock for CPU1 of vitech:                       "
						+ vitech.getProductInStock(cpu1));

		/******************** Order Test end ********************/

		/******************** Deletion Test start ********************/
		System.out.println("\n*** Testing delete functions ***");
		System.out.println("\n Listing stock of warehouse Saturn "
				+ saturn.getName());
		saturn.incrementStock(monitor5, 10);
		saturn.incrementStock(cpu1, 10);
		HashMap<Product, Integer> stocksat = saturn.getStock();
		Set<Product> keys = stocksat.keySet();
		for (Product key : keys) {
			System.out.println(key + " " + stocksat.get(key) + " Stueck");
		}
		System.out.println("Deleting cpu1");
		cpu1.delete();
		stocksat = saturn.getStock();
		keys = stocksat.keySet();
		for (Product key : keys) {
			System.out.println(key + " " + stocksat.get(key) + " Stueck");
		}

		System.out.println("\nContent of ProductGroup \"Video cards\":");

		for (String s : gracas.listStock(vitech)) {
			System.out.println(s);
		}
		System.out.println("\nDeleting ProductGroup \"nsidia\":");
		nsidia.delete();
		for (String s : gracas.listStock(vitech)) {
			System.out.println(s);
		}
		/******************** Deletion Test end ********************/

		/******************** Easy Customer Interface Test start ********************/
		System.out.println("\n*** Testing Easy Customer Interface ***");
		System.out.println("Querying available products:");
		for (String s : CustomerInterface.getAvailableProducts()) {
			System.out.println(s);
		}

		System.out.println("\nQuerying available Warehouses:");
		for (Warehouse w : CustomerInterface.getWarehouses()) {
			System.out.println(w.getName());
		}

		System.out.println("\nQuerying price of product CPU2 6:");
		System.out.println("CPU2 price: "
				+ CustomerInterface.getPriceOfProduct("CPU2 6"));

		System.out
				.println("\nQuerying stock of product CPU2 6 in warehouse vitech:");
		System.out.println("CPU2 stock: "
				+ CustomerInterface.getProductStock("CPU2 6", vitech));
		/******************** Easy Customer Interface Test end ********************/

		/******************** Persistence test start ********************/
		System.out.println("\n *** Testing data persistence ***");
		if (!PersistenceManager.storeData()) {
			System.out.println("ERROR: Could not save data to disk!");
		} else {
			System.out
					.println("Successfully stored data to \"./storage.data\"");
		}
		System.out.println("Deleting all data in memory");
		/*
		 * Most of the data is actually still there, because objects are only
		 * deleted if no references are pointing to them anymore. Since the
		 * StorageManager stores references to (almost) all objects, thats not
		 * going to happen here. But once the storage manager loads the data
		 * from the disk, he drops all the references and creates new objects
		 * and we get new references to those.
		 */
		/******************** Persistence test end ********************/
	}

	@SuppressWarnings("unchecked")
	public void testPhase2() {
		/******************** Persistence test part 2 start ********************/
		System.out.println("(Re)loading data from filesystem...");
		if (!PersistenceManager.loadDataDiscardCurrent()) {
			System.out.println("ERROR: Could not load data from disk!");
			System.exit(1); // shit
		} else {
			System.out.println("Successfully loaded data.");
		}
		System.out.println("Showing off some data: ");
		ProductFactory pf = StorageManager.getProductFactory();
		Product cpu0 = pf.getProductByName("CPU 0");
		System.out.println("\n Product: " + cpu0);
		Product mb0 = pf.getProductByName("Mainboard2 0");
		System.out.println("\n Product: " + mb0);

		LinkedList<Warehouse> llw = null;
		try {
			llw = (LinkedList<Warehouse>) StorageManager
					.getCreatedObjectsOfType(Warehouse.class);
		} catch (ClassCastException cce) {
			System.err.println("ERROR: ClassCastException " + cce.getMessage()
					+ "\n" + cce.getStackTrace());
			System.exit(1);
		}

		Warehouse w0 = null;
		if (llw != null && !llw.isEmpty()) {
			w0 = llw.get(0);
		}
		System.out.println("\n Listing stock of Warehouse " + w0.getName());
		HashMap<Product, Integer> stock = w0.getStock();
		Set<Product> keys = stock.keySet();
		for (Product key : keys) {
			System.out.println(key + " " + stock.get(key) + " Stueck");
		}

		LinkedList<Shipment> llo = null;
		try {
			llo = (LinkedList<Shipment>) StorageManager
					.getCreatedObjectsOfType(Shipment.class);
		} catch (ClassCastException cce) {
			System.err.println("ERROR: ClassCastException " + cce.getMessage()
					+ "\n" + cce.getStackTrace());
			System.exit(1);
		}

		Shipment o0 = null;
		if (llo != null && !llo.isEmpty()) {
			o0 = llo.get(0);
			System.out.println("\nShowing order: \nSource: "
					+ o0.getSource().getName() + " Destination: "
					+ o0.getDestination().getName());
		} else {
			System.out.println("Empty");
		}
		/******************** Persistence test part 2 end ********************/
	}
}
