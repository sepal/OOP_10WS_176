package OOP;

public class ProductAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -5537193804883505513L;
	
	public ProductAlreadyExistsException() {
		
	}
	
	public ProductAlreadyExistsException(String msg) {
		super(msg);
	}

}
