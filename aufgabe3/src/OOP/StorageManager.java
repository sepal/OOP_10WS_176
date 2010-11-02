package OOP;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * GOOD:
 * This class creates a very low object-coupling and still makes it possible to manage every
 * created object. It needs only a super() call to the StorageManagers constructor by the 
 * sub-class to get managed and so it can also be serialized by PersistenceManager or used for
 * referential integrity conserving deletion by ConsistencyManager.
 * 
 * There could be much more complex methods to manage and persistently store objects, that
 * would need the interface of a class to be adapted. But this requires little to no change
 * in the sub-class (little change for deletion, almost none for storage).
 */
public class StorageManager implements Serializable{
	private static final long serialVersionUID = 2533896750018151703L;
	
	protected static HashMap<Class<? extends StorageManager>, LinkedList<StorageManager>> storage =
		new HashMap<Class<? extends StorageManager>,  LinkedList<StorageManager>>();

	/**
	 * (Invariant?) This constructor has to be called by all objects of subclasses,
	 * so they can be managed.
	 * (post-condition) StorageManager has to store class (-object) of caller as key in HashMap 
	 * if no object of said class has been instantiated. Anyway it stores an object reference
	 * in the list identified by key (the class).
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
	 * (Precondition) A ProductFactory has been created and stored, or this method
	 * will return null. Also there should only be one ProductFactory. This method will only
	 * deliver the first in its list. There is no assurance which ProductFactory that is.
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
	 * (precondition) Returns a LinkedList of all objects created and stored of given type 'cl'.
	 * 'cl' has to be a subclass of StorageManager. If no list of given type exists, null will be returned.
	 *  The returned List contains objects of type StorageManager and have to bee casted to the given type
	 *  by the client.
	 * 
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