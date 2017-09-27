package mvs;

public class Boat implements Runnable {

	private int cargoBoxes;
	private String boatName;
	private Port port;

	// Constructors
	public Boat() {
		super();
	}

	public Boat(int cargoBoxes, String boatName) {
		super();
		this.cargoBoxes = cargoBoxes;
		this.boatName = boatName;
	}

	// Getters & Setters
	public int getCargoBoxes() {
		return cargoBoxes;
	}

	public void setCargoBoxes(int cargoBoxes) {
		this.cargoBoxes = cargoBoxes;
	}

	public String getBoatName() {
		return boatName;
	}

	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}

	public void setPort(Port port) throws MyPortFullException {

		if (port.addBoat(this)) {
			this.port = port;
		} else {
			throw new MyPortFullException();
		}
	}

	private void tranzaction(int count) {
		try {
			Thread.sleep(5 * count);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public void unLoadCargoBox(int count, int docNumber) {
		if (this.cargoBoxes < count) {
			tranzaction(this.cargoBoxes);
			this.cargoBoxes = 0;
		} else {
			tranzaction(count);
			this.cargoBoxes -= count;
		}
	}

	public void unLoadCargoBox(int count, int docNumber, boolean displayMessage) {
		if (this.cargoBoxes < count) {
			tranzaction(this.cargoBoxes);
			this.cargoBoxes = 0;
			if (displayMessage) {
				System.out.println(
						this.boatName + " unloaded in doc#" + docNumber + " only " + count + " boxes. Boat is empty!");
			}
		} else {
			tranzaction(count);
			this.cargoBoxes -= count;
			if (displayMessage) {
				System.out.println(this.boatName + " unloaded in doc#" + docNumber + " " + count + " boxes. Boat has "
						+ getCargoBoxes());
			}
		}
	}

	@Override
	public String toString() {
		return "Boat [cargoBoxes=" + cargoBoxes + ", boatName=" + boatName + "]";
	}

	@Override
	public void run() {
		port.unLoadCargo(this);
	}

}
