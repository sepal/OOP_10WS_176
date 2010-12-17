
public class CardMS extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as datamedium 
	 */
	public CardMS(String name) {
		super(name);
	}

	public boolean insertIntoCardReader(CardReader cr) {
		return cr.getMsslot().insert(this);
	}

	@Override
	public boolean insertIntoCardReader(CardCF1Reader cf1r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertIntoCardReader(CardCF2Reader cf2r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertIntoCardReader(CardMSReader msr) {
		return msr.getSlot().insert(this);
	}
}
