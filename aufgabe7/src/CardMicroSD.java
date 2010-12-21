
public class CardMicroSD extends CardMiniSD {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardMicroSD(String name) {
		super(name);
	}
	
	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (microsd inserted), otherwise false
	 */
	public boolean insertIntoCardReader(CardMicroSDReader microsd) {
		return microsd.getSlot().insert(this);
	}
}
