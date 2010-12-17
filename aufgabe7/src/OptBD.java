
public class OptBD extends DataMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as opticalmedium 
	 */
	public OptBD(String name) {
		super(name);
	}
	
	@Override
	public boolean insertIntoOpticalDrive(OptBDDrive drive) {
		return drive.getSlot().insert(this);
	}
}
