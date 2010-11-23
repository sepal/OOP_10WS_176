
public interface AssocIter<T extends Comparable<T>, A> extends Iter<T> {
	public A assoc();
	public boolean insert(T element);
	public boolean delete();
}
