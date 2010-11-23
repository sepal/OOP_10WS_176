
public interface ValueIter<T extends Comparable<T>, A, V> extends AssocIter<T, A> {
	public void set(V value);
	public V get();
}
