
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
	
	/**
	 *(postcondition) returns new created DummyPort
	 */
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

	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns true, if slot was free, otherwise false
	 *(invariant) default method; can be overwritten
	 */
	@Override
	public boolean insert(DataMedium dm) {
		return false;
	}

	/**
	 *(precondition) datamedium must exist
	 *(postcondition) returns true, if slot was free, otherwise false
	 *(invariant) default method; can be overwritten
	 */
	@Override
	public boolean eject() {
		return false;
	}
	
	private class DummyPort extends Port<NonRemovableStorage> {
		
		/**
		 *(precondition) nonRemovableStorage must exist
		 *(postcondition) returns true, if slot was free, otherwise false
		 *(invariant) default method; can be overwritten
		 */
		@Override
		public boolean insert(NonRemovableStorage plug) {
			return false;
		}
		
		/**
		 *(precondition) datamedium must exist
		 *(postcondition) returns true, if slot was free, otherwise false
		 *(invariant) default method; can be overwritten
		 */
		@Override
		public boolean eject() {
			return false;
		}
		
		/**
		 *(precondition) nonRemovableStorage must exist
		 *(postcondition) returns nonRemovableStorage
		 */
		@Override 
		public NonRemovableStorage getDev() {
			return disc;
		}
		
		/**
		 *(precondition) nonRemovableStorage must exist
		 *(postcondition) returns nonRemovableStorage as String
		 */
		@Override
		public String toString() {
			return ""+disc;
		}
	}
}
