
public class CardMicroSDReader extends USBDevice<CardMicroSD> {
	
	/**
	 *(postcondition) creates new cardreader for microSD cards
	 */
	public CardMicroSDReader() {
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
