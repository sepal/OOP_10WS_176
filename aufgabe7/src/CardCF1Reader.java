
public class CardCF1Reader extends USBDevice<CardCF1>  {
	
	/**
	 *(postcondition) creates new cardreader for CF1 cards
	 */
	public CardCF1Reader() {
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
