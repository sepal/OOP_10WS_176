import java.util.Random;


public class Labyrinth {
	
	private Field[][] lab;
	private int width;
	private int height;
	
	public Labyrinth(int width, int height) {
		this.lab = new Field[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				
				Random r = new Random();
				boolean wall = false;
				boolean[] walls = new boolean[4];
				
				for(int a = 0; a < 2; i++) {
					int z = r.nextInt(2);
					
					if(z == 0) {
						wall = false;
					} else if(z == 1) {
						wall = true;
					}
					walls[a] = wall;
				}
				
				if(j == 0) {
					walls[3] = true;
				}
				if(i == height) {
					walls[2] = true;
				}
				
				if(i == 0 && j == width) { //static exit (zur sicherheit)
					walls[1] = false;
				}
				
				int treasure = r.nextInt(11);
				
				Field f = new Field(j, i, walls, treasure);
				lab[j][i] = f;
				//createLab();
				
			}
		}
		
	}
	
	public char[][] createLab() {
		height =height*2+1;
		width = width*2+1;
		char[][] c = new char[height][width];
		for(int i = 1; i < height; i+=2) {
			for(int j = 1; j < width; j+=2) {
				Field f = getField(j, i);
				boolean[] walls = f.getWall();
				if(walls[0] == true) {
					c[j][i-1] = '-';
				} else {
					c[j][i-1] = ' ';
				}
				if(walls[1] == true) {
					c[j+1][i] = '|';
				} else {
					c[j+1][i] = ' ';
				}
				if(walls[2] == true) {
					c[j][i+1] = '_';
				} else {
					c[j][i+1] = ' ';
				}
				if(walls[3] == true) {
					c[j-1][i] = '|';
				}
				c[j][i] = ' ';
			}
		}
		return c;
	}
	
	public String toString() {
		char[][] c = createLab();
		String s = "";
		
		for(int i = 0; i < height; i++) {
			for(int j = 0;j < width; j++) {
				s += c[j][i];
			}
		}
		
		return s;
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
