
public abstract class OpticalDrive<T> implements USBDevice {
	Port<T> slot;
	
	public OpticalDrive() {
		slot = new Port<T>();
	}
	
	public Port<T> getMediumSlot() {
		return this.slot;
	}
}
