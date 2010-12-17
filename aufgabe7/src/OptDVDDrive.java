
public class OptDVDDrive  implements USBDevice {
	private Port<OptDVD> slot;

	@Override
	public boolean insert(DataMedium dm) {
		return dm.insertIntoOpticalDrive(this);
	}

	@Override
	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	public Port<OptDVD> getSlot() {
		return slot;
	}
}
