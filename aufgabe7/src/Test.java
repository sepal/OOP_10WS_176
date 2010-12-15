
public class Test {		
	public static void main(String[] args) {
		boolean res;
		System.out.println("*** Testing PC ***");
		System.out.println("Creating PC:");
		Computer pc1 = new Computer("Dell Optiplex", new HDD("Maxtor 350GB HDD"), new SSD("Corsair X128 SSD"));
		System.out.println("  " + pc1.getName());
		
		
		System.out.println("Hardrives:");
		System.out.println("  " + pc1.getSda());
		System.out.println("  " + pc1.getSdb());
		
		
		res = pc1.getSdslot().insert(new CardMicroSD("MicroSD1"));
		System.out.println("Inserted: " + pc1.getSdslot().getDev());
		System.out.println("  " + res);

		System.out.println("Inserting new SD Card");
		res = pc1.getSdslot().insert(new CardMicroSD("MicroSD1"));
		System.out.println("  " + res);

		System.out.println("Ejecting: " + pc1.getSdslot().getDev());
		res = pc1.getSdslot().eject();
		System.out.println("  " + res);
		
		System.out.println("Ejecting: " + pc1.getSdslot().getDev());
		res = pc1.getSdslot().eject();
		System.out.println("  " + res);

		
		res = pc1.getDVDDrive().insert(new OptDVD("DVD1"));
		System.out.println("Inserted: " + pc1.getDVDDrive().getDev() + " into DVDDrive");
		System.out.println("  " + res);
		
		System.out.println("Ejecting: " + pc1.getDVDDrive().getDev());
		res = pc1.getDVDDrive().eject();
		System.out.println("  " + res);
		
		res = pc1.getDVDDrive().insert(new OptCD("CD1"));
		System.out.println("Inserted: " + pc1.getDVDDrive().getDev() + " into DVDDrive");
		System.out.println("  " + res);
		
		System.out.println("Ejecting: " + pc1.getDVDDrive().getDev());
		res = pc1.getDVDDrive().eject();
		System.out.println("  " + res);
		
		res = pc1.getBDDrive().insert(new OptBD("BD"));
		System.out.println("Inserted: " + pc1.getBDDrive().getDev() + " into BDDrive");
		System.out.println("  " + res);

		
		
		System.out.println("\n*** Testing USB ***");
		
		System.out.println("Inserting Cardreader into usb1");
		pc1.getUsb1().insert(new CardReader());
		System.out.println("  " + res);
		
		CardReader cr = (CardReader) pc1.getUsb1().getDev();
		res = cr.getCf2slot().insert(new CardCF1("CF1"));
		System.out.println("Inserted: " + cr.getCf2slot().getDev());
		System.out.println("  " + res);
		
		System.out.println("Ejecting: card reader");
		res = pc1.getUsb1().eject();
		System.out.println("  " + res);

		System.out.println("Inserting CD Drive:");
		pc1.getUsb1().insert(new OptCDDrive());
		System.out.println("  " + res);
		
		OptCDDrive cdd = (OptCDDrive) pc1.getUsb1().getDev();
		res = cdd.getMediumSlot().insert(new OptCD("CD1"));
		System.out.println("Inserted: " + cdd.getMediumSlot().getDev());
		System.out.println("  " + res);

		System.out.println("Trying to insert new CD");
		res = cdd.getMediumSlot().insert(new OptCD("CD1"));
		System.out.println("  " + res);
		

		System.out.println("Inserting Cardreader into usb2");
		res = pc1.getUsb2().insert(new ExternalDisc(new HDD("Plextor HDD")));
		System.out.println("  " + res);
		System.out.println("Inserting Cardreader into usb2");
		res = pc1.getUsb2().insert(new ExternalDisc(new HDD("Plextor SSD")));
		System.out.println("  " + res);
	}
}
