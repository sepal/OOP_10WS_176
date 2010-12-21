
public class CardMiniSD extends CardSD {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardMiniSD(String name) {
		super(name);
	}
	
	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (minisd inserted), otherwise false
	 */
	public boolean insertIntoCardReader(CardMiniSDReader minisd) {
		return minisd.getSlot().insert(this);
	}
}
