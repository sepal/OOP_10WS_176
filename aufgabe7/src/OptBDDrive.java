
public class OptBDDrive implements USBDevice {
	private Port<OptBD> slot;

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}

	@Override
	public boolean eject() {
		return slot.eject();
	}

	public Port<OptBD> getSlot() {
		return slot;
	}


}
