
public abstract class Character implements Runnable {

	private int positionx;
	private int positiony;
	private int treasure;
	private int time;
	private boolean running =  true;

	public Character(int time, int x, int y) {
		this.time = time;
		this.positionx = x;
		this.positiony = y;
		this.treasure = 0;
		
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(this.time);
			while (this.isRunning()) {//(brett.getController().isRunning() && this.isRunning()) {
				this.doSomething();
				Thread.sleep(this.time);
			}
			//brett.getController().elementAbmelden(this);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

	protected abstract void doSomething();
	
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