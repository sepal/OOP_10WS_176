import java.util.Arrays;

public abstract class DepthPretty implements Pretty {
	protected int pos, depth;
	
	public DepthPretty(int depth) {
		this.depth = depth;
		this.pos = 0;
	}
	
	@Override
	abstract public String transform(String s);
	
	@Override
	public void reset() {
		pos = 0;
	}
	
	protected String getPosSpaces() {
		char[] spaces = new char[pos];
		Arrays.fill(spaces, ' ');
		return (new String(spaces));
	}
}
