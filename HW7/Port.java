package mvs;

public class Port {

	private Boat[] arrBoats = new Boat[5];
	private boolean isFullDocOne = false;
	private boolean isFullDocTwo = false;

	// Constructors
	public Port() {
		super();
	}

	public boolean addBoat(Boat boat) {

		for (int i = 0; i < arrBoats.length; i += 1) {
			if (arrBoats[i] == null) {
				arrBoats[i] = boat;
				// startUnloading(arrBoats[i]);
				return true;
			}
		}
		return false;
	}

	public void startUnloading(Boat boat, int docNumber) {

		switch (docNumber) {
		case 1:
			isFullDocOne = true;
			break;
		case 2:
			isFullDocTwo = true;
			break;
		}
		for (; boat.getCargoBoxes() > 0;) {
			boat.unLoadCargoBox(1, docNumber, true);
		}
		switch (docNumber) {
		case 1:
			isFullDocOne = false;
			break;
		case 2:
			isFullDocTwo = false;
			break;
		}

	}

	public void unLoadCargo(Boat boat) {

		while (boat.getCargoBoxes() > 0) {
			if (!isFullDocOne) {
				startUnloading(boat, 1);
			} else if (!isFullDocTwo) {
				startUnloading(boat, 2);
			}
		}

	}

}
