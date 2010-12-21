
public class Test {
	/*
	Standard Konstruktor für 4x4 Labyrinth;
	boolean [][][]lab = {
		{{false, false}, {false, false}, {false, false}, {false, false}},
		{{false, false}, {false, false}, {false, false}, {false, false}},
		{{false, false}, {false, false}, {false, false}, {false, false}},
		{{false, false}, {false, false}, {false, false}, {false, false}},
	};
	 */
	
	public static void main(String[] args) {
		boolean [][][]lab = {
			{{true , true }, {true , false}, {true , false}, {true , true }},
			{{false, false}, {true , true }, {false, false}, {false, false}},
			{{true , false}, {false, false}, {true , false}, {false, true }},
			{{false, true }, {false, false}, {false, false}, {true , false}},
		};
		System.out.println("Creating Game");
		Game g = new Game(lab, 25);
		System.out.println("Creating Hunter");
		g.createHunter(0, 0, "Ben");
		System.out.println("Starting Game.");
		g.startGame();
		long starttime = System.currentTimeMillis();
		// Repeat for 2 seconds or until the game is finished.
		while (g.getState() == Game.State.RUNNING && System.currentTimeMillis()-starttime < 2000){
			System.out.println("Game running");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Ending Game.");
		g.endGame();
		
	}
}
