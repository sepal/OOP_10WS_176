
public class OptDVDDrive extends USBDevice<OptDVD> {
	
	/**
	 *(postcondition) creates new opticalDrive for BD
	 */
	public OptDVDDrive() {
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
