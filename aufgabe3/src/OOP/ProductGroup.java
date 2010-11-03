package OOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import OOP.ProductFactory.Product;

/**
 * Represents a group of products, containing either products or further group
 * of products.
 * 
 */
public class ProductGroup extends StorageManager implements ProductGroupMember,
		Deletable {
	private static final long serialVersionUID = -2774890152346175212L;

	private boolean hasBeenDeleted = false;
	private String name;
	private HashSet<ProductGroupMember> members;

	public ProductGroup(String name) {
		super();

		this.name = name;
		members = new HashSet<ProductGroupMember>();
	}

	public String getName() {
		return name;
	}

	/**
	 * (precondition) productgroupmember must exist
	 * (postcondition) returns sum of all baseprices of the products in the productgroup and subproductgroups
	 */
	public int getBaseprice() {
		int sumb = 0;

		for (ProductGroupMember pgm : members) {
			if (pgm instanceof Product) {
				int bprice = pgm.getBaseprice();
				sumb += bprice;
			} else if (pgm instanceof ProductGroup) {
				pgm.getBaseprice();
			}
		}
		return sumb;
	}

	/**
	 * (precondition) productgroupmember must exist
	 * (postcondition) returns sum of all marketprices of the products in the productgroup and subproductgroups
	 */
	public int getMarketprice() {
		int summ = 0;

		for (ProductGroupMember pgm : members) {
			if (pgm instanceof Product) {
				int mprice = pgm.getMarketprice();
				summ += mprice;
			} else if (pgm instanceof ProductGroup) {
				pgm.getMarketprice(); // stimmt das rekursive so?!
			}
		}
		return summ;
	}

	/**
	 * (precondition) productgroupmember and warehouse must exist
	 * (postcondition) returns sum of all storagecosts of the products in the productgroup and subproductgroups
	 */
	public int getStoragecosts(Warehouse w) {
		int sumsc = 0;
		for (ProductGroupMember pgm : members) {
			if (pgm instanceof Product) {
				int scprice = pgm.getStoragecosts(w);
				sumsc += scprice;
			} else if (pgm instanceof ProductGroup) {
				pgm.getStoragecosts(w);// same here wegen rekursiv
			}
		}
		return sumsc;
	}

	/**
	 * (precondition) productgroupmember must exist
	 * (postcondition) returns cheapest product of the productgroup and the subproductgroups
	 */
	public Product getCheapest() {
		Product cheapest = null;

		for (ProductGroupMember pgm : members) {
			if (pgm instanceof Product) {
				if (cheapest == null
						|| cheapest.getBaseprice() > ((Product) pgm)
								.getBaseprice()) {
					cheapest = (Product) pgm;
				}
			} else if (pgm instanceof ProductGroup) {
				Product ptmp = pgm.getCheapest();
				if (ptmp != null
						&& (cheapest == null || cheapest.getBaseprice() > ptmp
								.getBaseprice())) {
					cheapest = ptmp;
				}
			}
		}

		return cheapest;
	}

	/**
	 * Adds a new member of the type ProduktGruppenMitglied to the group. When
	 * the member is already in the group, an exception is thrown. Also if you
	 * try to add a ProduktGruppe to itself, an exception will be thrown.
	 * 
	 * @param newMember
	 *            New member of group (beeing a Produkt or ProduktGruppe)
	 * @throws IllegalMemberException
	 *             If the new member already is member of the group or the group
	 *             itself.
	 */
	/**
	 * (precondition) New member newMember cannot be the ProductGroup you try to
	 * add it to. Also newMember cannot be already a member of the ProductGroup.
	 */
	public void addMember(ProductGroupMember newMember)
			throws IllegalMemberException {
		if (newMember == this)
			throw new IllegalMemberException(
					"Can't add ProductGroup to itself!");

		if (members.add(newMember) == false)
			throw new IllegalMemberException(
					"Element is already a member of this ProduktGruppe!");

		return;
	}
	
	/*
	 * GOOD:
	 * Uses dynamic binding to get a list of the stock of members.lang
	 */
	
	/**
	 * (invariant) This method will always return a String array with at
	 * least one element, that being the name of the Group.
	 * 
	 * (precondition) Implementations of ProductGroupMember that are members of
	 * this Group and therefore get their listStock method called have to implement
	 * it so, that it returns as the first element its name and further the names
	 * (and quantities) of any existing members (one element per string).
	 */
	@Override
	public String[] listStock(Warehouse w) {
		String[] tmp = { this.name + ":" };
		ArrayList<String> ret = new ArrayList<String>(Arrays.asList(tmp));

		for (ProductGroupMember pgm : members) {
			ret.addAll(Arrays.asList(pgm.listStock(w)));
		}

		// If group has no members, just return name
		if (ret.isEmpty()) {
			return tmp;
		} else {
			// toArray just uses tmp to specify the type, a new array will be
			// allocated by the function
			return ret.toArray(tmp);
		}
	}

	@Override
	public void deleteLocalReferencesTo(Deletable ref) {
		if (ref instanceof ProductGroupMember) {
			members.remove(ref);
		}
	}

	@Override
	public void deleteAllReferencesTo(Deletable ref) {
		if (ref instanceof ProductGroupMember) {
			deleteLocalReferencesTo(ref);
			ConsistencyManager.deleteAllReferencesTo(ref);
		}
	}

	public void delete() {
		deleteAllReferencesTo(this);
		members = null;
		hasBeenDeleted = true;
	}

	@Override
	public boolean hasBeenDeleted() {
		return hasBeenDeleted;
	}
}
