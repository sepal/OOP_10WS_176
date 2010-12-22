import java.util.ArrayList;

public class Hunter extends Character {
	private static final int MAXSTEPS = 1000;
	private String name;
	private int booty, posx, posy, steps, lastpos;
	private Game game;
	private Field pos;

	public Hunter(int time, int x, int y, String name, Game game) {
		super(time, game);
		this.name = name;
		this.booty = 0;
		this.posx = x;
		this.posy = y;
		this.steps = 0;
		this.lastpos = -1;
		try {
			pos = game.getLabyrith().getField(x, y);
		} catch (IndexOutOfBoundsException ex) {
			System.err.println("Hunter "+name+" in game "+game+" spawned on invalid field! Hunter will die now.");
			this.die();
		}
	}

	@Override
	protected void move() {
		// List all directions
		ArrayList<Integer> directions = new ArrayList<Integer>(4);
		directions.add(Field.NORTH); // N
		directions.add(Field.EAST); // E
		directions.add(Field.SOUTH); // S
		directions.add(Field.WEST); // W
		
		Labyrinth lab  = game.getLabyrith();
		// Remove directions blocked by wall 
		if (lab.hasWall(pos, Field.NORTH)) {
			directions.remove(new Integer(Field.NORTH));
		} else if (lab.hasWall(pos, Field.EAST)) {
			directions.remove(new Integer(Field.EAST));
		} else if (lab.hasWall(pos, Field.SOUTH)) {
			directions.remove(new Integer(Field.SOUTH));
		} else if (lab.hasWall(pos, Field.WEST)) {
			directions.remove(new Integer(Field.WEST));
		}
		
		// If list is empty and there's no where to go, die
		if (directions.size() == 0) this.die();
		
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
		
		// if win field, game is over
		if (game.getLabyrith().onWinField(null)) {// TODO: null
			game.hunterWin(); 
		} else {
			pos.leave(this); // Leave field
			try {
				pos = game.getLabyrith().getField(newx, newy);
			} catch (IndexOutOfBoundsException ex) {
				// This cannot happen if this program is correct
				System.err.println("ERRO: Hunter "+name+" made illegal move! This should not happen, terminating program.");
				System.exit(1);
			}
			pos.enter(this);
		}
		//else ->move to field and enter
		//if step == maxsteps, end game
		//update lastpos
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
