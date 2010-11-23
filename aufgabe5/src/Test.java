
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree<Integer> t = new Tree<Integer>();
		//WTF?
		Tree<Integer>.TreeIterator a = new Tree<Integer>.TreeIterator(t, 0);
	}
}
