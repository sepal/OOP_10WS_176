package OOP;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

import OOP.ProductFactory.Product;

/**
 * Represents a group of products, containing either products or further group of products.
 *
 */
public class ProductGroup extends StorageManager implements ProductGroupMember, Deletable  {
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
	
	public int getBaseprice() {
		int sumb = 0;

		for(ProductGroupMember pgm: members) {
			if(pgm instanceof Product) {
				int bprice = pgm.getBaseprice();
				sumb += bprice;
			} else if(pgm instanceof ProductGroup) {
				pgm.getBaseprice(); //rekursiv ok??
			}
		}
		return sumb;
	}
	
	public int getMarketprice() {
		int summ = 0;
		
		for(ProductGroupMember pgm: members) {
			if(pgm instanceof Product) {
				int mprice = pgm.getMarketprice();
				summ += mprice;
			} else if(pgm instanceof ProductGroup) {
				pgm.getMarketprice(); //stimmt das rekursive so?!
			}
		}
		return summ;
	}
	
	public int getStoragecosts(Warehouse w) {
		int sumsc = 0;
		for(ProductGroupMember pgm: members) {
			if(pgm instanceof Product) {
				int scprice = pgm.getStoragecosts(w);
				sumsc += scprice;
			} else if(pgm instanceof ProductGroup) {
				pgm.getStoragecosts(w);//same here wegen rekursiv
			}
		}
		return sumsc;
	}
	
	public Product getCheapest() {
		ListIterator<ProductGroupMember> i = (ListIterator<ProductGroupMember>) members.iterator();
		Product cheapest = null;
		while(i.hasNext()) {
			if(i instanceof Product) {
				if (i.previous() == null) {
					cheapest = (Product) i.next();
				}
				if (i.next().getBaseprice() < i.previous().getBaseprice()) {
					cheapest = (Product) i.next();
				} else {
					cheapest = (Product) i.previous();
				}
				return cheapest;
			}
			if(i instanceof ProductGroup) {
				getCheapest(); //hier wieder rekursiv
			}
		}
		return cheapest;
	}
	
	/**
	 * Adds a new member of the type ProduktGruppenMitglied to the group. 
	 * When the member is already in the group, an exception is thrown. Also if you try
	 * to add a ProduktGruppe to itself, an exception will be thrown.
	 * 
	 * @param newMember New member of group (beeing a Produkt or ProduktGruppe)
	 * @throws IllegalMemberException If the new member already is member of the group or the group itself.
	 */
	public void addMember(ProductGroupMember newMember) throws IllegalMemberException {
		if (newMember == this)
			throw new IllegalMemberException("Can't add ProductGroup to itself!");
		
		if (members.add(newMember) == false)
			throw new IllegalMemberException("Element is already a member of this ProduktGruppe!");
		
		return;
	}

	/**
	 * Implementation of the listStock method for ProduktGruppen.
	 * It iterates over the members of this group, calling their listStock methods
	 * and storing the resulting lists of Strings in an ArrayList to return the
	 * final list as a string array.
	 */
	@Override
	public String[] listStock(Warehouse w) {
		String[] tmp =  { this.name+":" };
		ArrayList<String> ret = new ArrayList<String>(Arrays.asList(tmp));
		
		for (ProductGroupMember pgm : members) {
			ret.addAll( Arrays.asList(pgm.listStock(w)) );
		}
		
		// If group has no members, just return name
		if (ret.isEmpty()) {
			return tmp;
		} else {
			// toArray just uses tmp to specify the type, a new array will be allocated by the function
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
