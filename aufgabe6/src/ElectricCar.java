
public class ElectricCar extends Car {
	private int usedPower;
	private String mode;
	
	public ElectricCar(int id) {
		super(id);
		usedPower = 0;
		mode = "Electric Car";
	}
	
	public int getUsedPower() {
		return usedPower;
	}
	
	public void increaseUsedPower(int used) {
		usedPower += Math.abs(used);
	}
	
	public String getMode() {
		return mode;
	}
}
