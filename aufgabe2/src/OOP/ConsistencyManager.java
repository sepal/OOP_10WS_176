package OOP;

import java.util.Iterator;
import java.util.Set;

/**
 * This class, which works closely together with StorageManager, is
 * responsible for the removal of every reference to an object if its 
 * deletion is requested. It uses StorageManagers object list for this
 * task and requests every object to delete any local references of the
 * removed object if existing.
 * 
 * @author ben
 *
 */
public class ConsistencyManager extends StorageManager {
	private static final long serialVersionUID = -6041808545355841203L;

	public static void deleteAllReferencesTo(Deletable ref) {
		Set<Class<? extends StorageManager>> keys = storage.keySet();
		@SuppressWarnings("rawtypes")
		Class[] interfaces = null;
		boolean isDeletable = false;
		Deletable del = null;
		StorageManager tmp = null;
		for (Class<? extends StorageManager> key : keys) {
			isDeletable = false;
			interfaces = key.getInterfaces();
			for (int i = 0; interfaces != null && i < interfaces.length; i++) {
				if (interfaces[i].equals(Deletable.class)) {
					isDeletable = true;
					break;
				}
			}
			
			if (isDeletable) {
				for (Iterator<StorageManager> it = storage.get(key).iterator(); it.hasNext();) {
					tmp = it.next();
					if (tmp instanceof Deletable) {
						del = (Deletable) tmp;
						del.deleteLocalReferencesTo(ref);
					}
				}
			}
		}
	}
}
