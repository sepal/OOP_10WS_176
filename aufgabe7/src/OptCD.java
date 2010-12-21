
public class OptCD extends OptDVD {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as opticalDrive(datamedium) 
	 */
	public OptCD(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *(precondition) opticalDrive must exist
	 *(postcondition) returns true, if slot was free (CD inserted), otherwise false
	 */
	@Override
	public boolean insertIntoOpticalDrive(OptCDDrive drive) {
		return drive.getSlot().insert(this);
	}
	
	/**
	 *(precondition) opticalDrive must exist
	 *(postcondition) returns true, if slot was free (DVD inserted), otherwise false
	 */
	@Override
	public boolean insertIntoOpticalDrive(OptDVDDrive drive) {
		return drive.getSlot().insert(this);
	}
	
	/**
	 *(precondition) opticalDrive must exist
	 *(postcondition) returns true, if slot was free (BD inserted), otherwise false
	 */
	@Override
	public boolean insertIntoOpticalDrive(OptBDDrive drive) {
		return drive.getSlot().insert(this);
	}
}
