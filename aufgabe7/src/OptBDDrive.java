
public class OptBDDrive extends USBDevice<OptBD> {
	
	public OptBDDrive() {
		super();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}
}
