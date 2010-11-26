/**
 * A simple Map implemented as linked list, storing key - value pairs.
 * Keys must not be null and have to be unique.
 *
 */
public class SimpleMap {
	private Object key, value;
	private SimpleMap next;
	
	/**
	 * Creates a new SimpleMap for the given key - value pair. The caller is
	 * responsible for checking the validity of key. If key is null, an assertion will
	 * lead to an error. 
	 * 
	 * @param key Must not be null and has to be unique.
	 * @param value Can be any Object (including null).
	 */
	/*
	 * pre-condition: key has to be unique (not yet in this map) and not null.
	 * post-condition: A new SimpleMap object containing key and value has been created or the programm
	 * will terminate with an error if key is null.
	 */
	private SimpleMap(Object key, Object value) {
		assert (key != null) : "Key in SimpleMap must not be null!";
		this.key = key;
	}
	
	/**
	 * Creates an empty SingleMap. Note that key is just null, because the list is empty.
	 * Adding null as key is not possible.
	 */
	public SimpleMap() {
		key = value = null;
		next = null;
	}
	
	/**
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public Object get(Object key) {
		if (key == null) return null;

		if (this.key.equals(key)) {
			return value;
		} else if (next != null) {
			return next.get(key);
		} else {
			return null;			
		}
	}
	
	public boolean add(Object key, Object value) {
		if (key == null || key.equals(this.key)) return false;
		
		if (next == null) {
			next = new SimpleMap(key, value);
			return true;
		} else {
			return next.add(key, value);
		}
	}
	
	public void remove(Object key) {
		if (this.key == null || key == null) return;
		
		if (this.key.equals(key)) {
			if (next == null) {
				this.key = null;
				this.value = null;
			} else {
				this.key = next.key;
				this.value = next.value;
				this.next = next.next;
			}
		} else if (next != null) {
			if (next.key.equals(key)) {
				this.next = next.next;
			} else {
				next.remove(key);
			}
		}
	}
	
	public ValueIterator getIteratorOverValues() {
		return new ValueIterator(this);
	}
	
	public class ValueIterator {
		SimpleMap pos;
		
		private ValueIterator(SimpleMap start) {
			pos = start;
		}
		
		public Object next() {
			Object res=null;
			
			if (pos == null) {
				return null;
			} else {
				res = pos.value;
				pos = pos.next;
				return res;
			}
		}
		
		public boolean hasNext() {
			if (pos == null || pos.next == null) {
				return false;
			} else {
				return true;
			}
		}
	}
}
