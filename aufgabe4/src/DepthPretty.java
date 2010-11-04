
public abstract class DepthPretty implements Pretty {
	protected int pos, depth;
	
	public DepthPretty(int depth) {
		this.depth = depth;
	}
	
	@Override
	abstract public String transform(String s);
	
	@Override
	public void reset() {
		pos = 0;
	}

}
