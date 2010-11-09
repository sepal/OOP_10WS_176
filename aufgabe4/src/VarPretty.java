

public class VarPretty extends DepthPretty {
	public enum Mode {
		Short, Long
	}
	private DepthPretty mode;
	private Mode modeNx;
	private boolean resetted;

	/*
	 * (precondition)depth should be >= 0, mode should be not null
	 */
	public VarPretty(Mode m, int depth) {
		super(depth);
		resetted = false;
		setMode(m);
		reset();
	}

	/*
	 * (precondition)M should not be null.
	 */
	public void setMode(Mode m) {
		this.modeNx = m;
		if (resetted)
			reset();
			
	}
	
	/*
	 * (precondition) Depth should be >= 0
	 */
	public void setDepth(int depth) {
		this.depth = depth;
		if (resetted)
			reset();
	}

	@Override
	public void reset(){
		super.reset();
		if (modeNx == Mode.Short) {
			System.out.println("Changing mode to short");
			mode = new ShortPretty(depth);
		} else {
			mode = new LongPretty(depth);
		}
		resetted = true;
	}
	
	/*
	 * (precondition)s should not be empty.
	 * (postcondition)A copy or transformed copy of the s should be returned.
	 * (invariant) String s itself will not be modified
	 */
	@Override
	public String transform(String s) {
		resetted = false;
		return mode.transform(s);
	}

}
