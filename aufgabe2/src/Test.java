import OOP.*;
import OOP.ProductFactory.Product;

/**
 * This App tests the functionality of exercise 1
 *
 */
public class Test {
	
	public static void main(String args[]) {
		System.out.println("*** Starting test of exercise 1 app - Lagerverwaltung ***");
		System.out.println("*** Creating new Lager ***");
		
		ProductFactory pv = ProductFactory.getInstance();
		Warehouse vitech = new Warehouse();
		
		System.out.println("*** Creating new ProductGruppen ***");

		ProductGroup comp = new ProductGroup("Components");
		
		ProductGroup cpus = new ProductGroup("CPUs");
		ProductGroup mainboards = new ProductGroup("Mainboards");
		ProductGroup gracas = new ProductGroup("Video cards");
		ProductGroup displays = new ProductGroup("Displays");
		
		ProductGroup nsidia = new ProductGroup("Nsidia");
		ProductGroup amt = new ProductGroup("Amt");
		
		System.out.println("*** Adding groups to groups ***");

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
		
		// General product test.
		for (int i=0; i<=5; i++) {
			Product cpu = pv.createProduct("CPU " + i, "CPU desc " + i);
			cpus.addMember(cpu);
			vitech.incrementStock(cpu, 4);
		}
		
		for (int i=0; i<=5; i++) {
			Product monitor = pv.createProduct("Monitor " + (19+i) + " Zoll", "LCD Monitor, groesse " + (19+i) + " Zoll");
			System.out.println(monitor.getName());
			displays.addMember(monitor);
			vitech.incrementStock(monitor, 4);
		}
		
		// Gruppen-listing Test
		String[] list = comp.listStock(vitech);
		System.out.println("*** Testing 'Auflisten des Gruppeninhaltes mit Lagerbestand' ***\n");
		for (String line : list) {
			System.out.println(line);
		}
		
		// Adding more products for configurations.
		for (int i=0; i<=3; i++) {
			Product p = pv.createProduct("Mainboard " + i, "Mainboard desc " + i);
			cpus.addMember(p);
			vitech.incrementStock(p, (int) Math.round(Math.random()*10 + 1));
		}

		// Adding more products for configurations.
		for (int i=0; i<=5; i++) {
			Product p = pv.createProduct("GraCa nsidia " + i, "Grafic card desc " + i);
			nsidia.addMember(p);
			vitech.incrementStock(p, 2);
		}

		// Test product exists in configuration and lager but no stock is zero.
		Product amtGraca = pv.createProduct("GraCa amt", "Grafic card desc");
		amt.addMember(amtGraca);
		vitech.incrementStock(amtGraca, 0);
		
		// Adding more products for configurations.
		Product monitor1 = pv.createProduct("Monitor 1", "Monitor desc 1");
		cpus.addMember(monitor1);
		vitech.incrementStock(monitor1, 20);
		
		// Test product exists in configuration but not in Lager.
		Product monitor2 = pv.createProduct("Monitor 2", "Monitor desc 2");
		cpus.addMember(monitor2);
		

		System.out.println("*** Testing get stock from Lager ***");
		Product p = pv.getProductByName("Monitor 1");
		int stock = vitech.getProductInStock(p);
		System.out.print(p.getName() + " - ");
		System.out.println(p.getDescription());
		System.out.println(stock + " items in Stock");
		

		System.out.println("*** Testing normal configuration ***");
		// Normal configuration test.
		Configuration k1 = new Configuration();
		k1.addProduct(pv.getProductByName("CPU 1"), 1);
		k1.addProduct(pv.getProductByName("Mainboard 3"), 1);
		k1.addProduct(pv.getProductByName("GraCa nsidia 5"), 1);
		k1.addProduct(pv.getProductByName("Monitor 1"), 1);
		System.out.println("*** Testing configuration 1 - removeFromStock  ***");
		k1.removeFromStock(vitech);
		
		System.out.println("*** Testing configuration with zero items in stock ***");
		// Configuration with product with stock 0.
		Configuration k2 = new Configuration();
		k2.addProduct(pv.getProductByName("CPU 1"), 1);
		k2.addProduct(pv.getProductByName("Mainboard 2"), 1);
		k2.addProduct(pv.getProductByName("GraCa amt"), 1);
		k2.addProduct(pv.getProductByName("Monitor 1"), 1);
		System.out.println("*** Testing configuration 2 - removeFromStock  ***");
		try {
			k2.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("*** Testing configuration with product not in stock ***");
		// Configuration with product with stock 0.
		Configuration k3 = new Configuration();
		k3.addProduct(pv.getProductByName("CPU 5"), 2);
		k3.addProduct(pv.getProductByName("Mainboard 3"), 1);
		k3.addProduct(pv.getProductByName("GraCa nsidia 1"), 1);
		k3.addProduct(pv.getProductByName("GraCa nsidia 5"), 1);
		k3.addProduct(pv.getProductByName("Monitor 1"), 1);
		k3.addProduct(pv.getProductByName("Monitor 2"), 1);
		System.out.println("*** Testing configuration 3 - removeFromStock  ***");
		try {
			k3.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}

		System.out.println("*** Testing removing from stock with were should be available ***");
		// GraCa nsidia 5 should still be available.
		k1.removeFromStock(vitech);

		System.out.println("*** Testing removing from stock with were should not be available ***");
		// GraCa nsidia 5 should not be available.
		try {
			k1.removeFromStock(vitech);
		} catch (ProductException e) {
			System.out.println(e.toString());
		}
		
		
	}
}
