
public class OptCDDrive extends USBDevice<OptCD> {
	
	/**
	 *(postcondition) creates new opticalDrive for CD
	 */
	public OptCDDrive() {
		super();
	}

	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns true, if slot was free (datamedium inserted), otherwise false
	 */
	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}
}
