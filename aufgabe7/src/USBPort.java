
public class USBPort extends USBDevice<USBDevice<?>> {

	public USBPort() {
		super();
	}
	
	@Override
	public boolean insert(DataMedium dm) {
		return false;
	}
	
	public boolean insert(USBDevice<?> usbdev) {
		return getSlot().insert(usbdev);
	}
}
