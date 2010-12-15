
public class Computer {
	private static final int NUM_DEVICES = 8;
	
	private String name;
	private NonRemovableStorage sda, sdb;
	private Port<USBDevice> usb1, usb2;
	
	private Port<CardMS> msslot;
	private Port<CardSD> sdslot;
	private Port<CardMiniSD> minisdslot;
	private Port<CardMicroSD> microsdslot;
	
	private OptBDDrive bddrive;
	private OptDVDDrive dvddrive;
	private OptCDDrive cddrive;
	
	public Computer(String name, NonRemovableStorage sda, NonRemovableStorage sdb) {
		this.name = name;
		
		this.sda = sda;
		this.sdb = sdb;
		
		usb1 = new Port<USBDevice>();
		usb2 = new Port<USBDevice>();
		msslot = new Port<CardMS>();
		sdslot = new Port<CardSD>();
		minisdslot = new Port<CardMiniSD>();
		microsdslot = new Port<CardMicroSD>();

		bddrive = new OptBDDrive();
		dvddrive = new OptDVDDrive();
		cddrive = new OptCDDrive();
	}
	
	public String getName() {
		return name;
	}

	public Port<USBDevice>  getUsb1() {
		return usb1;
	}

	public Port<USBDevice>  getUsb2() {
		return usb2;
	}

	public NonRemovableStorage getSda() {
		return sda;
	}

	public NonRemovableStorage getSdb() {
		return sdb;
	}

	public Port<CardMS> getMsslot() {
		return msslot;
	}

	public Port<CardSD> getSdslot() {
		return sdslot;
	}

	public Port<CardMiniSD> getMinisdslot() {
		return minisdslot;
	}

	public Port<CardMicroSD> getMicrosdslot() {
		return microsdslot;
	}
	
	public OptCDDrive getCDDrive(){
		return cddrive;
	}
	
	public OptDVDDrive getDVDDrive(){
		return dvddrive;
	}
	
	public OptBDDrive getBDDrive(){
		return bddrive;
	}
	
	public String[] volumes() {
		String[] names = new String[NUM_DEVICES];
		for (int i=0; i < NUM_DEVICES; i++) {
			names[i] = "";
		}
		names[0] += sda;
		names[1] += sdb;
		names[2] += usb1;
		names[3] += usb2;
		names[4] += msslot;
		names[5] += sdslot;
		names[6] += minisdslot;
		names[7] += microsdslot;
		
		return names;
	}
}
