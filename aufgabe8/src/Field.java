import java.util.ArrayList;

	
public class Field {
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	// The position coordinates
	private int x,y;
	private boolean[] wall;
	private int treasure;
	
	private ArrayList<Hunter> huntersOnTheField;
	private int ghosts;
	
	/**
	 *(precondition) every parameter must be >= 0, wall.length == 4 
	 *(postcondition) creates new field with walls and treasure
	 */
	public Field(int x, int y, boolean[] wall, int treasure) {
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.treasure = treasure;
	}
	
	/**
	 *(precondition) field must exist 
	 *(postcondition) returns treasure value of field, sets value = 0
	 */
	public synchronized int resetTreasure() {
		int tmp = this.treasure;
		this.treasure = 0;
		return tmp;
	}
	
	/**
	 *(precondition) field must exist, 0 <= wall && wall <= 3
	 *(postcondition) returns true, if field has northern/eastern/southern/western wall, otherwise false
	 */
	public boolean hasWall(int wall) {
		return this.wall[wall];
	}

	/**
	 *(postcondition) returns y of field
	 */
	public int getY() {
		return y;
	}

	/**
	 *(postcondition) returns x of field
	 */
	public int getX() {
		return x;
	}
	
	/**
	 *(precondition) Hunter must exit a field, before entering a new one.
	 *(postcondition) if hunter(s) are on field, ghost kills all, ghost amount increases by 1
	 */
	public synchronized void enter(Ghost g) {
		for (Hunter h: huntersOnTheField) {
			huntersOnTheField.remove(h);
			h.die();
		}
		ghosts++;
	}
	
	/**
	 *(precondition) Ghost must exit a field, before entering a new one.
	 *(postcondition) if ghost(s) are on field, hunter dies, else hunter added to list
	 */
	public synchronized void enter(Hunter h) {
		if (ghosts > 0) {
			h.die();
		} else {
			huntersOnTheField.add(h);
		}
	}
	
	/**
	 *(precondition) Ghost must exit field and enter a new one.
	 *(postcondition) Ghost amount decreases by 1
	 */
	public synchronized void leave(Ghost g) {
		ghosts--;
	}
	
	/**
	 *(precondition) Hunter must exit field and enter a new one.
	 *(postcondition) hunter is removed from the hunter list of this field.
	 */
	public synchronized void leave(Hunter h) {
		if (h != null)
			huntersOnTheField.remove(h);
	}
}
