import java.util.Random;


public class Labyrinth {
	
	private Field[][] lab;
	private int width;
	private int height;
	private int h = 0;
	private int w = 0;
	
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
	
	public Field getField(int x, int y) {
		return lab[y][x];
	}
	
	public boolean goNorth(Field f) {
		return f.hasWall(Field.NORTH);
	}
	
	public boolean goEast(Field f) {
		return f.hasWall(Field.EAST);
	}
	
	public boolean goSouth(Field f) {
		if(f.hasWall(Field.SOUTH) || lab[f.getX()][f.getY()+1].hasWall(Field.NORTH)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean goWest(Field f) {
		if(f.hasWall(Field.WEST) || lab[f.getX()-1][f.getY()].hasWall(Field.EAST)) {
			return true;
		} else {
			return false;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
