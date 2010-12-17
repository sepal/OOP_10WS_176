
public class OptCD extends OptDVD {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as opticalmedium 
	 */
	public OptCD(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean insertIntoOpticalDrive(OptCDDrive drive) {
		return drive.getSlot().insert(this);
	}
	
	@Override
	public boolean insertIntoOpticalDrive(OptDVDDrive drive) {
		return drive.getSlot().insert(this);
	}
	
	@Override
	public boolean insertIntoOpticalDrive(OptBDDrive drive) {
		return drive.getSlot().insert(this);
	}
}
