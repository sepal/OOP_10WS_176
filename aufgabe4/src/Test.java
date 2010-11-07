


public class Test {

	public static void main(String[] args) {
		/* NOTE: An mein Team
		 * Bitte verwendet einfach die 2 code Strings hier. Der eine enthält (fast) Alle Dinge die man so umformatieren
		 * soll und der zweite ist aus der Angabe und gut zum vergleichen. Wenn euch noch was einfällt, was fehlt,
		 * kann man code1 anpassen oder auch einen zusätzlichen String einführen. Aber es sollte nicht zu viele werden
		 * Nachtrag: Hab code1 in code1 und code1p2 (part 2) aufgeteilt, weil in der Angabe steht:
		 * transform(x)+transform(y) = transform(x+y) und das wollte ich testen.
		 */
		
		// TODO: More tests with OO-features
		
		// Test Strings
		String code1 = "  public void main(int argc, char[] argv) {  int a=0, b=10, c=0;c=a+b; if (a == 0) {\t";
		String code1p2 = "//Foo      \n  call();} foobar();}";
		String code2 = "class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }";
		String code3 = "class A{function f1(){} function f2(){}}";
		
		// Testing BlockPretty
		System.out.println("*** Testing BlockPretty***");
		BlockPretty bp = new BlockPretty(1);
		System.out.println("Unformatted code3: "+code3+"\nBlockPretty Formatted:\n"+bp.transform(code3)+"\n");
		
		System.out.println("*** Testing BlockPretty with -1***");
		bp = new BlockPretty(-1);
		System.out.println("Unformatted code3: "+code3+"\nBlockPretty Formatted:\n"+bp.transform(code3)+"\n");
		
		// Testing NoPretty
		System.out.println("*** Testing NoPretty***");
		NoPretty np = new NoPretty();
		System.out.println("Unformatted code3: "+code3+"\nNoPretty Formatted:\n"+np.transform(code3)+"\n");
		
		// Testing LongPretty
		LongPretty lp = new LongPretty(4);
		System.out.println("*** Testing LongPretty ***");
		System.out.println("Unformatted code1: "+code1+"\nLongPretty Formatted:\n"+lp.transform(code1)+"\n");
		System.out.println("Unformatted code1 part 2: "+code1p2+"\nLongPretty Formatted:\n"+lp.transform(code1p2)+"\n");
		System.out.println("Unformatted code1 zusammen:\n"+code1+code1p2+"\nLongPretty Formatted:\n"+lp.transform(code1+code1p2)+"\n");
		System.out.println("Unformatted:\n"+code2+"\nLongPretty Formatted:\n"+lp.transform(code2)+"\n");
		System.out.println("Testing 2 code fragments with Reset() call inbetween:");
		System.out.println("Unformatted code1: "+code1+"\nLongPretty Formatted:\n"+lp.transform(code1)+"\n");
		lp.reset();
		System.out.println("Unformatted code1 part 2: "+code1p2+"\nLongPretty Formatted:\n"+lp.transform(code1p2)+"\n");
		
		// Testing LongPretty
		DepthPretty dp = new LongPretty(4);
		System.out.println("*** Testing LongPretty as DepthPretty ***");
		System.out.println("Unformatted code1: "+code1+"\nLongPretty Formatted:\n"+dp.transform(code1)+"\n");
		System.out.println("Unformatted code1 part 2: "+code1p2+"\nLongPretty Formatted:\n"+dp.transform(code1p2)+"\n");
		System.out.println("Unformatted:\n"+code1+code1p2+"\nLongPretty Formatted:\n"+dp.transform(code1+code1p2)+"\n");
		System.out.println("Unformatted:\n"+code2+"\nLongPretty Formatted:\n"+dp.transform(code2)+"\n");
		
		// Testing VarPretty
		VarPretty vp = new VarPretty(VarPretty.Mode.Long, 4);
		System.out.println("*** Testing VarPretty with LongPretty and depth 4 ***");
		System.out.println("Unformatted code2: "+code2+"\nLongPretty Formatted:\n"+vp.transform(code2)+"\n");
		System.out.println("*** Testing VarPretty with LongPretty and depth 2 ***");
		vp.setDepth(2);
		vp.reset();
		System.out.println("Unformatted code2: "+code2+"\nLongPretty Formatted:\n"+vp.transform(code2)+"\n");
		System.out.println("*** Testing VarPretty with ShortPretty and depth 4 ***");
		vp.reset();
		vp.setDepth(4);
		vp.setMode(VarPretty.Mode.Short);
		System.out.println("Unformatted code2: "+code2+"\nShortPretty Formatted:\n"+vp.transform(code2)+"\n");
		
		// Testing MorePretty
		MorePretty mp = new MorePretty(VarPretty.Mode.Long, 4);
		System.out.println("*** Testing MorePretty open ***");
		mp.transform(code1);
		System.out.println("Unformatted code1: "+code1+"\nOpen curly brackets: "+mp.open()+"\n");
		mp.transform(code2);
		System.out.println("Unformatted code1: "+code2+"\nOpen curly brackets: "+mp.open()+"\n");
	}

}
