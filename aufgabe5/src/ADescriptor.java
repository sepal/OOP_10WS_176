
public class ADescriptor extends Descriptor {

	public ADescriptor(String desc) {
		super(desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Descriptor o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int as(String s) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(ch == 'a' || ch =='A') {
				count++;
			}
		}
		return count;
	}

}
