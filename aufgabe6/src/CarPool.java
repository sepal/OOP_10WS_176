
public class CarPool {
	private String name;
	private SimpleMap cars;
	private int electric, fuel;
	
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
		if(car instanceof ElectricCar) {
			electric++;
		} else if(car instanceof FuelCar) {
			fuel++;
		}
		return cars.add(car.getId(), car);
	}
	
	public void removeCar(Car car) {
		if(car instanceof ElectricCar) {
			electric--;
		} else if(car instanceof FuelCar) {
			fuel--;
		}
		cars.remove(car.getId());
	}
	
	public SimpleMap getCars(String name) {
		return cars;
	}
	
	public String counter() {
		return "Electric Cars: "+electric+", Fuel Cars: "+fuel;
	}
}
