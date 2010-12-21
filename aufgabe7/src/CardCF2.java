
public class CardCF2 extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardCF2(String name) {
		super(name);
	}

	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (cf2 card inserted), otherwise false
	 */
	@Override
	public boolean insertIntoCardReader(CardCF2Reader cf2r) {
		return cf2r.getSlot().insert(this);
	}
}
