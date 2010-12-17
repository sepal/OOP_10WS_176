
public abstract class DataMedium {
	private String name;
	
	/**
	 *(precondition) name must not be null
	 *(postcondition) creates new datamedium
	 */
	public DataMedium(String name) {
		this.name = name;
	}
	
	public boolean insertIntoCardReader(CardCF1Reader cf1r) {
		return false;
	}
	public boolean insertIntoCardReader(CardCF2Reader cf2r) {
		return false;
	}
	public boolean insertIntoCardReader(CardMSReader msr) {
		return false;
	}
	public boolean insertIntoCardReader(CardSDReader sdr) {
		return false;
	}
	public boolean insertIntoCardReader(CardReader cardReader) {
		return false;
	}
	public boolean insertIntoOpticalDrive(OptCDDrive drive) {
		return false;
	}
	public boolean insertIntoOpticalDrive(OptDVDDrive drive) {
		return false;
	}
	public boolean insertIntoOpticalDrive(OptBDDrive drive) {
		return false;
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
