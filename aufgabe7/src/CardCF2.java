
public class CardCF2 extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardCF2(String name) {
		super(name);
	}

	@Override
	public boolean insertIntoCardReader(CardReader cr) {
		return cr.getCf2slot().insert(this);
	}
}
