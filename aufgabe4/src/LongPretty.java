
public class LongPretty extends DepthPretty {

	public LongPretty(int depth) {
		super(depth);
	}

	@Override
	public String transform(String s) {
		int charcnt=0;
		
		StringBuilder sb = new StringBuilder("");
		
		for (int i=0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case ' ':case '\t':
					
					break;
				case '\n':
					break;
				case ';':
					break;
			}
		}
		
		return null;
	}

}
