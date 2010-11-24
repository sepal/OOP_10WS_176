/**
 * Very basic LinkedList implementation
 *
 * @param <T> Type to be stored
 */
public class SimpleList<T> {
	int pos;
	T element;
	SimpleList<T> next;
	
	public SimpleList() {
		pos = 0;
		next = null;
		element = null;
	}
	
	private SimpleList(int i) {
		pos = i;
		next = null;
		element = null;
	}
	
	public T get(int i) {
		if (pos == i) {
			return element;
		} else if (i > pos) {
			 if (next != null) {
				 return next.get(i);
			 } else {
				 return null;
			 }
		} else {
			return null;
		}
	}
	
	public void add(T element) {
		if (next == null) {
			this.element = element;
			next = new SimpleList<T>(pos+1);
		} else {
			next.add(element);
		}
	}
	
	/**
	 * Inserts after element after pos
	 * 
	 * @param element
	 * @param pos Position after which will be inserted.
	 * @return true if added successfully
	 */
	public boolean insert(T element, int pos) {
		int size = this.size();
		
		if (pos >= size) {
			return false;
		} else if (pos == size-1) {
			this.add(element);
			return true;
		} else if (pos < 0 && this.pos == 0) {
			if (next == null) {
				this.add(element);
				return true;
			}
			SimpleList<T> new1 = new SimpleList<T>(1);
			new1.element = this.element;
			new1.next = this.next;
			this.element = element;
			this.next = new1;
			if (new1.next != null) new1.next.updatePos(1);
			
			return true;
		} else if (pos == this.pos) {
			SimpleList<T> new1 = new SimpleList<T>(pos+1);
			new1.element = element;
			new1.next = this.next;
			this.next = new1;
			if (new1.next != null) new1.next.updatePos(1);
			
			return true;
		} else {
			if (next != null) next.insert(element, pos);
		}
		
		return false;
	}
	
	public int size() {
		if (next == null) {
			return pos;
		} else {
			return next.size();
		}
	}
	
	public boolean delete(int pos) {
		if (pos < 0 || pos >= this.size()) {
			return false;
		}
		
		if (this.size() == 1 && pos == 0) {
			this.element = null;
			this.next = null;
			return true;
		}
		
		if (pos > this.pos+1) {
			if (next != null) {
				next.delete(pos);
			} else {
				return false;
			}
		} else if (pos == this.pos+1) {
			if (this.next != null) {
				this.next = this.next.next;
				if (this.next != null) this.next.updatePos(-1);
				return true;
			}
		}
		
		return false;
	}
	
	private void updatePos(int offset) {
		this.pos += offset;
		if (next != null) next.updatePos(offset);
	}
}
