import java.util.TimerTask;
import java.util.Timer;

public class Test extends TimerTask {
	private static final long PROGTIME = 1000;//9500; // 10 Seconds minus some time for cleaning up/output
	//private static final long NUMGAMES = 3;
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
	private boolean [][][]lab1 = {
			{{true , true }, {true , false}, {true , false}, {true , true }},
			{{false, false}, {true , true }, {false, false}, {false, false}},
			{{true , false}, {false, false}, {true , false}, {false, true }},
			{{false, true }, {false, false}, {false, false}, {true , false}},
		};
	boolean [][][]lab2 = {
			{{true , true }, {true , false}, {true , true }},
			{{false, true }, {false, true }, {false, false}},
			{{false, false}, {false, true }, {false, true }},
		};
	boolean [][][]lab3 = {
			{{true , true }, {true , false}, {true , false}, {true , false}, {true , false}},
			{{false, false}, {false, true }, {false, false}, {false, true }, {false, true }},
			{{true , true }, {false, false}, {true , false}, {false, true }, {true , true }},
			{{false, false}, {false, false}, {true , false}, {false, false}, {false, true }},
			{{false, false}, {true , false}, {true , false}, {true , true }, {false, false}},
		};
	private Game g1;
	private Game g2;
	private Game g3;
	private boolean fin1, fin2, fin3;
	
	
	public static void main(String[] args) {
		Test t = new Test();
		timer = new Timer();
		// Starting timer
		//timer.schedule(t, PROGTIME);
		
		t.start1();
		while (t.isRunning()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Continue
			}
		}
		timer.cancel();
		System.out.println("end.");
		System.exit(0);
	}
	
	public Test() {
		// Game 1
		g1 = new Game(lab1, 15);
		g1.createHunter(0, 0, "Ben", 10);
		g1.createHunter(1, 1, "Sep", 20);
		g1.createHunter(2, 0, "Moni", 30);
		g1.createGhost(3, 3, 5);
		g1.createGhost(2, 2, 50);
		
		// Game 2
		g2 = new Game(lab2, 10);;
		g2.createHunter(1, 1, "Ben", 50);
		g2.createGhost(0, 0, 5);
		
		// Game 3
		g3 = new Game(lab3, 20);
		g3.createHunter(0, 0, "Sep");
		g3.createHunter(4, 4, "Moni");
		g3.createGhost(3, 3);
		g3.createGhost(1, 0);
		
		fin1 = fin2 = fin3 = false;
	}

	public void start1() {
		System.out.println("Starting Game1.");
		g1.startGame();
	}

	public void start2() {
		System.out.println("Starting Game2.");
		g2.startGame();
	}

	public void start3() {
		System.out.println("Starting Game3.");
		g3.startGame();
	}
	
	public boolean isRunning() {
		if (!fin1 && g1.getState() == Game.State.FINISHED) {
			System.out.println("Game1 finished.\n\nStarting Game2");
			fin1 = true;
			g2.startGame();
		}
		
		if(!fin2 && g2.getState() == Game.State.FINISHED) {
			System.out.println("Game2 finished.\n\nStarting Game3");
			fin2 = true;
			g3.startGame();
		}
		
		if (!fin3 && g3.getState() == Game.State.FINISHED) {
			System.out.println("Game3 finished.");
			fin3 = true;
		}
		
		if (fin1 && fin2 && fin3) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void run() {
		g1.endGame();
		g2.endGame();
		g3.endGame();
		System.out.println("exit.");
		System.exit(0);
	}
}
