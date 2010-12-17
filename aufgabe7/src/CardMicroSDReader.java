
public class CardMicroSDReader extends USBDevice<CardMicroSD> {
	
	public CardMicroSDReader() {
		super();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
