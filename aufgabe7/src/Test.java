
public class Test {
	public static void main(String[] args) {
		Computer pc1 = new Computer("Dell Optiplex", new HDD("Maxtor 350GB HDD"), new SSD("Corsair X128 SSD"));
		pc1.getUsb1().insert(new CardReader());
		pc1.getUsb2().insert(new ExternalDisc(new HDD("Plextor HDD")));
	}
}
