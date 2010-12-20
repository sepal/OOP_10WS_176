
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
	
	private List <Integer> characters;
	
	public Game(int width, int height) {
		lab = new Labyrinth(width, height);
		characters = Collections.synchronizedList(new ArrayList<Integer>());
	}
}
