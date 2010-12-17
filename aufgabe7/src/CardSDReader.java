
public class CardSDReader extends USBDevice<CardSD> {
	public CardSDReader() {
		super();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
