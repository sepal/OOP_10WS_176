
public class CarPool {
	private String name;
	private SimpleMap1 cars;
	private int electric, fuel;
	
	public CarPool(String name) {
		this.name = name;
		cars = new SimpleMap1();
	}
	
	public String getName() {
		return name;
	}
	
	public Car getCar(int id) {
		return (Car) cars.get(id);
	}
	
	public boolean addCar(Car car) {
		return cars.add(car.getId(), car);
	}
	
	public void removeCar(Car car) {
		cars.remove(car.getId());
	}
	
	public SimpleMap1 getCars() {
		return cars;
	}
}
