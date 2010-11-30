
public abstract class Car {
	private int id, mileage;
	private Purpose role;
	private String mode;
	
	public Car(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void increaseMileage(int distance) {
		mileage += Math.abs(distance);
	}
	
	public int getMileage() {
		return mileage;
	}
	
	public String getMode() {
		return mode;
	}
}
