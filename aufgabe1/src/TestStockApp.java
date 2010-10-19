import OOP.*;
import OOP.ProduktVerwaltung.Produkt;

/**
 * This App tests the functionality of exercise 1
 *
 */
public class TestStockApp {
	
	public static void main(String args[]) {
		System.out.println("*** Starting test of exercise 1 app - Lagerverwaltung ***");
		System.out.println("*** Creating new Lager ***");
		
		ProduktVerwaltung pv = ProduktVerwaltung.getInstance();
		Lager vitech = new Lager();
		
		System.out.println("*** Creating new ProduktGruppen ***");

		ProduktGruppe comp = new ProduktGruppe("Components");
		
		ProduktGruppe cpus = new ProduktGruppe("CPUs");
		ProduktGruppe mainboards = new ProduktGruppe("Mainboards");
		ProduktGruppe gracas = new ProduktGruppe("Video cards");
		ProduktGruppe displays = new ProduktGruppe("Displays");
		
		ProduktGruppe nsidia = new ProduktGruppe("Nsidia");
		ProduktGruppe amt = new ProduktGruppe("Amt");
		
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
			Produkt cpu = pv.createProdukt("CPU " + i, "CPU desc " + i);
			cpus.addMember(cpu);
			vitech.incrementStock(cpu, 4);
		}
		
		for (int i=0; i<=5; i++) {
			Produkt monitor = pv.createProdukt("Monitor " + (19+i) + " Zoll", "LCD Monitor, groesse " + (19+i) + " Zoll");
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
			Produkt p = pv.createProdukt("Mainboard " + i, "Mainboard desc " + i);
			cpus.addMember(p);
			vitech.incrementStock(p, (int) Math.round(Math.random()*10 + 1));
		}

		// Adding more products for configurations.
		for (int i=0; i<=5; i++) {
			Produkt p = pv.createProdukt("GraCa nsidia " + i, "Grafic card desc " + i);
			nsidia.addMember(p);
			vitech.incrementStock(p, 2);
		}

		// Test product exists in configuration and lager but no stock is zero.
		Produkt amtGraca = pv.createProdukt("GraCa amt", "Grafic card desc");
		amt.addMember(amtGraca);
		vitech.incrementStock(amtGraca, 0);
		
		// Adding more products for configurations.
		Produkt monitor1 = pv.createProdukt("Monitor 1", "Monitor desc 1");
		cpus.addMember(monitor1);
		vitech.incrementStock(monitor1, 20);
		
		// Test product exists in configuration but not in Lager.
		Produkt monitor2 = pv.createProdukt("Monitor 2", "Monitor desc 2");
		cpus.addMember(monitor2);
		

		System.out.println("*** Testing get stock from Lager ***");
		Produkt p = pv.getProduktByName("Monitor 1");
		int stock = vitech.getProduktInStock(p);
		System.out.print(p.getName() + " - ");
		System.out.println(p.getDescription());
		System.out.println(stock + " items in Stock");
		

		System.out.println("*** Testing normal configuration ***");
		// Normal configuration test.
		Konfiguration k1 = new Konfiguration();
		k1.addProduct(pv.getProduktByName("CPU 1"), 1);
		k1.addProduct(pv.getProduktByName("Mainboard 3"), 1);
		k1.addProduct(pv.getProduktByName("GraCa nsidia 5"), 1);
		k1.addProduct(pv.getProduktByName("Monitor 1"), 1);
		System.out.println("*** Testing configuration 1 - removeFromStock  ***");
		k1.removeFromStock(vitech);
		
		System.out.println("*** Testing configuration with zero items in stock ***");
		// Configuration with product with stock 0.
		Konfiguration k2 = new Konfiguration();
		k2.addProduct(pv.getProduktByName("CPU 1"), 1);
		k2.addProduct(pv.getProduktByName("Mainboard 2"), 1);
		k2.addProduct(pv.getProduktByName("GraCa amt"), 1);
		k2.addProduct(pv.getProduktByName("Monitor 1"), 1);
		System.out.println("*** Testing configuration 2 - removeFromStock  ***");
		try {
			k2.removeFromStock(vitech);
		} catch (ProduktException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("*** Testing configuration with product not in stock ***");
		// Configuration with product with stock 0.
		Konfiguration k3 = new Konfiguration();
		k3.addProduct(pv.getProduktByName("CPU 5"), 2);
		k3.addProduct(pv.getProduktByName("Mainboard 3"), 1);
		k3.addProduct(pv.getProduktByName("GraCa nsidia 1"), 1);
		k3.addProduct(pv.getProduktByName("GraCa nsidia 5"), 1);
		k3.addProduct(pv.getProduktByName("Monitor 1"), 1);
		k3.addProduct(pv.getProduktByName("Monitor 2"), 1);
		System.out.println("*** Testing configuration 3 - removeFromStock  ***");
		try {
			k3.removeFromStock(vitech);
		} catch (ProduktException e) {
			System.out.println(e.toString());
		}

		System.out.println("*** Testing removing from stock with were should be available ***");
		// GraCa nsidia 5 should still be available.
		k1.removeFromStock(vitech);

		System.out.println("*** Testing removing from stock with were should not be available ***");
		// GraCa nsidia 5 should not be available.
		try {
			k1.removeFromStock(vitech);
		} catch (ProduktException e) {
			System.out.println(e.toString());
		}
		
		
	}
}
