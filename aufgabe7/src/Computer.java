
public class Computer {
	private static final int NUM_DEVICES = 13;
	
	private String name;
	private NonRemovableStorage sda, sdb;
	private Port<USBDevice> usb1, usb2;
	
	private CardCF1Reader cf1reader;
	private CardCF2Reader cf2reader;
	private CardSDReader sdreader;
	private CardMiniSDReader minisdreader;
	private CardMicroSDReader microsdreader;
	private CardMSReader msreader;
	
	private OptBDDrive bddrive;
	private OptDVDDrive dvddrive;
	private OptCDDrive cddrive;
	
	/**
	 *(precondition) name and storages must not be null
	 *(postcondition) creates computer with empty ports
	 */
	public Computer(String name, NonRemovableStorage sda, NonRemovableStorage sdb) {
		this.name = name;
		
		this.sda = sda;
		this.sdb = sdb;

		cf1reader = new CardCF1Reader();
		cf2reader = new CardCF2Reader();
		sdreader = new CardSDReader();
		minisdreader = new CardMiniSDReader();
		microsdreader = new CardMicroSDReader();
		msreader = new CardMSReader();
		
		bddrive = new OptBDDrive();
		dvddrive = new OptDVDDrive();
		cddrive = new OptCDDrive();
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
	 *(postcondition) returns CDDrive
	 */
	public OptCDDrive getCDDrive(){
		return cddrive;
	}
	
	/**
	 *(postcondition) returns DVDDrive
	 */
	public OptDVDDrive getDVDDrive(){
		return dvddrive;
	}
	
	/**
	 *(postcondition) returns BDDrive
	 */
	public OptBDDrive getBDDrive(){
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
		names[4] += msreader.getSlot().getDev().getName();
		names[5] += sdreader.getSlot().getDev().getName();
		names[6] += minisdslot;
		names[7] += microsdslot;
		
		return names;
	}

	public static int getNumDevices() {
		return NUM_DEVICES;
	}

	public CardCF1Reader getCf1reader() {
		return cf1reader;
	}

	public CardCF2Reader getCf2reader() {
		return cf2reader;
	}

	public CardSDReader getSdreader() {
		return sdreader;
	}

	public CardMiniSDReader getMinisdreader() {
		return minisdreader;
	}

	public CardMicroSDReader getMicrosdreader() {
		return microsdreader;
	}

	public CardMSReader getMsreader() {
		return msreader;
	}

	public OptBDDrive getBddrive() {
		return bddrive;
	}

	public OptDVDDrive getDvddrive() {
		return dvddrive;
	}

	public OptCDDrive getCddrive() {
		return cddrive;
	}
}
