
public class OptCDDrive implements USBDevice {
	private Port<OptCD> slot;

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}

	public Port<OptCD> getSlot() {
		return slot;
	}
}
