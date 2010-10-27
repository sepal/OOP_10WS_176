import java.io.Serializable;
import java.util.Collection;

import java.util.Iterator;

public class Main extends Foobar {
	
	public Main() {
		super();
	}
	
	public static void main(String[] args) {
		Testy t = new Testy();
		if (t instanceof Foobar) {
			System.out.println("yes");
		}
	}

	@Override
	protected void foobar() {
		// TODO Auto-generated method stub
		System.out.println("MAIN");
	}
}
