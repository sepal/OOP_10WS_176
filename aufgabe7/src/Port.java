public class Port<T> {
	private PortState state;
	private T dev;
	
	/**
	 *(postcondition) creates port, state set "free"
	 */
	public Port() {
		state = new PortFree();
		dev = null;
	}
	
	/**
	 *(precondition) element must exist
	 *(postcondition) returns element
	 */
	public T getDev() {
		return dev;
	}
	
	/**
	 *(precondition) element must not be null
	 *(postcondition) returns true, if port was free, ohterwise false
	 */
	public boolean insert(T plug) {
		return state.insert(plug);
	}
	
	/**
	 *(postcondition) returns true, if port was used, otherwise false
	 */
	public boolean eject() {
		return state.eject();
	}
	
	/**
	 *(precondition) port must exist
	 *(postcondition) returns state as string
	 */
	public String toString() {
		return state.toString();
	}
	
	private abstract class PortState {
		protected abstract boolean insert(T plug);
		protected abstract boolean eject();
	}
	
	private class PortFree extends PortState {

		/**
		 *(precondition) element must not be null
		 *(postcondition) returns true because port was free, element set, state set "used"
		 */
		@Override
		protected boolean insert(T plug) {
			dev = plug;
			state = new PortUsed();
			return true;
		}

		/**
		 *(postcondition) returns false because state is free
		 */
		@Override
		protected boolean eject() { return false; }
		
		public String toString() {
			return "";
		}
	}
	
	private class PortUsed extends PortState {
		/**
		 *(precondition) element must not be null
		 *(postcondition) returns false because state is used
		 */
		@Override
		protected boolean insert(T plug) { return false;	}

		/**
		 *(postcondition) returns true because state was used, element set null, state set "free"
		 */
		@Override
		protected boolean eject() {
			dev = null;
			state = new PortFree();
			return true;
		}
		
		/**
		 *(precondition) element must exist
		 *(postcondition) returns element as string
		 */
		public String toString() {
			return dev.toString();
		}
	}
}
