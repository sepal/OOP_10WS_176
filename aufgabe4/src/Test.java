
public class Test {

	public static void main(String[] args) {
		LongPretty lp = new LongPretty(4);
		String code = "  public void main(int argc, char[] argv) {  int a=0, b=10, c=0;c=a+b; " +
				"//Foo      \n  call();}";
		System.out.println("Unformatted:\n"+code+"\nLongPretty Formatted:\n"+lp.transform(code)+"\n");
		code = "class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }";
		System.out.println("Unformatted:\n"+code+"\nLongPretty Formatted:\n"+lp.transform(code)+"\n");
	}

}
