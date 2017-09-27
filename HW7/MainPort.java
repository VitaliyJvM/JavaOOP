package mvs;

public class MainPort {

	public static void main(String[] args) {

		Boat boatOne = new Boat(10, "Admiral");
		Boat boatTwo = new Boat(10, "Nemo");
		Boat boatThree = new Boat(10, "Bono");
		
		Port portOne = new Port();

//		boatOne.unLoadCargoBox(3, true);
//		boatTwo.unLoadCargoBox(14, true);

		try {
			boatOne.setPort(portOne);
			boatTwo.setPort(portOne);
			boatThree.setPort(portOne);
		} catch (MyPortFullException e) {
			e.printStackTrace();
		}
		
		Thread boatThOne = new Thread(boatOne);
		Thread boatThTwo = new Thread(boatTwo);
		Thread boatThThree = new Thread(boatThree);
		boatThOne.start();
		boatThTwo.start();
		boatThThree.start();

	}

}
