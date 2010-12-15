
public abstract class Car {
	private int id, mileage;
	private Purpose role;
	private int consumption;
	
	
	/*
	 * pre-condition: id must not be null
	 */
	public Car(int id, Purpose p) {
		this.id = id;
		this.setPurpose(p);
	}
	
	/*
	 * Getter and Setter for attributes
	 */
	
	public int getId() {
		return id;
	}
	
	public void increaseMileage(int distance) {
		mileage += Math.abs(distance);
	}
	
	public int getMileage() {
		return mileage;
	}
	
	public String toString() {
		return "Car "+id;
	}

	public void setPurpose(Purpose role) {
		this.role = role;
	}

	public Purpose getPurpose() {
		return role;
	}
	
	public int getConsumption() {
		return consumption;
	}
	
	public void increaseConsumption(int volume) {
		consumption += Math.abs(volume);
	}
}
