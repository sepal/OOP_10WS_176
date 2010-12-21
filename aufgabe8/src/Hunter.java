
public class Hunter extends Character {
	private String name;
	private int booty;
	private Game game;

	public Hunter(int time, int x, int y, String name, Game game) {
		super(time, x, y, game);
		this.name = name;
		this.booty = 0;
	}

	@Override
	protected void walk() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getBooty() {
		return booty;
	}
	
	public void die() {
		game.killHunter(this);
	}
}
