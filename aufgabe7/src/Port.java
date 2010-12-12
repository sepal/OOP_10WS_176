public class Port<T> {
	private PortState state;
	private T dev;
	
	public Port() {
		state = new PortFree();
		dev = null;
	}
	
	public T getDev() {
		return dev;
	}
	
	public boolean insert(T plug) {
		return state.insert(plug);
	}
	
	public boolean eject() {
		return state.eject();
	}
	
	public String toString() {
		return state.toString();
	}
	
	private abstract class PortState {
		protected abstract boolean insert(T plug);
		protected abstract boolean eject();
	}
	
	private class PortFree extends PortState {

		@Override
		protected boolean insert(T plug) {
			dev = plug;
			state = new PortUsed();
			return true;
		}

		@Override
		protected boolean eject() { return false; }
		
		public String toString() {
			return "";
		}
	}
	
	private class PortUsed extends PortState {
		@Override
		protected boolean insert(T plug) { return false;	}

		@Override
		protected boolean eject() {
			dev = null;
			state = new PortFree();
			return true;
		}
		
		public String toString() {
			return dev.toString();
		}
	}
}
