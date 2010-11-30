
public class FuelCar extends Car {
	private int usedFuel;
	private String mode;
	
	public FuelCar(int id) {
		super(id);
		usedFuel = 0;
		mode = "Fuel Car";
	}

	public int getUsedFuel() {
		return usedFuel;
	}
	
	public void increaseUsedFuel(int used) {
		usedFuel += Math.abs(used);
	}
	
	public String getMode() {
		return mode;
	}
}
