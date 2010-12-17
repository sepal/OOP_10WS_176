
public class OptBDDrive implements USBDevice {
	private Port<OptBD> slot;

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}

	@Override
	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	public Port<OptBD> getSlot() {
		return slot;
	}


}
