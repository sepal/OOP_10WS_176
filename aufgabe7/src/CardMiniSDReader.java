
public class CardMiniSDReader extends USBDevice<CardMiniSD> {
	
	public CardMiniSDReader() {
		super();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
