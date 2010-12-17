
public class CardMSReader extends USBDevice<CardMS> {
	
	public CardMSReader() {
		super();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
