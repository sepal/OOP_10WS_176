
public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean res = false;
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
	}

}
