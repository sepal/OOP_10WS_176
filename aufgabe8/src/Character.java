import java.util.ArrayList;



public abstract class Character implements Runnable {

	private int positionx;
	private int positiony;
	private int time;
	private Game game;
	private boolean running;
	private ArrayList<Field> way;
	
	/**
	 *(precondition) game must exist, other parameters must be >= 0, x < lab.width, y < lab.height
	 *(postcondition) creates new character at a startposition in a game, way contains only startpoint, 
	 *because hasn't walked yet 
	 */
	public Character(int time, int x, int y, Game game) {
		this.time = time;
		this.positionx = x;
		this.positiony = y;		
		this.game = game;
		way = new ArrayList<Field>();
		Field f = this.game.getLabyrith().getField(x, y); //start point
		way.add(f);
	}
	
	/**
	 *(postcondition) character walks while game is running, thread sleeps after each position change 
	 */
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

	/**
	 *(postcondition) character walks
	 */
	protected void walk(){
		
	}
}
