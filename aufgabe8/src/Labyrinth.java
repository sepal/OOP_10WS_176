import java.util.Random;


public class Labyrinth {

	private Field[][] lab;
	
	public Labyrinth(int x, int y) {
		this.lab = new Field[y][x];
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				
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
					walls[]
				}
				
				Field f = new Field(j, i, walls);
				
				
			}
		}
		
	}	
}
