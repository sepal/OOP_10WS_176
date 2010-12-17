
public class CardCF2Reader extends USBDevice<CardCF2>{
	
	public CardCF2Reader() {
		super();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
