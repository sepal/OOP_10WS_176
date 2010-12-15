
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
}
