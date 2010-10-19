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
			Produkt monitor = pv.createProdukt("Monitor " + (19+i) + " Zoll", "LCD Monitor, größe " + (19+i) + " Zoll");
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
			vitech.incrementStock(p, (int) Math.round(Math.random()*10 + 1));
		}

		// Test product exists in configuration and lager but no stock is zero.
		Produkt amtGraca = pv.createProdukt("GraCa amt ", "Grafic card desc ");
		amt.addMember(amtGraca);
		vitech.incrementStock(amtGraca, 0);

		// Adding more products for configurations.
		for (int i=0; i<=4; i++) {
			Produkt p = pv.createProdukt("Monitor " + i, "Monitor desc " + i);
			cpus.addMember(p);
			vitech.incrementStock(p, (int) Math.round(Math.random()*10 + 1));
		}

		// Adding more products for configurations.
		Produkt monitor1 = pv.createProdukt("Monitor 1", "Monitor desc 1");
		cpus.addMember(monitor1);
		vitech.incrementStock(monitor1, 20);
		
		// Test product exists in configuration but not in Lager.
		Produkt monitor2 = pv.createProdukt("Monitor 2", "Monitor desc 2");
		cpus.addMember(monitor2);
		
		
		
	}
}
