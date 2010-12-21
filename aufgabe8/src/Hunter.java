
public class Hunter extends Character {
	private String name;
	private int treasure;
	private Game game;

	public Hunter(int time, int x, int y, String name, Game game) {
		super(time, x, y, game);
		this.name = name;
		this.treasure = 0;
	}

	@Override
	protected void walk() {
		// TODO Auto-generated method stub
		
		
	}
	
	public void die() {
		game.killHunter(this);
	}
}
