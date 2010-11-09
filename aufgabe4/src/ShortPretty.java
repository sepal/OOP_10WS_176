
public class ShortPretty extends DepthPretty {

	/*
	 * (precondition)depth should be >= 0
	 */
	public ShortPretty(int depth) {
		super(depth);
	}

	@Override
	public String transform(String s) {
		char si;
		String newS ="";
		boolean newline = true;
		
		for(int i = 0; i < s.length(); i++) {
			si = s.charAt(i);
			
			if(si == '{'){
				newS += "{\n";
				pos += depth;
				newline = true;
			}
			else if(si == '}') {
				if(pos>0) {
					pos -= depth;
					newS += this.getPosSpaces()+"}\n";
				}
				else {
					newS += "}\n";
				}
				newline = true;
			}
			else if(si == ';') {
				if(!newline) {
					newS += ";\n";
				} else {
					newS += this.getPosSpaces()+";\n";
				}
				newline = true;
			}
			else if(si == '\n') {
				newS += "\n";
				newline = true;
			}
			else if(si == '*'){
				if(s.charAt(i+1) == '/') {
					if(s.charAt(i+2) != ';') {
						newS += "*/"+'\n';
						newline = true;
						i++;
					} else {
						newS += "*";
					}
				}
				else {
					newS += "*";
				}
			}
			else if(si == ' ' || si == '\t') {
				if(!newline) {
					newS += si;
				}
				this.getPosSpaces();
			}
			else {
				if(newline) {
					newS += this.getPosSpaces();
				}
				newS += si;
				newline = false;
			}
		}
		return newS;
	}

}
