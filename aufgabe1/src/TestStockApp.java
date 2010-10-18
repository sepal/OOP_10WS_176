import OOP.*;
import OOP.ProduktVerwaltung.Produkt;

public class TestStockApp {
	
	public void main(String args[]) {
		ProduktVerwaltung pv = ProduktVerwaltung.getInstance();
		Lager vitech = new Lager();
		
		ProduktGruppe comp = new ProduktGruppe("Komponents");
		
		ProduktGruppe cpus = new ProduktGruppe("CPU");
		ProduktGruppe mainboards = new ProduktGruppe("Mainboard");
		ProduktGruppe gracas = new ProduktGruppe("Graficcards");
		
		ProduktGruppe nsidia = new ProduktGruppe("Nsidia");
		ProduktGruppe amt = new ProduktGruppe("Amt");
		
		ProduktGruppe monitors = new ProduktGruppe("Monitors");
		
		// Test group in group.
		comp.addMember(cpus);
		comp.addMember(mainboards);
		comp.addMember(gracas);
		comp.addMember(monitors);
		
		// Test group in group in group.
		gracas.addMember(nsidia);
		gracas.addMember(amt);
		
		// General product test.
		for (int i=0; i<=5; i++) {
			Produkt cpu = pv.createProdukt("CPU " + i, "CPU desc " + i);
			cpus.addMember(cpu);
			vitech.incrementStock(cpu, 4);
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
