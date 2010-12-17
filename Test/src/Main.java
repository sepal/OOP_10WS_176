public class Main {
	
	public static void main(String[] args) {
		A foo = new C();
		A bar = new B();
		
		shit(foo);
		shit(bar);

	}
	
	public static void shit(A a) {
		System.out.println("A");
		a.foobar();
	}
	
	public static void shit(B b) {
		System.out.println("B");
		b.foobar();
	}
	
	public static void shit(C c) {
		System.out.println("C");
		c.foobar();
	}
}
