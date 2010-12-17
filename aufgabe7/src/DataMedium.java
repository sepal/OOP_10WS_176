
public abstract class DataMedium {
	private String name;
	
	/**
	 *(precondition) name must not be null
	 *(postcondition) creates new datamedium
	 */
	public DataMedium(String name) {
		this.name = name;
	}
	
	public abstract boolean insertIntoCardReader(CardCF1Reader cf1r);
	public abstract boolean insertIntoCardReader(CardCF2Reader cf2r);
	public abstract boolean insertIntoCardReader(CardMSReader msr);

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
