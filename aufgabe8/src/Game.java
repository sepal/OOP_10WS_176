
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
	
	public Game(int width, int height, int sleepTime) {
		lab = new Labyrinth(width, height);
		characters = Collections.synchronizedList(new ArrayList<Character>());
		this.sleepTime = sleepTime;
	}
	
	private void addCharacter(Character character) {
		characters.add(character);
	}
	
	public void createGhost(int x, int y) {
		addCharacter(new Ghost(x, y, sleepTime));
	}
	
	public void createHunter(int x, int y, String name) {
		addCharacter(new Hunter(sleepTime, x, y, name));
	}
	
	public synchronized void killHunter(Hunter character) {
		this.characters.remove(character);
		if (this.characters.isEmpty()) {
			System.out.println("All hunters have died");
			endGame();
		}
	}
	
	public void startGame() {
		this.state = State.RUNNING;
		for (Character c: this.characters) {
			new Thread(c).start();
		}
	}
	
	private void endGame() {
		this.state = State.FINISHED;
	}
	
	
}
