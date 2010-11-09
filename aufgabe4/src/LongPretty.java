
public class LongPretty extends DepthPretty {

	/*
	 * (precondition)depth should be >= 0
	 */
	public LongPretty(int depth) {
		super(depth);
	}

	/*
	 * (precondition)s should not be empty.
	 * (postcondition)A copy or transformed copy of the s should be returned.
	 * (invariant) String s itself will not be modified
	 */
	@Override
	public String transform(String s) {
		char si;
		boolean newline = true;
		
		// Using StringBuilder because append is more efficient
		// String append creates new String Objects every time
		StringBuilder sb = new StringBuilder("");
		
		/* Main loop, traverses the input String and outputs formatted
		String into the StrinbBuilder. Trailing white spaces are not handled
		here though, because that was not possible with this method. */
		for (int i=0; i < s.length(); i++) {
			si = s.charAt(i);
			
			switch (si) {
				case '{':
					if (!newline) sb.append('\n');
					sb.append(this.getPosSpaces());
					sb.append("{\n");
					pos += depth;
					newline = true;
					break;
					
				case '}':
					if (!newline) sb.append('\n');
					if (pos > 0) pos -= depth;
					sb.append(getPosSpaces());
					sb.append("}\n");
					newline = true;
					break;
				
				case '\n':
					newline = true;
					sb.append('\n');
					break;

				case ';':
					if (newline) sb.append(this.getPosSpaces());
					newline = true;
					sb.append(";\n");
					break;

				case ' ':case '\t':
					if (!newline) sb.append(si);
					break;
					
				default:
					if (newline) sb.append(this.getPosSpaces());
					sb.append(si);
					newline = false;
			}
		}
		
		/* This part traverses the already pre-formatted String in
		 * the StringBuilder. It only takes care of trailing white spaces at
		 * the end of a line.
		 */
		newline = false;
		si = '\0';
		int nli, spi;
		nli = spi = -1;
		for (int i=sb.length()-1; i >= 0; i--) {
			si = sb.charAt(i);
			if (newline == true) {
				if (si == ' ' || si == '\t') {
					spi = i;
				} else {
					if (spi > 0) {
						// Deletes substring from start to (_excluding_) end
						sb.delete(spi, nli);
					}
					newline = false;
					spi = -1;
				}
			}
			
			if (si == '\n') {
				newline = true;
				nli = i;
			} 
		}
		
		return sb.toString();
	}
	
}
