
public class Test {		
	public static void main(String[] args) {
		boolean res;
		
		Computer pc = new Computer("PC1", new HDD("HDD1"), new SSD("SSD1"));
		
		System.out.println(pc.getName());
		System.out.println("Hard Drives: "+pc.getSda()+", "+pc.getSdb()+"\n");
		
		System.out.println("Used ports of "+pc.getName()+":");
		for(String s : pc.volumes()) {
			while(!s.equals("")){
				System.out.println("- "+s);
				break;
			}
		}
		
		CardSDReader sdr = pc.getSdreader();
		res = sdr.insert(new CardSD("SD1"));
		System.out.println("\nInserted into " + sdr.getName()+": "+res);
		
		System.out.println("\nUsed ports of "+pc.getName()+":");
		for(String s : pc.volumes()) {
			while(!s.equals("")){
				System.out.println("- "+s);
				break;
			}
		}
		
		CardCF2Reader cf2 = pc.getCf2reader();
		res = cf2.insert(new CardCF2("CF2"));
		System.out.println("\nInserted into " + cf2.getName()+": "+res);
		
		res = cf2.insert(new CardCF1("CF2.1"));
		System.out.println("Inserted into " + cf2.getName()+": "+res);
		
		System.out.println("\nUsed ports of "+pc.getName()+":");
		for(String s : pc.volumes()) {
			while(!s.equals("")){
				System.out.println("- "+s);
				break;
			}
		}
		
		CardCF2Reader cf1 = pc.getCf2reader();
		res = cf1.insert(new CardCF1("CF1"));
		System.out.println("\nInserted CF1 into " + cf1.getName()+": "+res);
		
		res = cf2.eject();
		System.out.println("Ejected from "+cf2.getName()+": "+res);
		
		res = cf1.insert(new CardCF1("CF1"));
		System.out.println("\nInserted CF1 into " + cf1.getName()+": "+res);
		
		
	}
}
