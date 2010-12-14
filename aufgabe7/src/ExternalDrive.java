
public class ExternalDrive implements USBDevice {
	private OpticalDrive<? extends OpticalMedium> discdrive;
	
	public ExternalDrive(OpticalDrive<? extends OpticalMedium> discdrive) {
		this.discdrive = discdrive;
	}

	public OpticalDrive<? extends OpticalMedium> getDiscdrive() {
		return discdrive;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return ""+discdrive;
	}
}
