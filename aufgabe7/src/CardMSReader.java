
public class CardMSReader implements USBDevice {
	private Port<CardMS> slot;
	
	public CardMSReader() {
		slot = new Port<CardMS>();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}
	
	public Port<CardMS> getSlot() {
		return slot;
	}
	
	public String getName() {
		return slot.toString();
	}
	
	public String toString() {
		return getName();
	}
}
