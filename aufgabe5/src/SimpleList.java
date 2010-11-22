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
	
	public int size() {
		if (next == null) {
			return pos;
		} else {
			return next.size();
		}
	}
}
