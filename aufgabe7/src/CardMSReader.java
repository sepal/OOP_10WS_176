
public class CardMSReader extends USBDevice<CardMS> {
	
	/**
	 *(postcondition) creates new cardreader for MS cards
	 */
	public CardMSReader() {
		super();
	}
	
	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns true, if slot was free (datamedium inserted), otherwise false
	 */
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
