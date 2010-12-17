
public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean res = false;
		
		Computer pc1 = new Computer("Dell Optiplex", new HDD("Maxtor 350GB HDD"), new SSD("Corsair X128 SSD"));
		System.out.println("Created new Computer: "+pc1.getName()+"\n");
		
		USBDevice usbdev = new CardCF1Reader();
		res = usbdev.insert(new CardCF2("CF2"));
		System.out.println(""+res+" "+usbdev.toString());
		
		CardCF2 cf2 = new CardCF1("CF1cf2");
		USBDevice usbdev2 = new CardCF1Reader();
		res = usbdev2.insert(cf2);
		System.out.println(""+res+" "+usbdev2.toString());
		
		USBDevice usbdev3 = new CardMSReader();
		res = usbdev3.insert(new CardCF1("CF1"));
		System.out.println(""+res+" "+usbdev3.toString());
		
		USBDevice usbdev4 = new CardSDReader();
		res = usbdev4.insert(new CardSD("SD1"));
		System.out.println(""+res+" "+usbdev4.toString());
	}

}
