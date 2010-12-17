
public class CardMS extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardMS(String name) {
		super(name);
	}

	@Override
	public boolean insertIntoCardReader(CardMSReader msr) {
		return msr.getSlot().insert(this);
	}
}
