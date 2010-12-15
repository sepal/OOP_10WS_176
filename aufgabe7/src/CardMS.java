/**
 * Memory Stick
 * @author sebastian
 *
 */
public class CardMS extends DataMedium {

	public CardMS(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public boolean insertIntoCardReader(CardReader creader) {
		return creader.getMsslot().insert(this);
	}
}
