package OOP;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

import OOP.ProduktVerwaltung.Produkt;

/**
 * Represents a group of products, containing either products or further group of products.
 *
 */
public class ProduktGruppe implements ProduktGruppenMitglied {
	private String name;
	private HashSet<ProduktGruppenMitglied> members;
	
	public ProduktGruppe(String name) {
		this.name = name;
		members = new HashSet<ProduktGruppenMitglied>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getBaseprice() {
		int sumb = 0;

		for(ProduktGruppenMitglied pgm: members) {
			if(pgm instanceof Produkt) {
				int bprice = pgm.getBaseprice();
				sumb += bprice;
			} else if(pgm instanceof ProduktGruppe) {
				pgm.getBaseprice(); //rekursiv ok??
			}
		}
		return sumb;
	}
	
	public int getMarketprice() {
		int summ = 0;
		
		for(ProduktGruppenMitglied pgm: members) {
			if(pgm instanceof Produkt) {
				int mprice = pgm.getMarketprice();
				summ += mprice;
			} else if(pgm instanceof ProduktGruppe) {
				pgm.getMarketprice(); //stimmt das rekursive so?!
			}
		}
		return summ;
	}
	
	public Produkt getCheapest(Lager l) {
		for(ProduktGruppenMitglied pgm: members) {
			if(pgm instanceof Produkt) {
				this.getBaseprice();
			}
		}
		return null;
	}
	
	/**
	 * Adds a new member of the type ProduktGruppenMitglied to the group. 
	 * When the member is already in the group, an exception is thrown. Also if you try
	 * to add a ProduktGruppe to itself, an exception will be thrown.
	 * 
	 * @param newMember New member of group (beeing a Produkt or ProduktGruppe)
	 * @throws IllegalMemberException If the new member already is member of the group or the group itself.
	 */
	public void addMember(ProduktGruppenMitglied newMember) throws IllegalMemberException {
		if (newMember == this)
			throw new IllegalMemberException("Can't add ProduktGruppe to itself!");
		
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
	public String[] listStock(Lager lager) {
		String[] tmp =  { this.name+":" };
		ArrayList<String> ret = new ArrayList<String>(Arrays.asList(tmp));
		
		for (ProduktGruppenMitglied pgm : members) {
			ret.addAll( Arrays.asList(pgm.listStock(lager)) );
		}
		
		// If group has no members, just return name
		if (ret.isEmpty()) {
			return tmp;
		} else {
			// toArray just uses tmp to specify the type, a new array will be allocated by the function
			return ret.toArray(tmp);
		}
	}

}
