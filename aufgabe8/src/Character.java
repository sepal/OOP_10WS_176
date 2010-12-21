import java.util.ArrayList;

public abstract class Character implements Runnable {
	private int positionx;
	private int positiony;
	private int time;
	private boolean running;
	private Game game;
	private Field f;
	
	/**
	 *(precondition) game must exist, other parameters must be >= 0, x < lab.width, y < lab.height
	 *(postcondition) creates new character at a start position in a game, way contains only start point, 
	 *because hasn't walked yet 
	 */
	public Character(int time, int x, int y, Game game) {
		this.time = time;
		this.positionx = x;
		this.positiony = y;		
		this.game = game;
		f = this.game.getLabyrith().getField(x, y); //start point
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
	
	public int getPosX() {
		return positionx;
	}
	
	public int getPosY() {
		return positiony;
	}	

	/**
	 *(postcondition) character walks
	 */
	protected void walk(){
		// List directions
		ArrayList<Integer> directions = new ArrayList<Integer>(4);
		directions.add(1); // N
		directions.add(2); // E
		directions.add(3); // S
		directions.add(4); // W
		
		Labyrinth lab  = game.getLabyrith();
		// Remove directions blocked by wall if (lab.goEast(f))
		// If list is empty and there's no where to go
		if (directions.size() == 0) return;
		// If list size > 1 and lastpos defined, don't go there
		// Pick random direction from list
		// Mark field as to be moved to, or return it or any other way
		// to let subclasses know, that this could be next field
	}
}
