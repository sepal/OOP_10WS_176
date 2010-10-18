import java.util.ArrayList;

public class Main {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String[]> foo = new ArrayList<String[]>();
		
		String[] bar = new String[5];
		for (int i=0; i<5; i++) {
			bar[i] = "test"+i;
		}
		
		foo.add(bar);
		
		String[] blub = new String[1];
		blub[0] = "blub";
		foo.add(blub);
		
		System.out.println(foo.toString());
	}
	

}
