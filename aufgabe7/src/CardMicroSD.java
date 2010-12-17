
public class CardMicroSD extends CardMiniSD {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardMicroSD(String name) {
		super(name);
	}
	
	public boolean insertIntoCardReader(CardMicroSDReader microsd) {
		return microsd.getSlot().insert(this);
	}
}
