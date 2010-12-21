
public class USBPort extends USBDevice<USBDevice<?>> {

	/**
	 *(postcondition) creates new USBPort as USBDevice
	 */
	public USBPort() {
		super();
	}
	
	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns true, if slot was free (datamedium inserted), otherwise false
	 *(invariant) default method
	 */
	@Override
	public boolean insert(DataMedium dm) {
		return false;
	}
	
	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns true, if slot was free (USBDevice inserted), otherwise false
	 */
	public <V> boolean insert(USBDevice<V> usbdev) {
		return getSlot().insert(usbdev);
	}
}
