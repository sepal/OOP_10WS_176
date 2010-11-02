package OOP;

import OOP.ProductFactory.Product;

/**
 * This interface allows a ProductGroup to store other ProductGroups as well as Products
 * and to treat them the same. When the user wants a list of items in a group and how many of them
 * are in stock for a given storage, this method is called and subsequently called in every
 * Product or ProduktGruppe contained in the ProductGroup.
 *
 */
public interface ProductGroupMember {
	/*
	 * (invariant) This method always returns a string array, with at least
	 * one element. That element being the name of the Server-object.
	 */
	public String[] listStock(Warehouse warehouse);
	public int getBaseprice();
	public int getMarketprice();
	public Product getCheapest();
	public int getStoragecosts(Warehouse w);
}
