
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
		// Das geht zwar, lÃ¶scht aber die Typinformation Ã¼ber den zweiten Parameter, wodurch
		// man eine stufe tiefer erst recht wieder keinen Iterator bekommt x|
		AssocIter<MyInt, ?> subit = it.assoc();
		subit.insert(new MyInt(10));
		AssocIter<MyInt, ?> subsubit = subit.assoc();
		intTree.assoc().delete();
		intTree.allLabels();
		

		System.out.println("*** Testing Interator<MyInt>***");
		ValueTree<Descriptor, BDescriptor> descTree = new ValueTree<Descriptor, BDescriptor>();
		descTree.assoc().insert(new ADescriptor("lalalAla"));
		AssocIter<Descriptor, AssocIter<Descriptor, ?>> itDesc = descTree.assoc();
		itDesc.next();
		
		
		// Folgendes natürlich nicht möglich, da ja AssocIter kein set hat.
		// Casten darf ich auch nicht, also wie soll man dann set aufrufen????
		itDesc.set();
		
		

	}
}
