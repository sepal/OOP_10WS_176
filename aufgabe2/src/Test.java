import OOP.*;
import OOP.ProductFactory.Product;

/**
 * This App tests the functionality of exercise 1
 *
 */
public class Test {
	
	public static void main(String args[]) {
		System.out.println("*** Starting test of exercise 2 app - Lagerverwaltung 2 ***");
		
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
		
		System.out.println("Created ProduktGruppen Components, CPUs, Mainboards, Video Cards, Displays, Nsidia and Amt.");
		
		System.out.println("\n*** Adding groups to groups ***");

		// Test group in group.
		comp.addMember(cpus);
		comp.addMember(mainboards);
		comp.addMember(gracas);
		comp.addMember(displays);
		
		System.out.println("CPUs, Mainboards, Video cards, Displays added to group Components");
		
		// Test group in group in group.
		gracas.addMember(nsidia);
		gracas.addMember(amt);
		
		System.out.println("nsidia and amt added to group Video Cards");
		/******************** ProduktGruppen end ********************/
		
		
		/******************** Products @vitech start ********************/
		System.out.println("\n*** Creating new Products and adding them to warehouse vitech***");
		
		// General product test.
		for (int i=0; i<=5; i++) {
			Product cpu = pf.createProduct("CPU " + i, "CPU desc " + i, 100, 120, 0,002);
			cpus.addMember(cpu);
			vitech.incrementStock(cpu, 10);
		}
		
		System.out.println("Created \"CPU i\" with i from 0 to 5, added 10 pieces to warehouse each.");
		
		for (int i=0; i<=5; i++) {
			Product monitor = pf.createProduct("Monitor " + (19+i) + " Zoll", "LCD Monitor, groesse " + (19+i) + " Zoll", 200, 240, 0,005);
			displays.addMember(monitor);
			vitech.incrementStock(monitor, 10);
		}
		
		System.out.println("Created \"Monitor i\" with i from 19 to 24, added 10 pieces to warehouse each.");
		
		// Adding more products for configurations.
		for (int i=0; i<=3; i++) {
			Product p = pf.createProduct("Mainboard " + i, "Mainboard desc " + i, 300, 360, 0,002);
			mainboards.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out.println("Created \"Mainboard i\" with i from 0 to 3, added 10 pieces to warehouse each.");
		
		// Adding more products for configurations.
		for (int i=0; i<=5; i++) {
			Product p = pf.createProduct("GraCa nsidia " + i, "Grafic card desc " + i, 200, 240, 0,001);
			nsidia.addMember(p);
			vitech.incrementStock(p, 10);
		}
		
		System.out.println("Created \"GraCa nsidia i\" with i from 0 to 5, added 10 pieces to warehouse each.");
		
		// Test product exists in configuration and lager but stock is zero.
		Product amtGraca = pf.createProduct("GraCa amt", "Grafic card desc", 200, 240, 0,001);
		amt.addMember(amtGraca);
		vitech.incrementStock(amtGraca, 0);
		
		System.out.println("Created \"GraCa amt\", added 0 pieces to warehouse.");
		
		// Test product exists in configuration but not in Lager.
		Product monitor2 = pf.createProduct("Monitor 2", "Monitor desc 2", 300, 360, 0,005);
		displays.addMember(monitor2);
		
		System.out.println("Created \"Monitor 2\", but did NOT add it to warehouse!");
		/******************** Products @vtech end ********************/
		
		
		/******************* Products @mediamarkt start ********************/
		System.out.println("\n*** Creating new Products and adding them to warehouse mediamarkt ***");
		
		// General product test.
		for (int i=0; i<=6; i++) {
			Product cpu = pf.createProduct("CPU2 " + i, "CPU2 desc " + i, 300, 360, 0,002);
			cpus.addMember(cpu);
			vitech.incrementStock(cpu, 10);
		}
		
		System.out.println("Created \"CPU i\" with i from 0 to 6, added 10 pieces to warehouse mediamarkt each.");
		
		for (int i=0; i<=5; i++) {
			Product monitor1 = pf.createProduct("Monitor2" + (19+i) + " Zoll", "LCD Monitor, groesse " + (19+i) + " Zoll", 400, 480, 0,005);
			displays.addMember(monitor1);
			vitech.incrementStock(monitor1, 10);
		}
		
		System.out.println("Created \"Monitor2 i\" with i from 19 to 24, added 10 pieces to warehouse mediamarkt each.");
		
		// Adding more products for configurations.
		for (int i=0; i<=3; i++) {
			Product p = pf.createProduct("Mainboard2 " + i, "Mainboard desc " + i, 600, 820, 0,002);
			mainboards.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out.println("Created \"Mainboard2 i\" with i from 0 to 3, added 10 pieces to warehouse mediamarkt each.");
		
		// Adding more products for configurations.
		for (int i=0; i<=5; i++) {
			Product p = pf.createProduct("GraCa2 nsidia " + i, "Grafic card desc " + i, 400, 480, 0,001);
			nsidia.addMember(p);
			vitech.incrementStock(p, 10);
		}
		
		System.out.println("Created \"GraCa2 nsidia i\" with i from 0 to 5, added 10 pieces to warehouse mediamarkt each.");
		
		// Test product exists in configuration and lager but stock is zero.
		Product amtGraca2 = pf.createProduct("GraCa2 amt", "Grafic card desc", 400, 480, 0,001);
		amt.addMember(amtGraca2);
		vitech.incrementStock(amtGraca2, 0);
		
		System.out.println("Created \"GraCa2 amt\", added 0 pieces to warehouse mediamarkt.");
		
		// Test product exists in configuration but not in Lager.
		Product monitor3 = pf.createProduct("Monitor 3", "Monitor desc 3", 300, 360, 0,005);
		displays.addMember(monitor3);
		
		System.out.println("Created \"Monitor 3\", but did NOT add it to warehouse mediamarkt!");
		/******************* Products @mediamarkt end ********************/
		
		/******************* Products @saturn start ********************/
		System.out.println("\n*** Creating new Products and adding them to warehouse saturn ***");
		
		// General product test.
		for (int i=0; i<=5; i++) {
			Product cpu3 = pf.createProduct("CPU3 " + i, "CPU desc " + i, 90, 109, 0,002);
			cpus.addMember(cpu3);
			vitech.incrementStock(cpu3, 10);
		}
		
		System.out.println("Created \"CPU3 i\" with i from 0 to 5, added 10 pieces to warehouse saturn each.");
		
		for (int i=0; i<=5; i++) {
			Product monitor4 = pf.createProduct("Monitor3 " + (19+i) + " Zoll", "LCD Monitor, groesse " + (19+i) + " Zoll", 230, 340, 0,005);
			displays.addMember(monitor4);
			vitech.incrementStock(monitor4, 10);
		}
		
		System.out.println("Created \"Monitor4 i\" with i from 19 to 24, added 10 pieces to warehouse saturn each.");
		
		// Adding more products for configurations.
		for (int i=0; i<=3; i++) {
			Product p = pf.createProduct("Mainboard3 " + i, "Mainboard desc " + i, 100, 150, 0,002);
			mainboards.addMember(p);
			vitech.incrementStock(p, 10);
		}

		System.out.println("Created \"Mainboard3 i\" with i from 0 to 3, added 10 pieces to warehouse saturn each.");
		
		// Adding more products for configurations.
		for (int i=0; i<=5; i++) {
			Product p = pf.createProduct("GraCa3 nsidia " + i, "Grafic card desc " + i, 240, 360, 0,001);
			nsidia.addMember(p);
			vitech.incrementStock(p, 10);
		}
		
		System.out.println("Created \"GraCa3 nsidia i\" with i from 0 to 5, added 10 pieces to warehouse saturn each.");
		
		// Test product exists in configuration and lager but stock is zero.
		Product amtGraca3 = pf.createProduct("GraCa3 amt", "Grafic card desc", 120, 150, 0,001);
		amt.addMember(amtGraca3);
		vitech.incrementStock(amtGraca3, 0);
		
		System.out.println("Created \"GraCa3 amt\", added 0 pieces to warehouse saturn.");
		
		// Test product exists in configuration but not in Lager.
		Product monitor5 = pf.createProduct("Monitor 5", "Monitor desc 2", 250, 340, 0,005);
		displays.addMember(monitor5);
		
		System.out.println("Created \"Monitor 5\", but did NOT add it to warehouse saturn!");
		/******************* Products @saturn end ********************/
		

		
		/******************** Group listing Test start ********************/
		String[] list = comp.listStock(vitech);
		System.out.println("\n*** Testing 'Auflisten des Gruppeninhaltes mit Lagerbestand' Vitech ***\n");
		for (String line : list) {
			System.out.println(line);
		}
		
		String[] list2 = comp.listStock(mediamarkt);
		System.out.println("\n*** Testing 'Auflisten des Gruppeninhaltes mit Lagerbestand' Mediamarkt ***\n");
		for (String line : list2) {
			System.out.println(line);
		}
		
		String[] list3 = comp.listStock(saturn);
		System.out.println("\n*** Testing 'Auflisten des Gruppeninhaltes mit Lagerbestand' Saturn ***\n");
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
		System.out.println("Created Configuration1 and added 1 \"CPU 1\", 1 \"Mainboard 3\", 1 \"GraCa nsidia 5\"," +
				" 1 \"Monitor 20 Zoll\"");
		System.out.println("*** Testing Configuration1 - getConfigurationStock ***");
		System.out.println("Number of Configuration1s that can be built: " + k1.getConfigurationStock(vitech));
		System.out.println("*** Testing configuration 1 - removeFromStock  ***");
		k1.removeFromStock(vitech);
		System.out.println("Removed one from stock, can still build: " +k1.getConfigurationStock(vitech));
		
		System.out.println("\n*** Testing configuration with zero items in stock ***");
		Configuration k2 = new Configuration();
		k2.addProduct(pf.getProductByName("CPU 1"), 1);
		k2.addProduct(pf.getProductByName("Mainboard 2"), 1);
		k2.addProduct(pf.getProductByName("GraCa amt"), 1);
		k2.addProduct(pf.getProductByName("Monitor 19 Zoll"), 1);
		System.out.println("Created Configuration2 with 1 \"CPU 1\", 1 \"Mainboard 2\", 1 \"GraCa amt\", " +
				"1 \"Monitor 19 Zoll\"");
		System.out.println("*** Testing configuration 2 - removeFromStock  ***");
		try {
			k2.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("\n*** Testing configuration with product not existing in warehouse ***");
		// Configuration with product with stock 0.
		Configuration k3 = new Configuration();
		k3.addProduct(pf.getProductByName("CPU 5"), 2);
		k3.addProduct(pf.getProductByName("Mainboard 3"), 1);
		k3.addProduct(pf.getProductByName("GraCa nsidia 1"), 2);
		k3.addProduct(pf.getProductByName("Monitor 2"), 1);
		System.out.println("Created Configuration3 with 2 \"CPU 5\", 1 \"Mainboard 3\", 2 \"GraCa nsidia 1\"" +
				", 1 \"Monitor 2\"");
		System.out.println("*** Testing configuration 3 - removeFromStock (should fail) ***");
		try {
			k3.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}

		System.out.println("\n*** Testing remove from stock working ***");
		System.out.println("Could build Configuration1 " + k1.getConfigurationStock(vitech) + " times.");
		// GraCa nsidia 5 should still be available.
		k1.removeFromStock(vitech);
		System.out.println("Built one, can still build: "  + k1.getConfigurationStock(vitech));

		System.out.println("\n*** Testing remove from stock failing ***");
		// GraCa nsidia 5 should not be available.
		System.out.println("Removing all \"GraCa nsidia 5\" cards, so Configuartion1 cannot be built anymore.");
		Product ns5 = pf.getProductByName("GraCa nsidia 5");
		vitech.decrementStock(ns5, vitech.getProductInStock(ns5));
		System.out.println("Configuration1 can be built " + k1.getConfigurationStock(vitech) + " times. Trying it anyway...");
		try {
			k1.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}
		/******************** Configuration Test end ********************/
		System.out.println("*** All tests finished ***");
	}
}
