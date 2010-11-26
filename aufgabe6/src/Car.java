
public abstract class Car {
	private int id, mileage;
	private Purpose role;
	
	public Car(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void increaseMilage(int distance) {
		mileage += Math.abs(distance);
	}
	
	public int getMilage() {
		return mileage;
	}
}
