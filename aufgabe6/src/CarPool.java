
public class CarPool {
	enum Role {
		ALL,
		CARGOTRANSPORT,
		PASSENGERTRANSPORT,
	}
	private String name;
	private SimpleMap cars;
	
	/*
	 * postcondition: after creating a new CarPool it is ready to be filled with cars
	 */
	public CarPool(String name) {
		this.name = name;
		cars = new SimpleMap();
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
	
	public SimpleMap getCars() {
		return cars;
	}
	
	private float getAverageOf(Car source) {
		SimpleMap.ValueIterator it = cars.getIteratorOverValues();
		int volume = 0;
		int count = 0;
		while(it.hasNext()) {
			Car c = (Car)it.next().getValue();

			if (c.getClass().equals(source.getClass())) {
				if (source.getPurpose() == null || c.getPurpose().getClass().equals(source.getPurpose().getClass())) {
					volume += c.getConsumption();
					count++;
				}
			}
		}
		
		return (count>0) ? (float)volume / (float)count : 0;
	}
	
	public float getAverageElectricUsage(Role r) {
		if (r == Role.ALL){
			return getAverageOf(new ElectricCar(0, null));
		} else if (r == Role.CARGOTRANSPORT) {
			return getAverageOf(new ElectricCar(0, new CargoTransport(0, 0)));
		} else {
			return getAverageOf(new ElectricCar(0, new PassengerTransport(0)));
		}
	}
	
	public float getAverageFuelUsage(Role r) {
		if (r == Role.ALL){
			return getAverageOf(new FuelCar(0, null));
		} else if (r == Role.CARGOTRANSPORT) {
			return getAverageOf(new FuelCar(0, new CargoTransport(0, 0)));
		} else {
			return getAverageOf(new FuelCar(0, new PassengerTransport(0)));
		}
	}
}
