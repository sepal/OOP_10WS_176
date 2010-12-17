
public class ExternalDisc implements USBDevice {
	private NonRemovableStorage disc;
	
	/**
	 *(postcondition) creates external disc
	 */
	public ExternalDisc(NonRemovableStorage disc) {
		this.disc = disc;
	}
	
	/**
	 *(precondition) disc must exist
	 *(postcondition) returns nonremovablestorage
	 */
	public NonRemovableStorage getDisc() {
		return disc;
	}
	/**
	 *(precondition) disc must exist
	 *(postcondition) returns nonremovablestorage as string
	 */
	public String getName() {
		return ""+disc;
	}
	/**
	 *(precondition) disc must exist
	 *(postcondition) returns nonremovablestorage as string
	 */
	public String toString() {
		return this.getName();
	}

	@Override
	public boolean insert(DataMedium dm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}
}
