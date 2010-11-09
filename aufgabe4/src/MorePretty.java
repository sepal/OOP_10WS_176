
public class MorePretty extends VarPretty {
	private String lastString;
	
	/*
	 * (precondition)depth should be >= 0, mode should be not null
	 */
	public MorePretty(Mode m, int depth) {
		super(m, depth);
	}

	@Override
	public void reset(){
		super.reset();
		lastString = "";
	}
	
	@Override
	public String transform(String s) {
		lastString = s;
		return super.transform(s);
	}
	
	private int countString(char ch) {
		int pos = lastString.indexOf(ch);
		int count = 0;
		while (pos>-1) {
			if (count > 1000) {
				return -1;
			}
			count++;
			pos = lastString.indexOf(ch, pos+1);
			//System.out.println(pos);
		}
		return count;
	}

	public int open() {
		int open = countString('{');
		int closed = countString('}');
		
		return open-closed;
	}
}
