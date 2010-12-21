
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
	
	private List <Character> characters;
	private ArrayList <Thread> charThreads;
	
	/**
	 *(precondition) every parameter must be >= 0
	 *(postcondition) creates new game with new labyrinth 
	 */
	public Game(boolean[][][] labConfig, int sleepTime) {
		lab = new Labyrinth(labConfig);
		System.out.println(lab);
		characters = Collections.synchronizedList(new ArrayList<Character>());
		charThreads = new ArrayList<Thread>();
		this.sleepTime = sleepTime;
	}
	
	/**
	 *(precondition) character must be created before
	 *(postcondition) adds character to character list
	 */
	private void addCharacter(Character character) {
		characters.add(character);
	}
	
	/**
	 *(precondition) every parameter must be >= 0, x < lab.width, y  < lab.height
	 *(postcondition) creates new ghost on field (its start field), puts ghost into lab, adds to character list 
	 */
	public void createGhost(int x, int y) {
		Ghost g;
		addCharacter(g = new Ghost(x, y, sleepTime, this));
		lab.getField(x, y).enter(g);
	}
	
	/**
	 *(precondition) every parameter must be >= 0, x < lab.width, y  < lab.height
	 *(postcondition) creates new hunter on field (its start field), puts hunter into lab, adds to character list 
	 */
	public void createHunter(int x, int y, String name) {
		Hunter h;
		addCharacter(h = new Hunter(sleepTime, x, y, name, this));
		lab.getField(x, y).enter(h);
	}
	
	/**
	 *(precondition) hunter must exist
	 *(postcondition) removes hunter from character list, if list is empty, game ends
	 */
	public synchronized void killHunter(Hunter character) {
		this.characters.remove(character);
		if (this.characters.isEmpty()) {
			System.out.println("All hunters have died");
			System.out.println("Ghost win!!!");
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
		for (Character c: this.characters) {
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
	 *(postcondition) returns current state
	 */
	public State getState() {
		return state;
	}
	
}
