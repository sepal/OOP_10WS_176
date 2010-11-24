
public class ValueTree<L extends Comparable<L>, V> extends Tree<L> {
	private V value;

	public ValueTree() {
		super();
		value = null;
	}
	
	@Override
	public ValueIter<L, ValueIter<L, ?, V>, V> assoc() {
		return new ValueTreeIterator(this);
	}
	
	private class ValueTreeIterator extends Tree<L>.TreeIterator implements ValueIter<L, ValueIter<L, ?, V>, V> {
		private ValueTree<L, V> state;
		
		ValueTreeIterator(ValueTree<L, V> state) {
			super(state);
			this.state = state;
		}

		@Override
		public V get() {
			return state.value;
		}

		@Override
		public void set(V value) {
			state.value = value;
		}
		
		public ValueIter<L, ValueIter<L, ?, V>, V> assoc() {
			
		}
		
	}
	
}
