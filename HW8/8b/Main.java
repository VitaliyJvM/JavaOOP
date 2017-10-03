package mvs;

public class Main {

	public static void main(String[] args) {

		BlackList bl = new BlackList();

		Cat catOne = new Cat(3, "Vaska", "White");
		Dog dog = new Dog("Bobik", 4);

		bl.add(catOne.getClass());
		System.out.println(bl);

		bl.add(dog.getClass());
		System.out.println(bl);

		Ball ballOne = new Ball(10, "Blue");
		Ball ballTwo = new Ball(4, "Red");
		Ball ballThree = new Ball(7, "Green");

		// System.out.println(ball.toString() + " belong " + bl.check(ball));
		// System.out.println(dog.toString() + " belong " + bl.check(dog));

		Stack stackOne = new Stack(8, bl);
		stackOne.add(ballOne);
		System.out.println(stackOne);

		stackOne.add(dog);
		System.out.println(stackOne);

		stackOne.add(ballTwo);
		stackOne.add(ballThree);
		System.out.println(stackOne);

		System.out.println("------------");
		System.out.println(stackOne.get());

		for (int i = 0; i < 5; i++) {
			System.out.println(stackOne.getOut());
		}

	}

}
