
public class ExternalDisc implements USBDevice {
	private NonRemovableStorage disc;
	
	public ExternalDisc(NonRemovableStorage disc) {
		this.disc = disc;
	}
	
	public NonRemovableStorage getDisc() {
		return disc;
	}

	public String getName() {
		return ""+disc;
	}
}
