
public abstract class USBDevice<T> {
	private Port<T> slot;
	
	/**
	 *(postcondition) creates new Port as USBDevice
	 */
	public USBDevice() {
		this.slot = new Port<T>();
	}
	
	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns port
	 */
	public Port<T> getSlot() {
		return slot;
	}
	
	/**
	 *(precondition) port must exist
	 *(postcondition) returns true, if slot was free, otherwise false
	 */
	public boolean eject() {
		return slot.eject();
	}
	
	/**
	 *(precondition) port must exist
	 *(postcondition) returns port as String
	 */
	public String getName() {
		return slot.toString();
	}
	
	/**
	 *(precondition) port must exist
	 *(postcondition) returns port as String
	 */
	public String toString() {
		return getName();
	}
	
	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns true, if slot was free, otherwise false
	 */
	public abstract boolean insert(DataMedium dm);
}
