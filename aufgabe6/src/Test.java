
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CarPoolMap pools = new CarPoolMap();
		System.out.println("***New CarPools***");
		CarPool cp1 = new CarPool("Fuhrpark1");
		pools.add(cp1.getName(), cp1);
		System.out.println("CarPool "+cp1.getName()+" created.\n");
		
		CarPool cp2 = new CarPool("Fuhrpark2");
		pools.add(cp2.getName(), cp2);
		System.out.println("CarPool "+cp2.getName()+" created.\n");
		
		CarPool cp3 = new CarPool("Fuhrpark3");
		pools.add(cp3.getName(), cp3);
		System.out.println("CarPool "+cp3.getName()+" created.");

		ElectricCar ec1 = new ElectricCar(01, new PassengerTransport(10));
		ElectricCar ec2 = new ElectricCar(02, new PassengerTransport(2));
		ElectricCar ec3 = new ElectricCar(03, new CargoTransport(10, 100));
		FuelCar fc1 = new FuelCar(04, new CargoTransport(10, 100));
		FuelCar fc2 = new FuelCar(05, new CargoTransport(2, 50));
		FuelCar fc3 = new FuelCar(06, new PassengerTransport(5));
			
		pools.get("Fuhrpark1").addCar(ec1);
		pools.get("Fuhrpark1").addCar(ec1);
		pools.get("Fuhrpark1").addCar(ec2);
		pools.get("Fuhrpark1").addCar(ec3);
		pools.get("Fuhrpark1").addCar(fc1);
		pools.get("Fuhrpark1").addCar(fc2);
		pools.get("Fuhrpark1").addCar(fc3);
		
		pools.get("Fuhrpark1").getCar(01).increaseMileage(3000);
		pools.get("Fuhrpark1").getCar(01).increaseConsumption(130);
		pools.get("Fuhrpark1").getCar(02).increaseMileage(6000);
		pools.get("Fuhrpark1").getCar(02).increaseConsumption(460);
		pools.get("Fuhrpark1").getCar(03).increaseMileage(12000);
		pools.get("Fuhrpark1").getCar(03).increaseConsumption(8000);
		
		pools.get("Fuhrpark1").getCar(04).increaseMileage(10000);
		pools.get("Fuhrpark1").getCar(04).increaseConsumption(20);
		pools.get("Fuhrpark1").getCar(05).increaseMileage(15000);
		pools.get("Fuhrpark1").getCar(05).increaseConsumption(30);
		pools.get("Fuhrpark1").getCar(06).increaseMileage(20000);
		pools.get("Fuhrpark1").getCar(06).increaseConsumption(40);
		
		System.out.println("***"+pools.get("Fuhrpark1").getName()+" "+pools.get("Fuhrpark1").getCars().toString());
		System.out.println("Average Usage of Electric Cars: "+pools.get("Fuhrpark1").getAverageElectricUsage(CarPool.Role.ALL)+"kW");
		System.out.println("Average Usage of Electric Cars for CargoTransport: "+pools.get("Fuhrpark1").getAverageElectricUsage(CarPool.Role.CARGOTRANSPORT)+"kW");
		System.out.println("Average Usage of Electric Cars for PassengerTransport: "+pools.get("Fuhrpark1").getAverageElectricUsage(CarPool.Role.PASSENGERTRANSPORT)+"kW");
		System.out.println("Average Usage of Fuel Cars: "+pools.get("Fuhrpark1").getAverageFuelUsage(CarPool.Role.ALL)+"L");
		System.out.println("Average Usage of Fuel Cars for CargoTransport: "+pools.get("Fuhrpark1").getAverageFuelUsage(CarPool.Role.CARGOTRANSPORT)+"L");
		System.out.println("Average Usage of Fuel Cars for PassengerTransport: "+pools.get("Fuhrpark1").getAverageFuelUsage(CarPool.Role.PASSENGERTRANSPORT)+"L");
		
		pools.get("Fuhrpark1").getCar(04).increaseMileage(200);
		pools.get("Fuhrpark1").getCar(04).increaseConsumption(20);
		System.out.println(pools.get("Fuhrpark1").getCar(04).getClass().getName()+" "+pools.get("Fuhrpark1").getCar(04).getId()+" used, new mileage: "+pools.get("Fuhrpark1").getCar(04).getMileage()+", current Fuel level: "+pools.get("Fuhrpark1").getCar(04).getConsumption());
		ec2.increaseMileage(500);
		ec2.increaseConsumption(50);
		System.out.println(ec2.getClass().getName()+" "+ec2.getId()+" used, new mileage: "+ec2.getMileage()+", current Electric level: "+ec2.getConsumption());
		System.out.println();	
		
		pools.get("Fuhrpark1").removeCar(fc2);
		System.out.println("\n***"+fc2.getClass().getName()+" "+fc2.getId()+" removed.***");
		System.out.println("\n***"+pools.get("Fuhrpark1").getName()+" new "+pools.get("Fuhrpark1").getCars().toString()+"\n");
		
		//---------------------------CAR POOL 2-----------------------------
		System.out.println("---------------------------------------------------------\n");

		ElectricCar ec4 = new ElectricCar(44, new PassengerTransport(5));
		ElectricCar ec5 = new ElectricCar(51, new PassengerTransport(4));
		ElectricCar ec6 = new ElectricCar(60, new PassengerTransport(9));
		FuelCar fc4 = new FuelCar(70,new CargoTransport(10, 100));
		FuelCar fc5 = new FuelCar(80,new CargoTransport(20, 500));
		FuelCar fc6 = new FuelCar(90, new CargoTransport(10, 300));
		FuelCar fc7 = new FuelCar(101, new CargoTransport(20, 400));
		FuelCar fc8 = new FuelCar(120, new PassengerTransport(2));
		
		pools.get("Fuhrpark2").addCar(ec4);
		pools.get("Fuhrpark2").addCar(ec5);
		pools.get("Fuhrpark2").addCar(ec6);
		pools.get("Fuhrpark2").addCar(fc4);
		pools.get("Fuhrpark2").addCar(fc5);
		pools.get("Fuhrpark2").addCar(fc6);
		pools.get("Fuhrpark2").addCar(fc7);
		pools.get("Fuhrpark2").addCar(fc8);
		
		pools.get("Fuhrpark2").getCar(44).increaseMileage(10000);
		pools.get("Fuhrpark2").getCar(44).increaseConsumption(300);
		pools.get("Fuhrpark2").getCar(51).increaseMileage(6000);
		pools.get("Fuhrpark2").getCar(51).increaseConsumption(300);
		pools.get("Fuhrpark2").getCar(60).increaseMileage(8000);
		pools.get("Fuhrpark2").getCar(60).increaseConsumption(300);
		
		pools.get("Fuhrpark2").getCar(70).increaseMileage(12000);
		pools.get("Fuhrpark2").getCar(70).increaseConsumption(60);
		pools.get("Fuhrpark2").getCar(80).increaseMileage(30000);
		pools.get("Fuhrpark2").getCar(80).increaseConsumption(100);
		pools.get("Fuhrpark2").getCar(90).increaseMileage(20000);
		pools.get("Fuhrpark2").getCar(90).increaseConsumption(100);
		pools.get("Fuhrpark2").getCar(101).increaseMileage(2000);
		pools.get("Fuhrpark2").getCar(101).increaseConsumption(100);
		pools.get("Fuhrpark2").getCar(120).increaseMileage(1000);
		pools.get("Fuhrpark2").getCar(120).increaseConsumption(100);

		System.out.println("***"+pools.get("Fuhrpark2").getName()+" "+pools.get("Fuhrpark2").getCars().toString());
		System.out.println("Average Usage of Electric Cars: "+pools.get("Fuhrpark2").getAverageElectricUsage(CarPool.Role.ALL)+"kW");
		System.out.println("Average Usage of Electric Cars for CargoTransport: "+pools.get("Fuhrpark2").getAverageElectricUsage(CarPool.Role.CARGOTRANSPORT)+"kW");
		System.out.println("Average Usage of Electric Cars for PassengerTransport: "+pools.get("Fuhrpark2").getAverageElectricUsage(CarPool.Role.PASSENGERTRANSPORT)+"kW");
		System.out.println("Average Usage of Fuel Cars: "+pools.get("Fuhrpark2").getAverageFuelUsage(CarPool.Role.ALL)+"L");
		System.out.println("Average Usage of Fuel Cars for CargoTransport: "+pools.get("Fuhrpark2").getAverageFuelUsage(CarPool.Role.CARGOTRANSPORT)+"L");
		System.out.println("Average Usage of Fuel Cars for PassengerTransport: "+pools.get("Fuhrpark2").getAverageFuelUsage(CarPool.Role.PASSENGERTRANSPORT)+"L");

		pools.get("Fuhrpark2").getCar(90).increaseMileage(500);
		pools.get("Fuhrpark2").getCar(90).increaseConsumption(40);
		System.out.println("\n"+pools.get("Fuhrpark2").getCar(90).getClass().getName()+" "+pools.get("Fuhrpark2").getCar(90).getId()+" used, new mileage: "+pools.get("Fuhrpark2").getCar(90).getMileage()+", current Fuel level: "+pools.get("Fuhrpark2").getCar(90).getConsumption());
		pools.get("Fuhrpark2").getCar(51).increaseMileage(800);
		pools.get("Fuhrpark2").getCar(51).increaseConsumption(90);
		System.out.println(pools.get("Fuhrpark2").getCar(51).getClass().getName()+" "+pools.get("Fuhrpark2").getCar(51).getId()+" used, new mileage: "+pools.get("Fuhrpark2").getCar(51).getMileage()+", current Electric level: "+pools.get("Fuhrpark2").getCar(51).getConsumption());
		System.out.println();

		pools.get("Fuhrpark2").removeCar(fc7);
		
		System.out.println(fc7.getClass().getName()+" "+fc7.getId()+" removed.\n");
		System.out.println("***"+pools.get("Fuhrpark2").getName()+" new "+pools.get("Fuhrpark2").getCars().toString());
		
		//-------------------------------CAR POOL 3----------------------------------
		System.out.println("---------------------------------------------------------\n");

		ElectricCar ec7 = new ElectricCar(17, new PassengerTransport(4));
		ElectricCar ec8 = new ElectricCar(18, new PassengerTransport(4));
		ElectricCar ec9 = new ElectricCar(19, new PassengerTransport(2));
		ElectricCar ec10 = new ElectricCar(20, new CargoTransport(20, 400));
		ElectricCar ec11 = new ElectricCar(30, new CargoTransport(50, 700));
		ElectricCar ec12 = new ElectricCar(31, new CargoTransport(40, 500));
		
		FuelCar fc10 = new FuelCar(41, new CargoTransport(10, 100));
		FuelCar fc11 = new FuelCar(42, new PassengerTransport(2));
		FuelCar fc12 = new FuelCar(43, new PassengerTransport(5));
		
		pools.get("Fuhrpark3").addCar(ec7);
		pools.get("Fuhrpark3").addCar(ec8);
		pools.get("Fuhrpark3").addCar(ec9);
		pools.get("Fuhrpark3").addCar(ec10);
		pools.get("Fuhrpark3").addCar(ec11);
		pools.get("Fuhrpark3").addCar(ec12);
		pools.get("Fuhrpark3").addCar(fc10);
		pools.get("Fuhrpark3").addCar(fc11);
		pools.get("Fuhrpark3").addCar(fc12);
		
		pools.get("Fuhrpark3").getCar(17).increaseMileage(1000);
		pools.get("Fuhrpark3").getCar(17).increaseConsumption(300);
		pools.get("Fuhrpark3").getCar(18).increaseMileage(4500);
		pools.get("Fuhrpark3").getCar(19).increaseMileage(3600);
		pools.get("Fuhrpark3").getCar(19).increaseConsumption(300);
		pools.get("Fuhrpark3").getCar(20).increaseMileage(10200);
		pools.get("Fuhrpark3").getCar(20).increaseConsumption(600);
		pools.get("Fuhrpark3").getCar(30).increaseMileage(11500);
		pools.get("Fuhrpark3").getCar(30).increaseConsumption(400);
		pools.get("Fuhrpark3").getCar(31).increaseMileage(7000);
		pools.get("Fuhrpark3").getCar(31).increaseConsumption(300);
		pools.get("Fuhrpark3").getCar(41).increaseMileage(12000);
		pools.get("Fuhrpark3").getCar(41).increaseConsumption(60);
		pools.get("Fuhrpark3").getCar(42).increaseMileage(30000);
		pools.get("Fuhrpark3").getCar(42).increaseConsumption(100);
		pools.get("Fuhrpark3").getCar(43).increaseMileage(20000);
		pools.get("Fuhrpark3").getCar(43).increaseConsumption(100);
		
		System.out.println("\n***"+pools.get("Fuhrpark3").getName()+" "+pools.get("Fuhrpark3").getCars().toString());
		System.out.println("Average Usage of Electric Cars: "+pools.get("Fuhrpark3").getAverageElectricUsage(CarPool.Role.ALL)+"kW");
		System.out.println("Average Usage of Electric Cars for CargoTransport: "+pools.get("Fuhrpark3").getAverageElectricUsage(CarPool.Role.CARGOTRANSPORT)+"kW");
		System.out.println("Average Usage of Electric Cars for PassengerTransport: "+pools.get("Fuhrpark3").getAverageElectricUsage(CarPool.Role.PASSENGERTRANSPORT)+"kW");
		System.out.println("Average Usage of Fuel Cars: "+pools.get("Fuhrpark3").getAverageFuelUsage(CarPool.Role.ALL)+"L");
		System.out.println("Average Usage of Fuel Cars for CargoTransport: "+pools.get("Fuhrpark3").getAverageFuelUsage(CarPool.Role.CARGOTRANSPORT)+"L");
		System.out.println("Average Usage of Fuel Cars for PassengerTransport: "+pools.get("Fuhrpark3").getAverageFuelUsage(CarPool.Role.PASSENGERTRANSPORT)+"L");
		System.out.println("Average seats for electric cars: "+pools.get("Fuhrpark3").getAverageSeats(CarPool.CarType.ELECTRIC));
		System.out.println("Average cargo area for electric cars: "+pools.get("Fuhrpark3").getAverageCargoArea(CarPool.CarType.ELECTRIC));
		System.out.println("Average seats for fuel cars: "+pools.get("Fuhrpark3").getAverageSeats(CarPool.CarType.FUEL));
		System.out.println("Average cargo area for fuel cars: "+pools.get("Fuhrpark3").getAverageCargoArea(CarPool.CarType.FUEL));
	}

}
