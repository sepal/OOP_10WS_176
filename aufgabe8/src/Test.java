
public class Test {
	public static void main(String[] args) {
		System.out.println("Creating Game");
		Game g = new Game(5, 5, 25);
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
