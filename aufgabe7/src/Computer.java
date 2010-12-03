
public class Computer {
	private String name;
	private NonRemovableStorage sda, sdb;
	private USBPort usb1, usb2;
	
	public Computer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] volumes() {
		return null;
	}

	public USBPort getUsb1() {
		return usb1;
	}

	public USBPort getUsb2() {
		return usb2;
	}
}
