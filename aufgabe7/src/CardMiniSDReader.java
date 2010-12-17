
public class CardMiniSDReader implements USBDevice {

	private Port<CardMiniSD> slot;
	
	public CardMiniSDReader() {
		slot = new Port<CardMiniSD>();
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
	
	public Port<CardMiniSD> getSlot() {
		return slot;
	}

}
