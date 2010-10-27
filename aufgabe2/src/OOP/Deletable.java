package OOP;

/**
 * Makes sure an object knows how to deal with delete requests and works
 * together with the ConsistencyManager.
 * 
 * @author ben
 *
 */
public interface Deletable {
	public boolean hasBeenDeleted();
	public void delete();
	public void deleteLocalReferencesTo(Deletable ref);
	public void deleteAllReferencesTo(Deletable ref);
}
