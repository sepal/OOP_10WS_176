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
	
	/*
	 * precondition: 
	 * Every parameter should be > 0, wall should.length == 4, 
	 * Field constants should be used to set the right walls.
	 * 
	 * postcondition:
	 * A new field object was created.
	 */
	public Field(int x, int y, boolean[] wall, int treasure) {
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.treasure = treasure;
	}
	
	public void resetTreasure() {
		this.treasure = 0;
	}
	
	public boolean hasWall(int wall) {
		return this.wall[wall];
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	
	public synchronized void enter(Ghost g) {
		
	}
	
	public synchronized void enter(Hunter h) {
		
	}
}
