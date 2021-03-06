package OOP;

/**
 * Exceptions for ProduktGruppe. Raised when adding a member to the group is not possible/allowed.
 *
 */
public class IllegalMemberException extends RuntimeException {
	private static final long serialVersionUID = -1252409003211874259L;

	public IllegalMemberException(String msg) {
		super(msg);
	}
	
	public IllegalMemberException() {
		super();
	}
}
