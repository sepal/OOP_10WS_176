import java.util.ArrayList;

public abstract class Character implements Runnable {
	private int positionx, positiony, time;
	private boolean running;
	private Integer lastpos;
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
		lastpos = -1;
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
		directions.add(Field.NORTH); // N
		directions.add(Field.EAST); // E
		directions.add(Field.SOUTH); // S
		directions.add(Field.WEST); // W
		
		Labyrinth lab  = game.getLabyrith();
		// Remove directions blocked by wall 
		if (lab.hasWall(f, Field.NORTH)) {
			directions.remove(new Integer(Field.NORTH));
		} else if (lab.hasWall(f, Field.EAST)) {
			directions.remove(new Integer(Field.EAST));
		} else if (lab.hasWall(f, Field.SOUTH)) {
			directions.remove(new Integer(Field.SOUTH));
		} else if (lab.hasWall(f, Field.WEST)) {
			directions.remove(new Integer(Field.WEST));
		}
		
		// If list is empty and there's no where to go
		if (directions.size() == 0) return;
		
		// If list size > 1 and lastpos defined, don't go there
		
		if (lastpos >= 0 && lastpos < 4) {
			if (directions.size() > 1) directions.remove(lastpos);
		}
		// TODO: update lastpos

		// Pick random direction from list
		// Mark field as to be moved to, or return it or any other way
		// to let subclasses know, that this could be next field
	}
}
