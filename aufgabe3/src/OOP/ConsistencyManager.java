package OOP;

import java.util.Iterator;
import java.util.Set;

public class ConsistencyManager extends StorageManager {
	private static final long serialVersionUID = -6041808545355841203L;

	/*
	 * (postcondition) The method calls all stored objects to delete all references to ref.
	 * 
	 * BUG:
	 * Realized there is a bug. The object is never actually deleted from the
	 * StorageManager itself. Fixed it.
	 */
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
					if (tmp == ref) {
						it.remove();
					} else if (tmp instanceof Deletable) {
						del = (Deletable) tmp;
						del.deleteLocalReferencesTo(ref);
					}
				}
			}
		}
	}
}
