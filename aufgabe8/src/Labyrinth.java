import java.util.Random;


public class Labyrinth {
	
	private Field[][] lab;
	private int width;
	private int height;
	
	/**
	 *(postcondition) creates new labyrinth with treasures on fields
	 */
	public Labyrinth(boolean[][][] labConfig) {
		int width = labConfig.length;
		int height = labConfig[0].length;
		
		this.lab = new Field[height][width];

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				boolean[] wall = {labConfig[y][x][Field.NORTH], labConfig[y][x][Field.EAST], (y==height-1 ? true : false), (x==0 ? true : false)};
				lab[x][y] = new Field(x, y, wall, new Random().nextInt(11));
			}
		}
		
	}
	
	/**
	 *(precondition) x and y must be > 0 && x < width && y < height
	 *(postcondition) returns field
	 * @throws Exception 
	 */
	public Field getField(int x, int y) throws IndexOutOfBoundsException {
		return lab[y][x];
	}
	
	/**
	 *(precondition) field must exist, direction must be Field.NORTH, Field.EAST, Field.SOUT or Field.WEST
	 *(postcondition) returns true, if field has wall, otherwise false
	 */
	public boolean hasWall(Field f, int direction) throws IndexOutOfBoundsException {		
		if (direction == Field.SOUTH) {
			if(f.hasWall(Field.SOUTH) || lab[f.getX()][f.getY()+1].hasWall(Field.NORTH)) {
				return true;
			} else {
				return false;
			}
		}else if (direction == Field.EAST){ 
			if(f.hasWall(Field.WEST) || lab[f.getX()-1][f.getY()].hasWall(Field.EAST)) {
				return true;
			} else {
				return false;
			}
		}else {
			return f.hasWall(direction);
		}
	}
	
	/**
	 *(precondition) field must exist
	 *(postcondition) returns true, if field is exit field, otherwise false
	 */
	public boolean onWinField(Field f) {
		if (f.getX() == this.width-1 || f.getY() == 0) {
			return true;
		}
		return false;
	}

	/**
	 *(precondition) labyrinth must exist
	 *(postcondition) returns width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 *(precondition) labyrinth must exist
	 *(postcondition) returns height
	 */
	public int getHeight() {
		return height;
	}
}
