
public abstract class OpticalDrive<T extends OpticalMedium> {
	Port<T> slot;
	
	/**
	 *(postcondition) creates opticalDrive with port (cd, dvd, bd) 
	 */
	public OpticalDrive() {
		slot = new Port<T>();
	}
	
	/**
	 *(precondition) element must not be null
	 *(postcondition) returns true, if state of port was free, otherwise false
	 */
	public boolean insert(T disc) {
		return slot.insert(disc);
	}
	
	/**
	 *(postcondition) returns true, if state of port was used, otherwise false
	 */
	public boolean eject() {
		return slot.eject();
	}
	
	/**
	 *(postcondition) returns port as string
	 */
	public String getName() {
		return ""+slot;
	}
	
	/**
	 *(postcondition) returns port as string
	 */
	public String toString() {
		return getName();
	}
}
