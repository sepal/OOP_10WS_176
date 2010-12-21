import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(0);
		al.add(1);
		al.remove(new Integer(0));
		System.out.println(al.size());
	}

}
