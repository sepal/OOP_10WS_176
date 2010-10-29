package OOP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class is responsible for storing the application data on disk and
 * restoring it again. It uses the object storage of StorageManager to
 * get references to every object for serialization.
 * 
 * @author ben
 *
 */
public class PersistenceManager extends StorageManager {
	private static final long serialVersionUID = 7035068205475226503L;

	/**
	 * Stores the HashMap storage which contains references to all created
	 * Objects in the file "storage.data"
	 * 
	 * @return Returns true if stored successfully, false if an exception occurred.
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
			System.err.println("Error in PersistenceManager.storeData(): " + ioex.getMessage()+
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
}
