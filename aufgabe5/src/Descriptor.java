
public abstract class Descriptor implements Comparable<Descriptor> {
	String desc;
	
	public Descriptor(String desc) {
		this.desc = desc;
	}
	
	public String toString() {
		return desc;
	}
	
	public int compareTo(Descriptor d) {
		int len;
		char a, b;
		
		if (d.desc.length() < this.desc.length()) {
			len = d.desc.length();
		} else {
			len = this.desc.length();
		}
		
		for (int i=0; i < len; i++) {
			a = this.desc.charAt(i);
			b = d.desc.charAt(i);
			if (a < b) {
				return -1;
			} else if (a > b) {
				return 1;
			}
		}
		
		if (this.desc.length() > d.desc.length()) {
			return 1;
		} else if (this.desc.length() < d.desc.length()) {
			return -1;
		}
		
		return 0;
	}
}
