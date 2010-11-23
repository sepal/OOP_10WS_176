
public class ValueTree<L extends Comparable<L>> extends Tree {
	
	public ValueTree() {
		super();
	}
	
	class ValueTreeIterator<T extends Comparable<T>, A> extends TreeIterator{
		private ValueTreeIterator(Tree<L> start, int startlabel) {
			super(start, startlabel);
		}
	}
}
