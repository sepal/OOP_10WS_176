
public class CardSD extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardSD(String name) {
		super(name);
	}

	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (sd card inserted), otherwise false
	 */
	@Override
	public boolean insertIntoCardReader(CardSDReader sdr) {
		return sdr.getSlot().insert(this);
	}
}
