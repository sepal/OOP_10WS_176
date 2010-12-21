
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	enum State {
		IDLE,
		RUNNING,
		FINISHED,
	}
	
	private Labyrinth lab;
	private State state;
	private int sleepTime;
	
	private List<Hunter> hunters;
	private List<Ghost> ghosts;
	
	private ArrayList <Thread> charThreads;
	
	/**
	 *(precondition) every parameter must be >= 0
	 *(postcondition) creates new game with new labyrinth 
	 */
	public Game(boolean[][][] labConfig, int sleepTime) {
		lab = new Labyrinth(labConfig);
		System.out.println(lab);
		hunters = Collections.synchronizedList(new ArrayList<Hunter>());
		ghosts = Collections.synchronizedList(new ArrayList<Ghost>());
		charThreads = new ArrayList<Thread>();
		this.sleepTime = sleepTime;
	}
	
	/**
	 *(precondition) every parameter must be >= 0, x < lab.width, y  < lab.height
	 *(postcondition) creates new ghost on field (its start field), puts ghost into lab, adds to character list 
	 */
	public void createGhost(int x, int y) {
		Ghost g;
		ghosts.add(g = new Ghost(x, y, sleepTime, this));
		lab.getField(x, y).enter(g);
	}
	
	/**
	 *(precondition) every parameter must be >= 0, x < lab.width, y  < lab.height
	 *(postcondition) creates new hunter on field (its start field), puts hunter into lab, adds to character list 
	 */
	public void createHunter(int x, int y, String name) {
		Hunter h;
		hunters.add(h = new Hunter(sleepTime, x, y, name, this));
		lab.getField(x, y).enter(h);
	}
	
	/**
	 *(precondition) hunter must exist
	 *(postcondition) removes hunter from character list, if list is empty, game ends
	 */
	public synchronized void killHunter(Hunter h) {
		this.hunters.remove(h);
		
		// TODO: fixen, kann ja nie eintretten da ja noch die ghosts drinnen sind.
		if (this.hunters.isEmpty()) {
			System.out.println("All hunters have died");
			System.out.println("Ghosts win!!!");
			endGame();
		}
	}
	
	/**
	 *(precondition) labyrinth must exist
	 *(postcondition) returns lab
	 */
	public Labyrinth getLabyrith() {
		return this.lab;
	}
	
	/**
	 *(postcondition) sets state on running, starts thread for each character, adds thread to game's tread list
	 */
	public void startGame() {
		this.state = State.RUNNING;
		for (Character c: this.hunters) {
			Thread t = new Thread(c);
			System.out.println(t);
			charThreads.add(t);
			t.start();
		}
	}
	
	/**
	 *(postcondition) sets state on finished, interrupts each thread
	 */
	public void endGame() {
		this.state = State.FINISHED;
		for (Thread t: charThreads) {
			t.interrupt();
		}
	}
	
	/**
	 * (precondition) Should only be called by hunter.
	 * (postcondition) Game should be stopped and the value of treasure of each hunter outputed.
	 */
	public void hunterWin() {
		this.endGame();
		System.out.println("Hunters win!!");
		for (Hunter h: hunters) {
			// TODO: Englisch verbessern...
			System.out.println(h.getName() + " has " + h.getBooty() + " value of treasure.");
		}
	}
	
	/**
	 * (precondition) 
	 */
	
	/**
	 *(postcondition) returns current state
	 */
	public State getState() {
		return state;
	}
	
}
