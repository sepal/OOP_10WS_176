
public class Hunter extends Character {

	private String name;
	private int treasure;
	private Game game;

	public Hunter(int time, int x, int y, String name, Game game) {
		super(time, x, y);
		this.name = name;
		this.treasure = 0;
		this.game = game;
	}

	@Override
	protected void doSomething() {
		// TODO Auto-generated method stub
		
	}

	public void collect() {
		int t = 0;
		Field f  = null;
		synchronized(this) {
			t = 
			this.treasure += t;
			t = 0;
			
		}
	}
}
