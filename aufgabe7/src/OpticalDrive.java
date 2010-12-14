
public abstract class OpticalDrive<T extends OpticalMedium> {
	Port<T> slot;
	
	public OpticalDrive() {
		slot = new Port<T>();
	}
	
	public boolean insert(T disc) {
		return slot.insert(disc);
	}
	
	public boolean eject() {
		return slot.eject();
	}
}
