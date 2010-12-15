
public abstract class DataMedium {
	private String name;
	
	/**
	 *(precondition) name must not be null
	 *(postcondition) creates new datamedium
	 */
	public DataMedium(String name) {
		this.name = name;
	}
	
	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns name of datamedium
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns name as string 
	 */
	public String toString() {
		return name;
	}
}
