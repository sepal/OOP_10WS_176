
public class CardCF2Reader extends USBDevice<CardCF2>{
	
	/**
	 *(postcondition) creates new cardreader for CF2 cards
	 */
	public CardCF2Reader() {
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
