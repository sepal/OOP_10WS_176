import java.util.Collection;

import java.util.Iterator;

public class Main extends Foobar {
	
	public Main() {
		super();
	}
	
	public static void main(String[] args) {
		Testy t = new Testy();
		Main m = new Main();
		Main n = new Main();
		
		Collection<Foobar> al = Foobar.hm.values();
		Iterator<Foobar> it = al.iterator();
		while (it.hasNext()) {
			it.next().foobar();
		}
	}

	@Override
	protected void foobar() {
		// TODO Auto-generated method stub
		System.out.println("MAIN");
	}
}
