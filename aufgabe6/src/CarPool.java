
public class CarPool {
	public enum CarType {
		ELECTRIC,
		FUEL,
		ALL,
	}

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
	
	/*
	 * precondition: id has to be a valid car id identifying a car in this car pool
	 * postcondition: returns the car identified by id, or null if no such car exists
	 */
	public Car getCar(int id) {
		Object tmp = cars.get(id);
		if (tmp != null && tmp instanceof Car) {
			return (Car) tmp;
		} else {
			return null;
		}
	}
	
	/*
	 * precondition: car must not be null, this car or another car with the same id must not
	 *   be already member of this car pool
	 * postcondition: if car was not null and no car with the same id was yet in the pool,
	 *   car has been added to the pool
	 */
	public boolean addCar(Car car) {
		if (car == null) {
			return false;
		} else {
			return cars.add(car.getId(), car);
		}
	}
	
	/*
	 * postcondition: if car was a member of this car pool, after calling this method it won't be any longer
	 */
	public void removeCar(Car car) {
		cars.remove(car.getId());
	}
	
	public SimpleMap getCars() {
		return cars;
	}

	/*
	 * (pre-condition) Param source shoud be instance of ElectricCar or FuelCar
	 * (post-condition) If no cars were found, the function will return 0;
	 */
	private float getAverageConsumptionOf(Car source) {
		if (source == null) return 0;
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
			return getAverageConsumptionOf(new ElectricCar(0, null));
		} else if (r == Role.CARGOTRANSPORT) {
			return getAverageConsumptionOf(new ElectricCar(0, new CargoTransport(0, 0)));
		} else {
			return getAverageConsumptionOf(new ElectricCar(0, new PassengerTransport(0)));
		}
	}
	
	public float getAverageFuelUsage(Role r) {
		if (r == Role.ALL){
			return getAverageConsumptionOf(new FuelCar(0, null));
		} else if (r == Role.CARGOTRANSPORT) {
			return getAverageConsumptionOf(new FuelCar(0, new CargoTransport(0, 0)));
		} else {
			return getAverageConsumptionOf(new FuelCar(0, new PassengerTransport(0)));
		}
	}
	
	/**
	 * 
	 * @param source Dummy class to determine which type of car should be loaded. If null all cars are included.
	 * @param r What statistics do we want.
	 * @return 
	 */
	/*
	 * (pre-condition) Param r should not be all.
	 * (post-condition) If no cars were found, the function will return 0;
	 */
	private float getAverageRoleStatesOf(Car source, Role r) {
		SimpleMap.ValueIterator it = cars.getIteratorOverValues();
		int volume = 0;
		int count = 0;
		while(it.hasNext()) {
			Car c = (Car)it.next().getValue();

			if (source == null || c.getClass().equals(source.getClass())) {
				if (r == Role.PASSENGERTRANSPORT && c.getPurpose().getClass().equals(PassengerTransport.class)) {
					PassengerTransport pt = (PassengerTransport)c.getPurpose();
					volume += pt.getMaxPassenger();
					count++;
				} else if (r == Role.CARGOTRANSPORT && c.getPurpose().getClass().equals(CargoTransport.class)) {
					CargoTransport ct = (CargoTransport)c.getPurpose();
					volume += ct.getCargoArea();
					count++;
				}
			}
		}
		
		return (count>0) ? (float)volume / (float)count : 0;
	}
	
	public float getAverageSeats(CarType ct) {
		if (ct == CarType.ALL) {
			return getAverageRoleStatesOf(null, Role.PASSENGERTRANSPORT);
		} else if(ct == CarType.ELECTRIC) {
			return getAverageRoleStatesOf(new ElectricCar(0, null), Role.PASSENGERTRANSPORT);
		} else {
			return getAverageRoleStatesOf(new FuelCar(0, null), Role.PASSENGERTRANSPORT);
		}
	}
	
	public float getAverageCargoArea(CarType ct) {
		if (ct == CarType.ALL) {
			return getAverageRoleStatesOf(null, Role.CARGOTRANSPORT);
		} else if(ct == CarType.ELECTRIC) {
			return getAverageRoleStatesOf(new ElectricCar(0, null), Role.CARGOTRANSPORT);
		} else {
			return getAverageRoleStatesOf(new FuelCar(0, null), Role.CARGOTRANSPORT);
		}
	}
}
