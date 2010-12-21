
public class CardMiniSDReader extends USBDevice<CardMiniSD> {
	
	/**
	 *(postcondition) creates new cardreader for miniSD cards
	 */
	public CardMiniSDReader() {
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
