
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
	
	private void createGhost(int x, int y) {
		addCharacter(new Ghost(x, y, sleepTime));
	}
	
	private void createHunter(int x, int y) {
		addCharacter(new Hunter(x, y, sleepTime));
	}
}
