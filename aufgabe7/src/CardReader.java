public class CardReader implements USBDevice {
	private Port<CardSD> sdslot;
	private Port<CardMiniSD> minisdslot;
	private Port<CardMicroSD> microsdslot;
	private Port<CardMS> msslot;
	private Port<CardCF1> cf1slot;
	private Port<CardCF2> cf2slot;
	
	/**
	 *(postcondition) creates cardReader with emtpy ports
	 */
	public CardReader() {
		sdslot = new Port<CardSD>();
		minisdslot = new Port<CardMiniSD>();
		microsdslot = new Port<CardMicroSD>();
		msslot = new Port<CardMS>();
		cf1slot = new Port<CardCF1>();
		cf2slot = new Port<CardCF2>();
	}

	/**
	 *(postcondition) returns SDPort
	 */
	public Port<CardSD> getSdslot() {
		return sdslot;
	}

	/**
	 *(postcondition) returns miniSDPort
	 */
	public Port<CardMiniSD> getMinisdslot() {
		return minisdslot;
	}

	/**
	 *(postcondition) returns microSDPort
	 */
	public Port<CardMicroSD> getMicrosdslot() {
		return microsdslot;
	}

	/**
	 *(postcondition) returns MSPort
	 */
	public Port<CardMS> getMsslot() {
		return msslot;
	}

	/**
	 *(postcondition) returns CF1Port
	 */
	public Port<CardCF1> getCf1slot() {
		return cf1slot;
	}

	/**
	 *(postcondition) returns CF2Port
	 */
	public Port<CardCF2> getCf2slot() {
		return cf2slot;
	}

	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
