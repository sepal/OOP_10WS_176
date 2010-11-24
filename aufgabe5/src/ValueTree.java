

public class ValueTree<L extends Comparable<L>, V> extends Tree {
	V value;
	

	public AssocIter<L, AssocIter<L, ?>> assoc() {
		return new ValueIter(this);
	}
	
	protected class ValueIter implements AssocIter<L, AssocIter<L, ?>> {
		
		private ValueIter(Tree<L> start) {

		}
		
		@Override
		public L next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public AssocIter<L, ?> assoc() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean insert(L element) {
			return false;
		}

		@Override
		public boolean delete() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}
