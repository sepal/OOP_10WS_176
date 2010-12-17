
public class ExternalDisc extends USBDevice<NonRemovableStorage> {
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
	
	public Port<NonRemovableStorage> getSlot() {
		return new DummyPort();
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
		return false;
	}

	@Override
	public boolean eject() {
		return false;
	}
	
	private class DummyPort extends Port<NonRemovableStorage> {
		@Override
		public boolean insert(NonRemovableStorage plug) {
			return false;
		}
		
		@Override
		public boolean eject() {
			return false;
		}
		
		@Override 
		public NonRemovableStorage getDev() {
			return disc;
		}
		
		@Override
		public String toString() {
			return ""+disc;
		}
	}
}
