public class CardReader implements USBDevice {
	private String name;
	private Port<CardSD> sdslot;
	private Port<CardMiniSD> minisdslot;
	private Port<CardMicroSD> microsdslot;
	private Port<CardMS> msslot;
	private Port<CardCF1> cf1slot;
	private Port<CardCF2> cf2slot;
	
	public CardReader(String name) {
		this.name = name;
		sdslot = new Port<CardSD>();
		minisdslot = new Port<CardMiniSD>();
		microsdslot = new Port<CardMicroSD>();
		msslot = new Port<CardMS>();
		cf1slot = new Port<CardCF1>();
		cf2slot = new Port<CardCF2>();
	}

	public Port<CardSD> getSdslot() {
		return sdslot;
	}

	public Port<CardMiniSD> getMinisdslot() {
		return minisdslot;
	}

	public Port<CardMicroSD> getMicrosdslot() {
		return microsdslot;
	}

	public Port<CardMS> getMsslot() {
		return msslot;
	}

	public Port<CardCF1> getCf1slot() {
		return cf1slot;
	}

	public Port<CardCF2> getCf2slot() {
		return cf2slot;
	}

	@Override
	public String getName() {
		return name;
	}
}
