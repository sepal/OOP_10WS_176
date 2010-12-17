
public class OptCDDrive extends USBDevice<OptCD> {
	
	public OptCDDrive() {
		super();
	}

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}
}
