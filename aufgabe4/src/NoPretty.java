
public class NoPretty implements Pretty {
	@Override
	public String transform(String s) {
		return s;
	}
	
	@Override
	public void reset() {
		return;
	}
}
