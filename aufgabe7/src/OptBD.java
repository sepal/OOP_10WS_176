
public class OptBD extends OpticalMedium {

	/**
	 *(precondition) name must not be null
	 *(postcondition) creates object as opticalmedium 
	 */
	public OptBD(String name) {
		super(name);
	}
	@Override
	public boolean insertIntoOpticalDrive(OpticalDrive<OptBD> od) {
		return od.getSlot().insert(this);
	}

	@Override
	public boolean insertIntoCardReader(CardReader cr) {
		// TODO Auto-generated method stub
		return false;
	}
}
