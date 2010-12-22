import java.util.ArrayList;

public class Ghost extends Character {
	private int posx, posy, lastpos;
	private Game game;
	private Field pos;
	
	/**
	 *  (precondition) x and y have to be in the bounds of the games labyrinth, game must be a valid game instance
	 *  (not null), time has to be bigger than 0
	 *  (postcondition) If all parameters were valid, a new instance of Ghost has been created, ready to be started,
	 *  if the start-coordinates were invalid, this Ghost will be terminated and removed.
	 */
	public Ghost(int time, int x, int y, Game game) {
		super(time, game);
		posx = x;
		posy = y;
		this.game = game;
		try {
			pos = game.getLabyrith().getField(x, y);
		} catch (IndexOutOfBoundsException ex) {
			System.err.println("Ghost in game "+game+" spawned on invalid field! Ghost will die now.");
			this.die();
		}
	}
	
	/**
	 * (precondition) Ghost is up and running, game is a valid game, the Ghost is on a valid position on
	 * the field and able to move (not blocked by walls)
	 * (postcondition) After this method was called, with all the preconditions fulfilled, the Ghost will
	 * have moved one square, eventually killing a Hunter or ending the game in the process.
	 *  If the conditions were not met, the Ghost will be terminated and removed
	 */
	@Override
	protected void move() {
		// List all directions
		ArrayList<Integer> directions = new ArrayList<Integer>(4);
		directions.add(Field.NORTH); // N
		directions.add(Field.EAST); // E
		directions.add(Field.SOUTH); // S
		directions.add(Field.WEST); // W
		
		Labyrinth lab  = game.getLabyrith();
		// Remove directions blocked by wall or off the labyrinth (win field)
		if (lab.hasWall(pos, Field.NORTH) || lab.onWinField(posx, posy-1)) {
			directions.remove(new Integer(Field.NORTH));
		} else if (lab.hasWall(pos, Field.EAST) || lab.onWinField(posx+1, posy)) {
			directions.remove(new Integer(Field.EAST));
		} else if (lab.hasWall(pos, Field.SOUTH) || lab.onWinField(posx, posy+1)) {
			directions.remove(new Integer(Field.SOUTH));
		} else if (lab.hasWall(pos, Field.WEST) || lab.onWinField(posx-1, posy)) {
			directions.remove(new Integer(Field.WEST));
		}
		
		// If list is empty and there's no where to go, die
		if (directions.size() == 0) { 
			this.die();
			return;
		}
		
		// If list size > 1 and lastpos defined, don't go there
		if (directions.size() > 1 && lastpos >= 0 && lastpos < 4) {
			directions.remove(lastpos);
		}

		// Pick random direction from list
		int direction = directions.get( (int) Math.round(Math.random() * directions.size()) );
		int newx, newy;
		newx = posx;
		newy = posy;
		if (direction == Field.NORTH) {
			newy--;
		} else if (direction == Field.EAST) {
			newx++;
		} else if (direction == Field.SOUTH) {
			newy++;
		} else if (direction == Field.WEST) {
			newx--;
		}
		
		pos.leave(this); // Leave field
		try {
			pos = game.getLabyrith().getField(newx, newy);
		} catch (IndexOutOfBoundsException ex) {
			// This cannot happen if this program is correct
			System.err.println("ERRO: Ghost made illegal move! This should not happen, terminating program.");
			System.exit(1);
		}
		pos.enter(this);
		
		// Update lastpos and coordinates
		if (direction == Field.NORTH) {
			lastpos = Field.SOUTH;
		} else if (direction == Field.EAST) {
			lastpos = Field.WEST;
		} else if (direction == Field.SOUTH) {
			lastpos = Field.NORTH;
		} else if (direction == Field.WEST) {
			lastpos = Field.EAST;
		}
		posx = newx;
		posy = newy;
	}
	
	// (postcondition) The Thread of this Ghost will stop at some point, it will be removed from
	//  the game and not move anymore
	private void die() {
		this.stopThread();
		game.removeGhost(this);
	}
}
