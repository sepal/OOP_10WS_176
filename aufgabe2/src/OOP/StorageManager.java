package OOP;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class is the base class for every class, whichs objects should
 * be managed by this application and therefore be able to be stored on disk
 * and to support consistent deletion operations.
 * 
 * @author ben
 *
 */
public class StorageManager implements Serializable{
	private static final long serialVersionUID = 2533896750018151703L;
	
	// It stores for every Class (object) a LinkedList with object-references.
	protected static HashMap<Class<? extends StorageManager>, LinkedList<StorageManager>> storage =
		new HashMap<Class<? extends StorageManager>,  LinkedList<StorageManager>>();

	/**
	 * This constructor will be called by all subclasses and stores an object-reference
	 * of the new object in a LinkedList. These lists are identified by the class of
	 * the objects in the list.
	 */
	protected StorageManager() {
		LinkedList<StorageManager> ll;
		Class<? extends StorageManager> cl = this.getClass();
		
		if (storage.containsKey(cl)) {
			ll = storage.get(cl);
			ll.add(this);
		} else {
			ll = new LinkedList<StorageManager>();
			ll.add(this);
			storage.put(cl, ll);
		}
	}
	
	/**
	 * Returns the first created and stored ProductFactory instance. Since ProductFactory
	 * is a singleton, there should be only one. If none was created or stored yet, it
	 * will return null.
	 * 
	 * @return ProductFactory reference or null. See above.
	 */
	public static ProductFactory getProductFactory() {
		LinkedList<StorageManager> ll;
		
		ll = storage.get(ProductFactory.class);
		if (ll == null) {
			System.err.println("Does not exist");
			return null;
		}
		
		StorageManager tmp = ll.getFirst();
		if (tmp instanceof ProductFactory) {
			return (ProductFactory) tmp;
		}
		
		return null;
	}
	
	/**
	 * Returns a LinkedList containing all the objects created and stored of given
	 * class. They will be of type StorageManager and have to be casted to their
	 * specific sub-type if necessary.
	 * If no objects of given class exist, null will be returned
	 * 
	 * @return LinkedList with objects or null.
	 */
	public static LinkedList<? extends StorageManager> getCreatedObjectsOfType(Class<? extends StorageManager> cl) {
		return storage.get(cl);
	}
	
	/* DEBUG
	public static void printData() {
		java.util.Set<Class<? extends StorageManager>> keys 
		= storage.keySet();
		LinkedList<StorageManager> ll;
		
		for (Class<? extends StorageManager> cl : keys) {
			System.out.println("\n*** Class: " + cl.getName());
			ll = storage.get(cl);
			for (java.util.Iterator<StorageManager> it = ll.iterator(); it.hasNext(); ) {
				System.out.println(it.next().toString());
			}
		}
	}*/
}