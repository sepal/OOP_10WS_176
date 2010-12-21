import java.util.ArrayList;



public abstract class Character implements Runnable {

	private int positionx;
	private int positiony;
	private int treasure;
	private int time;
	private Game game;
	private boolean running;
	ArrayList<Field> way;

	public Character(int time, int x, int y, Game game) {
		this.time = time;
		this.positionx = x;
		this.positiony = y;		
		this.game = game;
		way = new ArrayList<Field>();
	}
	
	@Override
	public void run() {
		this.running = true;
		try {
			while (this.isRunning() && game.getState() == Game.State.RUNNING) {
				this.walk();
				Thread.sleep(this.time);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

	protected void walk(){
		Field f = getField(positionx, positiony);
		//fuer hunter, treasure einsammeln
		/*if(f.getTreasure() > 0) {
			hunter.setTreasure(hunter.getTreasure()+f.getTreasure());
			f.resetTreasure();
		}*/
		
		boolean[] walls = f.getWall();
		if(walls[0] == false && positiony-- >= 0) {
			Field next = getField(positionx, positiony--);
			way.add(next);
		} else if(walls[1] == false && positionx++ < game.getLabyrith().getWidth()) {
			Field next = getField(positionx++, positiony);
			way.add(next);
		} else if(walls[3] == false && positionx-- >= 0) {
			Field next = getField(positionx--, positiony);
			way.add(next);
		} else if(walls[2] == false && positiony++ < game.getLabyrith().getHeight()) {
			Field next = getField(positionx, positiony++);
			way.add(next);
		}
		if(walls[1] == false && positionx == game.getLabyrith().getWidth()-1) {//only for hunter
			System.out.println("WIN");
		}
		
		
//		int counterwalls = 0;
//		for(boolean b : walls) {
//			if(b == true) {
//				counterwalls++;
//			}
//		}
		
	}
	
	public Field getField(int x, int y) {
		return game.getLabyrith().getField(x, y);
	}
	
	public void stop() {
		this.running = false;
	}
	
	public int getPositionx() {
		return positionx;
	}

	public void setPositionx(int positionx) {
		this.positionx = positionx;
	}

	public int getPositiony() {
		return positiony;
	}

	public void setPositiony(int positiony) {
		this.positiony = positiony;
	}

	public int getTreasure() {
		return treasure;
	}

	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
