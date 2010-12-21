import java.util.ArrayList;



public abstract class Character implements Runnable {

	private int positionx;
	private int positiony;
	private int treasure;
	private int time;
	private Game game;
	private boolean running;
	ArrayList<Field> way;

	public Character(int time, int x, int y, Game game) {
		this.time = time;
		this.positionx = x;
		this.positiony = y;		
		this.game = game;
		way = new ArrayList<Field>();
	}
	
	@Override
	public void run() {
		this.running = true;
		try {
			while (this.running && game.getState() == Game.State.RUNNING) {
				this.walk();
				Thread.sleep(this.time);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

	protected void walk(){
		
	}
}
