package OOP;

/**
 * Indicates exceptions regarding Produkte, like trying to dicrease stock of
 * a Produkt that is not in stock at all.
 *
 */
public class ProduktException extends RuntimeException {
	private static final long serialVersionUID = 9041239970867761445L;
	
	public ProduktException() {
		super();
	}
	
	public ProduktException(String msg) {
		super(msg);
	}
}
