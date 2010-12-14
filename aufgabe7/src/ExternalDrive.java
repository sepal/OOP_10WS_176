
public class ExternalDrive implements USBDevice {
	private OpticalDrive discdrive;
	
	public ExternalDrive(OpticalDrive discdrive) {
		this.discdrive = discdrive;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
