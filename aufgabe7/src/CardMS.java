
public class CardMS extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardMS(String name) {
		super(name);
	}

	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (ms card inserted), otherwise false
	 */
	@Override
	public boolean insertIntoCardReader(CardMSReader msr) {
		return msr.getSlot().insert(this);
	}
}
