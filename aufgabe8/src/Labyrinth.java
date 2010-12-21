import java.util.Random;


public class Labyrinth {
	
	private Field[][] lab;
	private int width;
	private int height;
	private int h = 0;
	private int w = 0;
	
	public Labyrinth(int width, int height) {
		this.width = width;
		this.height = height;
		this.lab = new Field[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				
				Random r = new Random();
				boolean wall = false;
				boolean[] walls = new boolean[4];
				
				for(int a = 0; a < 2; a++) {
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

			}
		}
		
	}
	
	public char[][] createLab() {
		h = getHeight();
		w = getWidth();
		w = (w*2)+1;
		h = (h*2)+1;
		int g = 0;
		System.out.println(h+" "+w);
		char[][] c = new char[h][w];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Field f = getField(j, i);
				boolean[] walls = f.getWall();
				if(i ==0 && j == 0) {
					g = 1;
				} else {
					g = 2;
				}
				
				if(walls[0] == true) {
					c[j+g][i+g-1] = '-';
				} else {
					c[j+g][i+g-1] = '.';
				}
				if(walls[1] == true) {
					c[j+g+1][i+g] = '|';
				} else {
					c[j+g+1][i+g] = '.';
				}
				if(walls[2] == true) {
					c[j+g][i+g+1] = '_';
				} else {
					c[j+g][i+g+1] = '.';
				}
				if(walls[3] == true) {
					c[j+g-1][i+g] = '|';
				} else {
					c[j+g-1][i+g] = '.';
				}
				c[j+g][i+g] = '#';
			}
		}
		return c;
	}
	
	public String toString() {
		char[][] c = createLab();
		String s = "";
		for(int i = 0; i < h; i++) {
			for(int j = 0;j < w; j++) {
				s += c[j][i];
			}
			s+="\n";
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
