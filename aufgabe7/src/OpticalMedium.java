
public abstract class OpticalMedium extends DataMedium {

	public OpticalMedium(String name) {
		super(name);
	}

	public boolean insertIntoOpticalDrive(OpticalDrive<OptBD> od) {
		return od.getSlot().insert(OptBD bd);
	}
}
