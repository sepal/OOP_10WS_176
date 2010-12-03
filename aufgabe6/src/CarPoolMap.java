/*
 * Convenience class for SimpleMap, to avoid casts when accessing CarPools objects
 */
public class CarPoolMap extends SimpleMap {
	
	public CarPool get(String key) {
		Object tmp = super.get(key);
		if (tmp == null || !(tmp instanceof CarPool)) {
			return null;
		} else {
			return (CarPool) tmp;
		}
	}
	
	public boolean add(String key, CarPool value) {
		return super.add(key, value);
	}
	
	public void remove(String key) {
		super.remove(key);
	}
}
