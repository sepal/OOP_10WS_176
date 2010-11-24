
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("*** Testing Interator<MyInt>***");
		Tree<MyInt> intTree = new Tree<MyInt>();
		intTree.assoc().insert(new MyInt(1));
		intTree.assoc().insert(new MyInt(3));
		intTree.assoc().insert(new MyInt(2));
		AssocIter<MyInt, AssocIter<MyInt, ?>> it = intTree.assoc();
		it.next();
		// TODO: checken wieso des net funzt.
		AssocIter<MyInt, AssocIter<MyInt, ?>> subit = it.assoc();
		subit.insert(new MyInt(10));
		intTree.assoc().delete();
		intTree.allLabels();
		

		System.out.println("*** Testing Interator<MyInt>***");
		ValueTree<Descriptor, BDescriptor> descTree = new ValueTree<Descriptor, BDescriptor>();
		descTree.assoc().insert(new ADescriptor("lalalAla"), new BDescriptor("blaBlablabla"));
		

	}
}
