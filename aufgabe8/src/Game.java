import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
	
	private ConcurrentHashMap<Character, Thread> charThreads;
	
	/**
	 *(precondition) every parameter must be >= 0
	 *(postcondition) creates new game with new labyrinth 
	 */
	public Game(boolean[][][] labConfig, int sleepTime) {
		lab = new Labyrinth(labConfig);
		hunters = Collections.synchronizedList(new ArrayList<Hunter>());
		ghosts = Collections.synchronizedList(new ArrayList<Ghost>());
		charThreads = new ConcurrentHashMap<Character, Thread>();
		this.sleepTime = sleepTime;
	}
	
	/**
	 *(precondition) every parameter must be >= 0, x < lab.width, y  < lab.height
	 *(postcondition) creates new ghost on field (its start field), puts ghost into lab, adds to character list 
	 */
	public void createGhost(int x, int y) {
		Ghost g;
		ghosts.add(g = new Ghost(sleepTime, x, y, this));
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
	 *(postcondition) removes hunter from character list and stops the thread, if list is empty, game ends
	 */
	public synchronized void killHunter(Hunter h) {
		System.out.println("Hunter " + h.getName() + " was killed.");
		this.hunters.remove(h);
		if (this.charThreads.get(h) != null)
			this.charThreads.get(h).interrupt();
		
		if (this.hunters.isEmpty()) {
			System.out.println("All hunters have died");
			System.out.println("Ghosts win!!!");
			endGame();
		}
	}
	
	/**
	 *(precondition) ghost must exist
	 *(postcondition) removes ghost from character list and stops the thread.
	 */
	public synchronized void removeGhost(Ghost g) {
		this.ghosts.remove(g);
		if (this.charThreads.get(g) != null)
			this.charThreads.get(g).interrupt();
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
	public synchronized void startGame() {
		this.state = State.RUNNING;
		for (Hunter c: this.hunters) {
			Thread t = new Thread(c);
			charThreads.put(c, t);
			t.start();
		}
		for (Ghost c: this.ghosts) {
			Thread t = new Thread(c);
			charThreads.put(c, t);
			t.start();
		}
	}
	
	/**
	 *(postcondition) sets state on finished, interrupts each thread
	 */
	public synchronized void endGame() {
		// TODO
		this.state = State.FINISHED;
		for (Hunter h: this.hunters) {
			this.charThreads.get(h).interrupt();
		}
		for (Ghost h: this.ghosts) {
			this.charThreads.get(h).interrupt();
		}
		System.out.println(Thread.activeCount());
	}
	
	/**
	 * (precondition) Should only be called by hunter.
	 * (postcondition) Game should be stopped and the value of treasure of each hunter outputed.
	 */
	public synchronized void hunterWin() {
		this.endGame();
		System.out.println("Hunters win!!");
		for (Hunter h: hunters) {
			System.out.println(h.getName() + " has " + h.getBooty() + " worth of treasure.");
		}
	}
	
	/**
	 *(invariant) returns current state
	 */
	public State getState() {
		return state;
	}
	
}
