package OOP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class StorageManager implements Serializable {
	private static final long serialVersionUID = 2533896750018151703L;
	
	protected static HashMap<Class<? extends StorageManager>, LinkedList<StorageManager>> storage =
		new HashMap<Class<? extends StorageManager>,  LinkedList<StorageManager>>();

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
	 * Stores the HashMap storage which contains references to all created
	 * Objects in the file "storage.data"
	 * 
	 * @return Returns true if stored successfully, false if an exception occured.
	 */
	public static boolean storeData() {
		try {
			// Write to disk with FileOutputStream
			FileOutputStream fout = new FileOutputStream("storage.data");
			// Write object with ObjectOutputStream
			ObjectOutputStream objout = new  ObjectOutputStream (fout);
			// Write object out to disk
			objout.writeObject( storage );
			
			fout.close();
			objout.close();
			
		} catch (IOException ioex) {
			System.err.println("Error in StorageManager.storeData(): " + ioex.getMessage()+
					"\n" + ioex.getStackTrace());
			return false;
		}
		
		return true;
	}
	
	/**
	 * Erases all created objects stored currently in the StorageManager and
	 * loads the file "storage.data" to restore the data saved there. 
	 * If this method returns "false" and the exception occurred before the actual
	 * data was overwritten, the current data could be still intact.
	 * 
	 * @return Returns true if it successfully loaded the old data, false if that failed.
	 */
	@SuppressWarnings("unchecked")
	public static boolean loadDataDiscardCurrent() {
		Object obj = null;
		
		try {
			// Read from disk using FileInputStream
			FileInputStream fin = new FileInputStream("storage.data");
			// Read object using ObjectInputStream
			ObjectInputStream objin = new ObjectInputStream (fin);
			// Read the actual Object
			obj = objin.readObject();
			
			if (obj instanceof HashMap) {
				// Only Object we store is the "storage" so it should be safe to cast back to HashMap
				 storage = (HashMap<Class<? extends StorageManager>, LinkedList<StorageManager>>) obj;
			}
			
			fin.close();
			objin.close();
			
		} catch (IOException ioex) {
			System.err.println(ioex.getMessage());
			return false;
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
			return false;
		} catch (ClassCastException cce) {
			System.err.println(cce.getMessage());
			return false;
		} finally{
			if (storage == null) return false;
		}
		
		return true;
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