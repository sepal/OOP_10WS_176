package OOP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

public class PersistenceManager extends StorageManager {
	private static final long serialVersionUID = 7035068205475226503L;

	/**
	 * (precondition) This method fails if program has insufficient rights
	 * to write in current directory, directory has not enough space left or any
	 * other reason file cannot be written. 
	 * The file "storage.data" will be overwritten if existent and has to be backed up
	 * by the client if necessary.
	 * 
	 * ERROR:
	 * Nobody actually checks if the file can be written and if not, the client cannot
	 * find out why. Also, the client can not specify a different location for the file.
	 * Solution: 
	 * Either the Server checks for said conditions and gives the client sufficient information
	 * to deal with the problem, e.g., throwing exceptions.
	 * Or the server expects the client to take care of this and gives him the opportunity to
	 * specify a (safe) location to write to.
	 * 
	 * I would implement a mix of both. Changing the return type to void and adding exceptions,
	 * as well as a parameter to specify a location (and check that location).
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
	 * (precondition) File storage.data has to exist and be valid or this method will return false.
	 * Current content of object storage (pretty much all objects that have been created in this App)
	 * will be deleted through this method and have to be saved by the client if necessary.
	 * 
	 * ERROR:
	 * Like the store method, this method should deliver more information to the client
	 * and let him choose a location. Also, there is no check if the file exists, can be
	 * read and actually contains what is expected. It just tries to read it and if it fails
	 * at some point, stops.
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
