
public class CardSDReader extends USBDevice<CardSD> {
	//private Port<CardSD> slot;
	
	public CardSDReader() {
		//slot = new Port<CardSD>();
		super();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
