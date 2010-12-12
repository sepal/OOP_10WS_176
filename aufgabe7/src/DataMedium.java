
public abstract class DataMedium {
	private String name;
	
	public DataMedium(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
