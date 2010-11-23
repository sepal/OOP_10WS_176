
public abstract class Descriptor implements Comparable<Descriptor> {
	String desc;
	
	public Descriptor(String desc) {
		this.desc = desc;
	}
	
	public String toString() {
		return desc;
	}
	
	public int compareTo(Descriptor d) {
		// TODO: ausprogrammieren
		if (d.desc.length() > this.desc.length()) {
			return 1;
		} else {
			for (int i=0; i<desc.length(); i++) {
				
			}
		}
		return 0;
	}
}
