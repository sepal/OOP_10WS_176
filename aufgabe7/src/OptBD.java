
public class OptBD extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as opticalDrive(datamedium)
	 */
	public OptBD(String name) {
		super(name);
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
