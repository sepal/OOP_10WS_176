
public class BlockPretty implements Pretty {
	private StringBuilder newline;

	public BlockPretty(int newlines) {
		newline = new StringBuilder();
		for(int i=0; i<newlines; i++) {
			newline.append('\n');
		}		
	}
	
	@Override
	public String transform(String s) {
		if (newline.length() > 0) {
			StringBuilder sb = new StringBuilder("");
			for (int i=0; i < s.length(); i++) {
				char si = s.charAt(i);
				sb.append(si);
				if (si == '}') {
					sb.append(newline);
				}
			}
			return sb.toString();
		} else {
			return new StringBuilder(s).toString();
		}
	}

	@Override
	public void reset() {
	}

}
