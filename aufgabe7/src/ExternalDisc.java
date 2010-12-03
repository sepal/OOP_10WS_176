
public class ExternalDisc extends USBDevice {
	private NonRemovableStorage disc;
	
	public ExternalDisc(NonRemovableStorage disc) {
		this.disc = disc;
	}
	
	public NonRemovableStorage getDisc() {
		return disc;
	}
}
