
public class Test {	
	public static void main(String[] args) {
		boolean res;
		Computer pc1 = new Computer("Dell Optiplex", new HDD("Maxtor 350GB HDD"), new SSD("Corsair X128 SSD"));
		pc1.getUsb1().insert(new CardReader());
		pc1.getUsb2().insert(new ExternalDisc(new HDD("Plextor HDD")));
		
		res = pc1.getSdslot().insert(new CardMicroSD("MicroSD1"));
		System.out.println("Inserted: " + pc1.getSdslot().getDev());
		System.out.println(res);
		
		res = pc1.getSdslot().insert(new CardMicroSD("MicroSD1"));
		System.out.println("Inserted: " + pc1.getSdslot().getDev());
		System.out.println(res);

		System.out.println("Ejecting: " + pc1.getSdslot().getDev());
		res = pc1.getSdslot().eject();
		System.out.println(res);
		
		System.out.println("Ejecting: " + pc1.getSdslot().getDev());
		res = pc1.getSdslot().eject();
		System.out.println(res);

		
		res = pc1.getDVDDrive().insert(new OptDVD("DVD1"));
		System.out.println("Inserted: " + pc1.getDVDDrive().getDev());
		System.out.println(res);
		res = pc1.getDVDDrive().eject();
		
		res = pc1.getDVDDrive().insert(new OptCD("CD1"));
		System.out.println("Inserted: " + pc1.getDVDDrive().getDev());
		System.out.println(res);
		res = pc1.getDVDDrive().eject();
		
		res = pc1.getBDDrive().insert(new OptBD("BD"));
		System.out.println("Inserted: " + pc1.getBDDrive().getDev());
		System.out.println(res);
		res = pc1.getBDDrive().eject();
	}
}
