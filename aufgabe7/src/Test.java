
public class Test {		
	public static void main(String[] args) {
		boolean res;
		
		Computer pc = new Computer("PC1", new HDD("HDD1"), new SSD("SSD1"));
		
		System.out.println(pc.getName());
		System.out.println("Hard Drives: "+pc.getSda()+", "+pc.getSdb()+"\n");
		
		System.out.println("Used ports of "+pc.getName()+":");
		String[] list = pc.volumes();
		
		for(String s : list) {
			while(!s.equals("")){
				System.out.println("- "+s);
				break;
			}
		}
		
		CardSDReader sdr = pc.getSdreader();
		sdr.insert(new CardSD("SD1"));
		
		System.out.println();
		
		for(String s : list) {
			System.out.println("- "+s);
		}
		
	}
}
