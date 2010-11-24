/**
 * Anmerkung: "Die Aufgabe ist so konstruiert, dass dabei einige Schwierigkeiten
 * auftauchen, für die wir Lösungsmöglichkeiten kennengelernt haben." steht in
 * der Angabe, leider müssen wir dem widersprechen. Keine der uns bekannten
 * Lösungsmöglichkeiten konnten zB. die Probleme mit "rekursiven" Generics
 * (AssocIter<L, AsssocIter<L, Ass....>>) lösen.
 * 
 * Leider konnten wir in dem geringen Zeitrahmen die komplexe Aufgabenstellung
 * verstehen und auch eine Lösung für diese Probleme finden.
 * 
 * @author GroupWS10176
 * 
 */

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

		// Das geht zwar, löscht aber die Typinformation über den zweiten
		// Parameter, wodurch
		// man eine stufe tiefer erst recht wieder keinen Iterator bekommt x|
		AssocIter<MyInt, ?> subit = it.assoc();
		subit.insert(new MyInt(10));
		AssocIter<MyInt, ?> subsubit = subit.assoc();
		intTree.assoc().delete();
		intTree.allLabels();

		System.out.println("*** Testing Interator<MyInt>***");
		ValueTree<Descriptor, BDescriptor> descTree = new ValueTree<Descriptor, BDescriptor>();
		descTree.assoc().insert(new ADescriptor("lalalAla"));
		ValueIter<Descriptor, ValueIter<Descriptor, ?, BDescriptor>, BDescriptor> itDesc = descTree.assoc();
		itDesc.next();
		itDesc.set(new BDescriptor("test"));
	}
}
