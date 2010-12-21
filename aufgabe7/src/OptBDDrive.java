
public class OptBDDrive extends USBDevice<OptBD> {
	
	/**
	 *(postcondition) creates new opticalDrive for BD
	 */
	public OptBDDrive() {
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
