
public class OptDVD extends OptBD {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as opticalmedium 
	 */
	public OptDVD(String name) {
		super(name);
	}
	
	@Override
	public boolean insertIntoOpticalDrive(OptDVDDrive drive) {
		return drive.getSlot().insert(this);
	}
}
