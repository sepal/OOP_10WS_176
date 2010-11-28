
public class CarPool {
	String name;
	SimpleMap cars;
	
	public CarPool(String name) {
		this.name = name;
		cars = new SimpleMap();
	}
	
	public String getName() {
		return name;
	}
	
	public Car getCar(int id) {
		Object tmp = cars.get(id);
		if (tmp instanceof Car) {
			return (Car) tmp;
		} else {
			return null;
		}
	}
	
	public boolean addCar(Car car) {
		return cars.add(car.getId(), car);
	}
	
	public void removeCar(Car car) {
		cars.remove(car.getId());
	}
}
