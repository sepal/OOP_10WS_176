
public interface ValueIter<T extends Comparable<T>, A, V> extends AssocIter<T, A> {
	public V get();
	public void set(V value);
}
