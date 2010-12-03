
public class USBPort {
	private PortState state;
	private USBDevice dev;
	
	public USBPort() {
		state = new PortFree();
		dev = null;
	}
	
	public boolean insert(USBDevice usb) {
		return state.insert(usb);
	}
	
	public boolean eject() {
		return state.eject();
	}
	
	private abstract class PortState {
		protected abstract boolean insert(USBDevice usb);
		protected abstract boolean eject();
	}
	
	private class PortFree extends PortState {

		@Override
		protected boolean insert(USBDevice usb) {
			dev = usb;
			state = new PortUsed();
			return true;
		}

		@Override
		protected boolean eject() { return false; }
	}
	
	private class PortUsed extends PortState {
		@Override
		protected boolean insert(USBDevice usb) { return false;	}

		@Override
		protected boolean eject() {
			dev = null;
			state = new PortFree();
			return true;
		}
	}
}
