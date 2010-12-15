public class USBPort {
	private PortState state;
	private USBDevice dev;
	
	/**
	 *(postcondition) creates usbPort, state set "free"
	 */
	public USBPort() {
		state = new PortFree();
		dev = null;
	}
	
	/**
	 *(precondition) device must exist
	 *(postcondition) returns device
	 */
	public USBDevice getDev() {
		return dev;
	}
	
	/**
	 *(precondition) device must not be null
	 *(postcondition) returns true, if port was free, otherwise false
	 */
	public boolean insert(USBDevice usb) {
		return state.insert(usb);
	}
	
	/**
	 *(postcondition) returns true, if port was used, otherwise false
	 */
	public boolean eject() {
		return state.eject();
	}
	
	private abstract class PortState {
		protected abstract boolean insert(USBDevice usb);
		protected abstract boolean eject();
	}
	
	private class PortFree extends PortState {

		/**
		 *(precondition) device must not be mull
		 *(postcondition) returns true because port was free, device set, state set "used"
		 */
		@Override
		protected boolean insert(USBDevice usb) {
			dev = usb;
			state = new PortUsed();
			return true;
		}

		/**
		 *(postcondition) returns false because state is free
		 */
		@Override
		protected boolean eject() { return false; }
	}
	
	private class PortUsed extends PortState {
		
		/**
		 *(precondition) device must not be null
		 *(postcondition) returns false because state is used
		 */
		@Override
		protected boolean insert(USBDevice usb) { return false;	}

		/**
		 *(postcondition) returns true because port was used, device set null, state set "free"
		 */
		@Override
		protected boolean eject() {
			dev = null;
			state = new PortFree();
			return true;
		}
	}
}
