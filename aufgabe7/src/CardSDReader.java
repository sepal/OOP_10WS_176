
public class CardSDReader extends USBDevice<CardSD> {
	
	/**
	 *(postcondition) creates new cardreader for SD cards
	 */
	public CardSDReader() {
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
