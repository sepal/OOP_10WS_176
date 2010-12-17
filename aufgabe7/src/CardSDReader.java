
public class CardSDReader implements USBDevice {
	private Port<CardSD> slot;
	
	public CardSDReader() {
		slot = new Port<CardSD>();
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
	
	public Port<CardSD> getSlot() {
		return slot;
	}

}
