
public class CardCF1Reader implements USBDevice {
	private Port<CardCF1> slot;
	
	public CardCF1Reader() {
		slot = new Port<CardCF1>();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}

	public Port<CardCF1> getSlot() {
		return slot;
	}
}
