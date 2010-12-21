import java.util.ArrayList;

public class Computer {
	private static final int NUM_DEVICES = 13;
	
	private String name;
	private NonRemovableStorage sda, sdb;
	private USBPort usb1, usb2;
	
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
		
		usb1 = new USBPort();
		usb2 = new USBPort();
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
	public USBPort  getUsb1() {
		return usb1;
	}

	/**
	 *(postcondition) returns USBPort
	 */
	public USBPort getUsb2() {
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
	 *(postcondition) returns Number of devices
	 */
	public static int getNumDevices() {
		return NUM_DEVICES;
	}

	/**
	 *(postcondition) returns CF! Reader
	 */
	public CardCF1Reader getCf1reader() {
		return cf1reader;
	}

	/**
	 *(postcondition) returns CF2 Reader
	 */
	public CardCF2Reader getCf2reader() {
		return cf2reader;
	}

	/**
	 *(postcondition) returns SD Reader
	 */
	public CardSDReader getSdreader() {
		return sdreader;
	}

	/**
	 *(postcondition) returns MiniSD Reader
	 */
	public CardMiniSDReader getMinisdreader() {
		return minisdreader;
	}

	/**
	 *(postcondition) returns MicroSD Reader
	 */
	public CardMicroSDReader getMicrosdreader() {
		return microsdreader;
	}

	/**
	 *(postcondition) returns MS Reader
	 */
	public CardMSReader getMsreader() {
		return msreader;
	}
	
	/**
	 *(postcondition) returns list of names of ports
	 */
	public String[] volumes() {
		ArrayList<String> names, cut;
		names = new ArrayList<String>();
		cut = new ArrayList<String>();

		cut.add("null");
		cut.add("");
		
		names.add(""+ sda);
		names.add(""+ sdb);
		names.add(""+ usb1);
		names.add(""+ usb2);
		
		names.add(""+ msreader);
		names.add(""+ sdreader);
		names.add(""+ minisdreader);
		names.add(""+ microsdreader);
		names.add(""+ cf1reader);
		names.add(""+ cf2reader);
		names.add(""+ bddrive);
		names.add(""+ cddrive);
		names.add(""+ dvddrive);
		
		names.removeAll(cut);
		
		return names.toArray(new String[1]);
	}
}
