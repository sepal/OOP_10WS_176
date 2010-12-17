
public class CardCF1 extends CardCF2 {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardCF1(String name) {
		super(name);
	}
	
	@Override
	public boolean insertIntoCardReader(CardCF1Reader cf1r) {
		return cf1r.getSlot().insert(this);
	}
}
