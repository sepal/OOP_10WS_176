
public class CardMS extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardMS(String name) {
		super(name);
	}

	public boolean insertIntoCardReader(CardReader cr) {
		return cr.getMsslot().insert(this);
	}
}
