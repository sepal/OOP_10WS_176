package OOP;

import java.util.HashSet;
import java.util.ArrayList;

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
	
	public void addMember(ProduktGruppenMitglied newMember) throws IllegalMemberException {
		if (newMember == this)
			throw new IllegalMemberException("Can't add ProduktGruppe to itself!");
		
		if (members.add(newMember) == false)
			throw new IllegalMemberException("Element is already a member of this ProduktGruppe!");
		
		return;
	}

	@Override
	public String[] listStock(Lager lager) {
		

		return null;
	}

}
