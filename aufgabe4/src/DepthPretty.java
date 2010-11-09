import java.util.Arrays;

public abstract class DepthPretty implements Pretty {
	protected int pos, depth;
	/*
	 * (precondition)depth should be >= 0
	 */
	public DepthPretty(int depth) {
		this.depth = depth;
		this.pos = 0;
	}
	
	/*
	 * (precondition)s should not be empty.
	 * (postcondition)A copy or transformed copy of the s should be returned.
	 * (invariant) String s itself will not be modified
	 */
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
