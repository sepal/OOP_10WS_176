
public class OptDVDDrive extends USBDevice<OptDVD> {
	
	public OptDVDDrive() {
		super();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}
}
