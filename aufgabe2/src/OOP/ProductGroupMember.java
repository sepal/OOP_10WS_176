package OOP;

import OOP.ProductFactory.Product;

/**
 * This interface allows a ProduktGruppe to store other ProduktGruppen as well as Produkte
 * and to treat them the same. When the user wants a list of items in a group and how many of them
 * are in stock for a given storage, this method is called and subsequently called in every
 * Produkt or ProduktGruppe contained in the ProduktGruppe.
 *
 */
public interface ProductGroupMember {
	public String[] listStock(Warehouse w);
	public int getBaseprice();
	public int getMarketprice();
	public Product getCheapest(Warehouse w);
	public int getStoragecosts(Warehouse w);
}
