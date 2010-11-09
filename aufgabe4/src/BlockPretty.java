
public class BlockPretty implements Pretty {
	private StringBuilder newline;

	/*
	 * (precondition)Newlines should be not newlines.
	 */
	public BlockPretty(int newlines) {
		newline = new StringBuilder();
		for(int i=0; i<newlines; i++) {
			newline.append('\n');
		}		
	}
	
	/*
	 * (precondition)s should not be empty.
	 * (postcondition)A copy or transformed copy of the s should be returned.
	 * (invariant) String s itself will not be modified
	 */
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
