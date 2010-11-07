

public class VarPretty extends DepthPretty {
	public enum Mode {
		Short, Long
	}
	private DepthPretty mode;
	private Mode modeNx;
	private boolean resetted;

	public VarPretty(Mode m, int depth) {
		super(depth);
		resetted = false;
		setMode(m);
		reset();
	}

	public void setMode(Mode m) {
		this.modeNx = m;
		if (resetted)
			reset();
			
	}
	
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
	
	@Override
	public String transform(String s) {
		resetted = false;
		return mode.transform(s);
	}

}
