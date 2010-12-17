
public class CardCF1Reader extends USBDevice<CardCF1>  {
	
	public CardCF1Reader() {
		super();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoCardReader(this);
	}
}
