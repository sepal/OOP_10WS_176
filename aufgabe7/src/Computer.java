
public class Computer {
	private static final int NUM_DEVICES = 8;
	
	private String name;
	private NonRemovableStorage sda, sdb;
	private Port<USBDevice> usb1, usb2;
	
	private Port<CardMS> msslot;
	private Port<CardSD> sdslot;
	private Port<CardMiniSD> minisdslot;
	private Port<CardMicroSD> microsdslot;
	
	private Port<OptBD> bddrive;
	private Port<OptDVD> dvddrive;
	private Port<OptCD> cddrive;
	
	
	/**
	 *(precondition) name and storages must not be null
	 *(postcondition) creates computer with empty ports
	 */
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

		bddrive = new Port<OptBD>();
		dvddrive = new Port<OptDVD>();
		cddrive = new Port<OptCD>();
	}
	
	/**
	 *(postcondition) returns name
	 */
	public String getName() {
		return name;
	}

	/**
	 *(postcondition) returns USBPort
	 */
	public Port<USBDevice>  getUsb1() {
		return usb1;
	}

	/**
	 *(postcondition) returns USBPort
	 */
	public Port<USBDevice>  getUsb2() {
		return usb2;
	}

	/**
	 *(postcondition) returns NonRemovableStorage
	 */
	public NonRemovableStorage getSda() {
		return sda;
	}

	/**
	 *(postcondition) returns NonRemovableStorage
	 */
	public NonRemovableStorage getSdb() {
		return sdb;
	}

	/**
	 *(postcondition) returns MSPort
	 */
	public Port<CardMS> getMsslot() {
		return msslot;
	}

	/**
	 *(postcondition) returns SDPort
	 */
	public Port<CardSD> getSdslot() {
		return sdslot;
	}

	/**
	 *(postcondition) returns MiniSDPort
	 */
	public Port<CardMiniSD> getMinisdslot() {
		return minisdslot;
	}

	/**
	 *(postcondition) returns MicroSDPort
	 */
	public Port<CardMicroSD> getMicrosdslot() {
		return microsdslot;
	}
	
	/**
	 *(postcondition) returns CDPort
	 */
	public Port<OptCD> getCDDrive(){
		return cddrive;
	}
	
	/**
	 *(postcondition) returns DVDPort
	 */
	public Port<OptDVD> getDVDDrive(){
		return dvddrive;
	}
	
	/**
	 *(postcondition) returns BDPort
	 */
	public Port<OptBD> getBDDrive(){
		return bddrive;
	}
	
	/**
	 *(postcondition) returns list of names of ports
	 */
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
