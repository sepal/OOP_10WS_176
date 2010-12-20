import java.util.Random;


public class Labyrinth {

	private Field[][] lab;
	
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
				
				Field f = new Field(j, i, walls);
				lab[j][i] = f;
				
			}
		}
		
	}	
}
