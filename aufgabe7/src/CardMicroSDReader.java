
public class CardMicroSDReader extends USBDevice {


	private Port<CardMicroSD> slot;
	
	public CardMicroSDReader() {
		slot = new Port<CardMicroSD>();
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
	
	public Port<CardMicroSD> getSlot() {
		return slot;
	}

}
