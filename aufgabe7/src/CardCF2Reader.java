
public class CardCF2Reader implements USBDevice {
	private Port<CardCF2> slot;
	
	public CardCF2Reader() {
		slot = new Port<CardCF2>();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}
	
	public Port<CardCF2> getSlot() {
		return slot;
	}
}
