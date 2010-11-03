
public abstract class DepthPretty implements Pretty {
	protected int pos;
	
	@Override
	abstract public String transform(String s);
	
	@Override
	public void reset() {
		pos = 0;
	}

}
