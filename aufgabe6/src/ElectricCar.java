
public class ElectricCar extends Car {
	private int usedPower;
	
	public ElectricCar(int id) {
		super(id);
		usedPower = 0;
	}
	
	public int getUsedPower() {
		return usedPower;
	}
	
	public void increaseUsedPower(int used) {
		usedPower += Math.abs(used);
	}
}
