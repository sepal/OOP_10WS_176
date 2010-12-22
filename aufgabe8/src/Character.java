import java.util.ArrayList;

public abstract class Character implements Runnable {
	private int time;
	private boolean running;
	private Game game;
	
	/**
	 *(precondition) game must exist, other parameters must be >= 0, x < lab.width, y < lab.height
	 *(postcondition) creates new character at a start position in a game, way contains only start point, 
	 *because hasn't walked yet 
	 */
	public Character(int time, Game game) {
		this.time = time;	
		this.game = game;
	}
	
	/**
	 *(postcondition) character walks while game is running, thread sleeps after each position change 
	 */
	@Override
	public void run() {
		this.running = true;
		try {
			while (this.running && game.getState() == Game.State.RUNNING) {
				this.move();
				Thread.sleep(this.time);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

	/**
	 *(postcondition) character walks
	 */
	protected abstract void move();
}
