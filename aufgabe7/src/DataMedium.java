
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

	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (cf1 card inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoCardReader(CardCF1Reader cf1r) {
		return false;
	}
	
	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (cf2 inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoCardReader(CardCF2Reader cf2r) {
		return false;
	}
	
	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (MS inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoCardReader(CardMSReader msr) {
		return false;
	}
	
	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (sd card inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoCardReader(CardSDReader sdr) {
		return false;
	}
	
	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (minisd inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoCardReader(CardMiniSDReader minisd) {
		return false;
	}
	
	/**
	 *(precondition) cardreader must exist
	 *(postcondition) returns true, if slot was free (microsd inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoCardReader(CardMicroSDReader microsd) {
		return false;
	}
	
	/**
	 *(precondition) opticalDrive must exist
	 *(postcondition) returns true, if slot was free (CD inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoOpticalDrive(OptCDDrive drive) {
		return false;
	}
	
	/**
	 *(precondition) opticalDrive must exist
	 *(postcondition) returns true, if slot was free (DVD inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoOpticalDrive(OptDVDDrive drive) {
		return false;
	}
	
	/**
	 *(precondition) opticalDrive must exist
	 *(postcondition) returns true, if slot was free (BD inserted), otherwise false
	 *(invariant) default method; can be overwritten
	 */
	public boolean insertIntoOpticalDrive(OptBDDrive drive) {
		return false;
	}
}
