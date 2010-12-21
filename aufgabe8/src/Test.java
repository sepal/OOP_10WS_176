import java.util.TimerTask;
import java.util.Timer;

public class Test extends TimerTask {
	private static final long PROGTIME = 9500; // 10 Seconds minus some time for cleaning up/output
	private static final long NUMGAMES = 1;
	private static Timer timer;
	/*
	Standard Konstruktor f�r 4x4 Labyrinth;
	boolean [][][]lab = {
		{{false, false}, {false, false}, {false, false}, {false, false}},
		{{false, false}, {false, false}, {false, false}, {false, false}},
		{{false, false}, {false, false}, {false, false}, {false, false}},
		{{false, false}, {false, false}, {false, false}, {false, false}},
	};
	*/
	private boolean [][][]lab = {
			{{true , true }, {true , false}, {true , false}, {true , true }},
			{{false, false}, {true , true }, {false, false}, {false, false}},
			{{true , false}, {false, false}, {true , false}, {false, true }},
			{{false, true }, {false, false}, {false, false}, {true , false}},
		};
	private Game g1;
	
	
	public static void main(String[] args) {
		Test t = new Test();
		timer = new Timer();
		// Starting timer
		timer.schedule(t, PROGTIME);
		
		t.start();
		
		while (t.isRunning()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// Continue
			}
		}
	}
	
	public Test() {
		// Creating games
		System.out.println("Creating Game");
		g1 = new Game(lab, 25);
		System.out.println("Creating Hunter");
		g1.createHunter(0, 0, "Ben");
	}

	public void start() {
		System.out.println("Starting Game.");
		g1.startGame();
	}
	
	public boolean isRunning() {
		int deadgames = 0;
		if (g1.getState() == Game.State.FINISHED) deadgames++;
		// Other games
		if (deadgames == Test.NUMGAMES) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void run() {
		g1.endGame();
		// TODO: Cleanup, output
		System.exit(0);
	}
}
