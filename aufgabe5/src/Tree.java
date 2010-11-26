public class Tree<L extends Comparable<L>> {
	private SimpleList<Tree<L>> edges;
	private SimpleList<L> labels;
	boolean delFlag = false;
	
	public Tree() {
		edges = new SimpleList<Tree<L>>();
		labels = new SimpleList<L>();
	}
	
	public AssocIter<L, AssocIter<L, ?>> assoc() {
		return new TreeIterator(this);
	}
	
	public void allLabels() {
		// kA WTF
	}
	
	class TreeIterator implements AssocIter<L, AssocIter<L, ?>> {
		Tree<L> state;
		int ilabel;
		
		TreeIterator(Tree<L> start) {
			state = start;
			ilabel = -1;
		}

		@Override
		public L next() {
			delFlag = false;
			ilabel++;
			
			int size = state.labels.size();
			if (size == 0 || ilabel >= size) {
				return null;
			}
			
			return state.labels.get(ilabel);
		}

		@Override
		public boolean hasNext() {
			if (ilabel >= 0 && ilabel < state.labels.size()) {
				return true;
			}
			
			return false;
		}

		@Override
		public boolean insert(L element) {
			int size = state.labels.size(), comp;
			int i;
			
			for (i=0; i < size; i++) {
				comp = state.labels.get(i).compareTo(element);
				if (comp >= 1) {
					i--;
					break;
				} else if (comp == 0) {
					return false;
				}
			}
			
			if (i == size) i--;
			return false;
		}

		@Override
		public boolean delete() {
			if (delFlag) return false;
			if (ilabel < 0 || ilabel >= state.labels.size()) return false;
			
			state.labels.delete(ilabel);
			state.edges.delete(ilabel);
			delFlag = true;
			
			return true;
		}

		@Override
		public AssocIter<L, AssocIter<L, ?>> assoc() {
			Tree<L> root = state.edges.get(ilabel);
			if (root == null) return null;
			
			return new TreeIterator(root);
		}
	}
}
