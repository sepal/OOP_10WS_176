
public class CargoTransport implements Purpose {
	private int cargoArea;
	private int maxPayload;

	public CargoTransport(int cargoArea, int maxPayload) {
		this.setCargoArea(cargoArea);
		this.setMaxPayload(maxPayload);
	}

	public void setCargoArea(int cargoArea) {
		this.cargoArea = cargoArea;
	}

	public int getCargoArea() {
		return cargoArea;
	}

	public void setMaxPayload(int maxPayload) {
		this.maxPayload = maxPayload;
	}

	public int getMaxPayload() {
		return maxPayload;
	}
	
}
