
public class Hunter extends Character {

	private String name;
	private int treasure;
	
	public Hunter(int time, int x, int y, String name) {
		super(time, x, y);
		this.name = name;
		this.treasure = 0;
	}

	@Override
	protected void doSomething() {
		// TODO Auto-generated method stub
		
	}

	public void collect() {
		int t = 0;
		
		synchronized(this) {
			t = 
			this.treasure += t;
			t = 0;
			
		}
	}
}
