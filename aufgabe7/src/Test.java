
public class Test {		
	public static void main(String[] args) {
		boolean res;
		Computer pc1 = new Computer("PC1", new HDD("SDA"), new SSD("SDB"));
		CardMSReader msr = new CardMSReader();
		
		pc1.getUsb1().insert(msr);
		
		for (String s: pc1.volumes()) {
			System.out.println(s);
		}
	}
}
