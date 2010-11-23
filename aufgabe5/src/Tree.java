
public class Tree<L extends Comparable<L>> {
	private Node root;
	
	public Tree() {
		root = new Node();
	}
	
	private class Node {
		SimpleList<Node> edges;
		SimpleList<L> labels;
		
		private Node() {
			edges = new SimpleList<Node>();
			labels = new SimpleList<L>();
		}
	}
}
