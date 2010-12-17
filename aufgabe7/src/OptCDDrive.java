
public class OptCDDrive implements USBDevice {
	private Port<OptCD> slot;

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}

	@Override
	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	public Port<OptCD> getSlot() {
		return slot;
	}
}
