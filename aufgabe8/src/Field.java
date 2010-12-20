import java.util.ArrayList;

	
public class Field {
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	// The position coordinates
	private int x,y;
	private boolean[] wall;
	
	private ArrayList<Hunter> huntersOnTheField;
	
	/**
	 * precondition: 
	 * Every parameter should be > 0, wall should.length == 4, 
	 * Field constants should be used to set the right walls.
	 * 
	 * postcondition:
	 * A new field object was created.
	 */
	public Field(int x, int y, boolean[] wall) {
		this.x = x;
		this.y = y;
		this.wall = wall;
	}
}
