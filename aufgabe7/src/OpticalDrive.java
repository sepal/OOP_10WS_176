
public abstract class OpticalDrive<T> implements USBDevice {
	Port<T> slot;
	
	/**
	 *(postcondition) creates opticalDrive with port (cd, dvd, bd) 
	 */
	public OpticalDrive() {
		slot = new Port<T>();
	}
	
	/**
	 *(postcondition) returns port with specific type
	 */
	public Port<T> getMediumSlot() {
		return this.slot;
	}
}
