package OOP;

import OOP.ProduktVerwaltung.Produkt;

/**
 * This interface allows a ProduktGruppe to store other ProduktGruppen as well as Produkte
 * and to treat them the same. When the user wants a list of items in a group and how many of them
 * are in stock for a given storage, this method is called and subsequently called in every
 * Produkt or ProduktGruppe contained in the ProduktGruppe.
 *
 */
public interface ProduktGruppenMitglied {
	public String[] listStock(Lager lager);
	public int getBaseprice();
	public int getMarketprice();
	public Produkt getCheapest(Lager l);
	public int getStoragecosts(Lager l);
}
