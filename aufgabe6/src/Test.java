
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("***New CarPool***");
		CarPool cp1 = new CarPool("Fuhrpark1");
		System.out.println("CarPool "+cp1.getName()+" created.\n");
		System.out.println("***New Cars***");
		ElectricCar ec1 = new ElectricCar(01);
		ElectricCar ec2 = new ElectricCar(02);
		ElectricCar ec3 = new ElectricCar(03);
		FuelCar fc1 = new FuelCar(04);
		FuelCar fc2 = new FuelCar(05);
		FuelCar fc3 = new FuelCar(06);
		
		//ValueIterator vi = new ValueIterator(cp1.getCars("Fuhrpark1"));
		
		ec1.increaseMileage(3000);
		ec1.increaseUsedPower(130);
		ec2.increaseMileage(6000);
		ec2.increaseUsedPower(460);
		ec3.increaseMileage(12000);
		ec3.increaseUsedPower(8000);
		
		fc1.increaseMileage(10000);
		fc1.increaseUsedFuel(20);
		fc2.increaseMileage(15000);
		fc2.increaseUsedFuel(30);
		fc3.increaseMileage(20000);
		fc3.increaseUsedFuel(40);
		
		cp1.addCar(ec1);
		cp1.addCar(ec2);
		cp1.addCar(ec3);
		cp1.addCar(fc1);
		cp1.addCar(fc2);
		cp1.addCar(fc3);
		
		System.out.println("Car List of "+cp1.getName()+":");
		System.out.println(ec1.getMode()+" "+ec1.getId()+", "+ec1.getMileage()+" miles, "+ec1.getUsedPower()+"kW used Power");
		System.out.println(ec2.getMode()+" "+ec2.getId()+", "+ec2.getMileage()+" miles, "+ec2.getUsedPower()+"kW used Power");
		System.out.println(ec3.getMode()+" "+ec3.getId()+", "+ec3.getMileage()+" miles, "+ec3.getUsedPower()+"kW used Power");
		System.out.println(fc1.getMode()+" "+fc1.getId()+", "+fc1.getMileage()+" miles, "+fc1.getUsedFuel()+"L used Fuel");
		System.out.println(fc2.getMode()+" "+fc2.getId()+", "+fc2.getMileage()+" miles, "+fc2.getUsedFuel()+"L used Fuel");
		System.out.println(fc3.getMode()+" "+fc3.getId()+", "+fc3.getMileage()+" miles, "+fc3.getUsedFuel()+"L used Fuel");
		System.out.println();
		System.out.println(cp1.counter());
		cp1.removeCar(fc3);
		System.out.println();
		
		System.out.println("Car "+fc3.getId()+" removed.");
		System.out.println(cp1.counter());
		
	}

}
