
public class FuelCar extends Car {
	private int usedFuel;
	
	public FuelCar(int id) {
		super(id);
		usedFuel = 0;
	}

	public int getUsedFuel() {
		return usedFuel;
	}
	
	public void increaseUsedFuel(int used) {
		usedFuel += Math.abs(used);
	}
}
