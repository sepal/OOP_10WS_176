
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	enum State {
		NONE,
		RUNNING,
		FINISHED,
	}
	
	private Labyrinth lab;
	private State state;
	private int sleepTime;
	
	private List <Character> characters;
	private ArrayList <Thread> charThreads;
	
	public Game(int width, int height, int sleepTime) {
		lab = new Labyrinth(width, height);
		characters = Collections.synchronizedList(new ArrayList<Character>());
		this.sleepTime = sleepTime;
	}
	
	private void addCharacter(Character character) {
		characters.add(character);
	}
	
	public void createGhost(int x, int y) {
		addCharacter(new Ghost(x, y, sleepTime, this));
	}
	
	public void createHunter(int x, int y, String name) {
		addCharacter(new Hunter(sleepTime, x, y, name, this));
	}
	
	public synchronized void killHunter(Hunter character) {
		this.characters.remove(character);
		if (this.characters.isEmpty()) {
			System.out.println("All hunters have died");
			endGame();
		}
	}
	
	public Labyrinth getLabyrith() {
		return this.lab;
	}
	
	public void startGame() {
		this.state = State.RUNNING;
		for (Character c: this.characters) {
			Thread t = new Thread(c);
			charThreads.add(t);
			t.start();
		}
	}
	
	private void endGame() {
		this.state = State.FINISHED;
		for (Thread t: charThreads) {
			t.interrupt();
		}
	}
	
	public State getState() {
		return state;
	}
	
}
