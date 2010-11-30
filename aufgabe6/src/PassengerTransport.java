
public class PassengerTransport implements Purpose {
	private int maxPassenger = 0;
	public PassengerTransport (int maxPassenger) {
		this.setMaxPassenger(maxPassenger);
	}
	public void setMaxPassenger(int maxPassenger) {
		this.maxPassenger = maxPassenger;
	}
	public int getMaxPassenger() {
		return maxPassenger;
	}
}
