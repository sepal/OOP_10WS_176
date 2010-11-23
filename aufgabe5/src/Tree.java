
public class Tree<L extends Comparable<L>> {
	SimpleList<Tree<L>> edges;
	SimpleList<L> labels;
	
	public Tree() {
		edges = new SimpleList<Tree<L>>();
		labels = new SimpleList<L>();
	}
	
	public AssocIter<L, AssocIter<L, ?>> assoc() {
		
		return null;
	}
	
	public void allLabels() {
		// kA WTF
	}
	
	private class TreeIterator implements AssocIter<L, AssocIter<L, ?>> {
		Tree<L> state;
		int ilabel;
		
		private TreeIterator(Tree<L> start, int startlabel) {
			state = start;
			ilabel = startlabel;
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
		public boolean insert(L element) {
			for (int i = 0; i<labels.size(); i++) {
				L label = labels.get(i);
				if (label == element){
					return true;
				}
			}
			labels.add(element);
			return false;
		}

		@Override
		public boolean delete() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public AssocIter<L, ?> assoc() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
