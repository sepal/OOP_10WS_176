
public class MyInt implements Comparable<MyInt> {
	private int value;
	
	public MyInt(int i) {
		value = i;
	}
	
	@Override
	public int compareTo(MyInt mi) {
		if (this.value > mi.value) {
			return 1;
		} else if (this.value < mi.value) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public String toString() {
		return Integer.toString(value);
	}
}
