
public class Test {		
	public static void main(String[] args) {
		boolean res;		
		Computer pc = new Computer("PC1", new HDD("HDD1"), new SSD("SSD1"));
		
		System.out.println(pc.getName());
		System.out.println("Hard Drives: "+pc.getSda()+", "+pc.getSdb()+"\n");
		
		System.out.println("Used data media on "+pc.getName()+":");
		for(String s : pc.volumes()) {
			System.out.println("- "+s);
		}
		
		CardSDReader sdr = pc.getSdreader();
		res = sdr.insert(new CardSD("SD1"));
		System.out.println("\nInserted "+sdr.getName()+" into SDReader: "+res);
		
		System.out.println("\nUsed data media on "+pc.getName()+":");
		for(String s : pc.volumes()) {
			System.out.println("- "+s);
		}
		
		CardCF2Reader cf2 = pc.getCf2reader();
		res = cf2.insert(new CardCF2("CF2"));
		System.out.println("\nInserted "+cf2.getName()+" into CF2Reader: "+res);
		
		res = cf2.insert(new CardCF1("CF2.1"));
		System.out.println("Inserted "+cf2.getName()+" into CF2Reader: "+res);
		
		System.out.println("\nUsed data media on "+pc.getName()+":");
		for(String s : pc.volumes()) {
			System.out.println("- "+s);
		}
		
		CardCF2Reader cf1 = pc.getCf2reader();
		res = cf1.insert(new CardCF1("CF1"));
		System.out.println("\nInserted " +cf1.getName() +" into CF2Reader: "+res);
		
		System.out.println("Ejected "+cf2.getName()+" from CF2Reader: "+cf2.eject());
		
		res = cf1.insert(new CardCF1("CF1"));
		System.out.println("Inserted "+cf1.getName()+" into CF2Reader: "+res);
		
		CardMSReader ms = pc.getMsreader();
		res = ms.insert(new CardSD("SD2"));
		System.out.println("Inserted SD2 into MSReader: "+res);
		
		OptDVDDrive dvd = pc.getDVDDrive();
		res = dvd.insert(new OptDVD("DVD1"));
		System.out.println("Inserted "+dvd.getName()+" into DVDReader: "+res);
		
		System.out.println("\nUsed data media on "+pc.getName()+":");
		for(String s : pc.volumes()) {
			System.out.println("- "+s);
		}
		
		System.out.println("\nEjected "+cf1.getName()+" from CF1Reader: "+cf1.eject());
		
		System.out.println("\nUsed data media on "+pc.getName()+":");
		for(String s : pc.volumes()) {
			System.out.println("- "+s);
		}

		OptCDDrive cd = pc.getCDDrive();
		res = cd.insert(new CardMiniSD("miniSD"));
		System.out.println("\nInserted miniSD into CDReader: "+res);
	}
}
