
	/**
	 * A simple Map implemented as linked list, storing key - value pairs.
	 * Keys must not be null and have to be unique.
	 */
public class SimpleMap {
	private Object key, value;
	private SimpleMapElement start;
	private SimpleMapElement last;
	private int size;
	
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
	 * post-condition: A new SimpleMap object containing key and value has been created or the program
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
		start = null;
	}
	
	/**
	 * toString() method for car objects
	 */
	public String toString() {
		String cons = "";
		String s = "CarList:\n";
		ValueIterator vi = this.getIteratorOverValues();
		while(vi.hasNext()) {
			Car c = (Car) vi.next().getValue();
			if(c.getClass().getName().equals("ElectricCar")) cons = "kW used";
			else if(c.getClass().getName().equals("FuelCar")) cons = "L used";
			
			s += c.getClass().getName()+" "+c.getId()+", "+c.getMileage()+ " miles, "+c.getConsumption()+" "+cons+"\n";
		}
		return s;
	}
	
	/**
	 * @param key
	 * @return object
	 */
	public Object get(Object key) {
		if (key == null) return null;
		
		ValueIterator vi = this.getIteratorOverValues();
		while(vi.hasNext()) {
			SimpleMapElement sm = vi.next();
			if(key.equals(sm.getKey())) {
				return sm.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param key, value
	 * @return true if added, otherwise false
	 */
	public boolean add(Object key, Object value) {
		if (key == null) return false;
		
		if (start == null) {
			start = new SimpleMapElement(key, value);
			start.setPrev(null);
			start.setNext(null);
			last = start;
		}
		else {
			SimpleMapElement cur = new SimpleMapElement(key, value);
			last.setNext(cur);
			cur.setPrev(last);
			cur.setNext(null);
			last = cur;
		}
		size++;
		return true;
	}
	
	/**
	 * iterator iterates throw objects, if found creates connection between objects before and after
	 */
	public void remove(Object key) {
		if (key == null) return;
		
		ValueIterator vi = this.getIteratorOverValues();
		while(vi.hasNext()) {
			SimpleMapElement sm = vi.next();
			if(key.equals(sm.getKey())) {
				if(sm.getPrev() == null) {//actual element is startelement
					if(sm.getNext() != null) {
						sm.getNext().setPrev(null);
						start = sm.getNext();
					} else {
						start = null;
					}
				} else if(sm.getNext() == null) { //actual element is last element
					sm.getPrev().setNext(null);
					last = sm.getPrev();
				} else {
					sm.getPrev().setNext(sm.getNext());
					sm.getNext().setPrev(sm.getPrev());
				}
				size--;	
			}
		}		
	}
	
	public int size() {
		return size;
	}
	
	public ValueIterator getIteratorOverValues() {
		return new ValueIterator(start);
	}
	
	public class ValueIterator {
		SimpleMapElement pos;
		boolean ok = true;
		
		private ValueIterator(SimpleMapElement start) {
			pos = start;
		}
		
		public SimpleMapElement next() {
			SimpleMapElement next =null;
			
			if (pos == null) {
				return null;
			} else {
				if(pos == start && ok) {
					ok = false;
					return start;
				}
				next = pos.getNext();
				pos = next;
				return next;
			}
		}
		
		public boolean hasNext() {
			if (pos == null) {
				return false;
			} else {
				if(pos == start) {
					return true;
				}
				if(pos.getNext() == null) {
					return false;
				} else {
					return true;
				}
			}
		}
	}
}