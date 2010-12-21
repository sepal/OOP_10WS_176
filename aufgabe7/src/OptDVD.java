
public class OptDVD extends OptBD {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as opticalDrive(datamedium) 
	 */
	public OptDVD(String name) {
		super(name);
	}
	
	/**
	 *(precondition) opticalDrive must exist
	 *(postcondition) returns true, if slot was free (DVD inserted), otherwise false
	 */
	@Override
	public boolean insertIntoOpticalDrive(OptDVDDrive drive) {
		return drive.getSlot().insert(this);
	}
}
