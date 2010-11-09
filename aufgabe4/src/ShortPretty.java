
public class ShortPretty extends DepthPretty {

	/*
	 * (precondition)depth should be >= 0
	 */
	public ShortPretty(int depth) {
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
		StringBuilder sb = new StringBuilder("");
		boolean newline = true;
		
		for(int i = 0; i < s.length(); i++) {
			si = s.charAt(i);
			
			if(si == '{'){
				sb.append("{\n");
				pos += depth;
				newline = true;
			}
			else if(si == '}') {
				if(pos>0) {
					pos -= depth;
				}
				sb.append(this.getPosSpaces());
				sb.append("}\n");
				newline = true;
			}
			else if(si == ';') {
				if(newline) {
					sb.append(this.getPosSpaces()+";\n");
				}
				sb.append(";\n");
				newline = true;
			}
			else if(si == '\n') {
				sb.append("\n");
				newline = true;
			}
			else if(si == '*'){
				if(s.charAt(i+1) == '/') {
					if(s.charAt(i+2) != ';') {
						sb.append("*/");
						sb.append('\n');
						newline = true;
						i++;
					} else {
						sb.append("*");
					}
				}
				else {
					sb.append("*");
				}
			}
			else if(si == ' ' || si == '\t') {
				if(!newline) {
					sb.append(si);
				}
				this.getPosSpaces();
			}
			else {
				if(newline) {
					sb.append(this.getPosSpaces());
				}
				sb.append(si);
				newline = false;
			}
		}
		
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
