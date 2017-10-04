package mvs;

import java.util.ArrayDeque;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		ArrayDeque<Person> quePerson = new ArrayDeque<Person>();

		quePerson.offer(new Person("Sheldon"));
		quePerson.offer(new Person("Leonard"));
		quePerson.offer(new Person("Volovitc"));
		quePerson.offer(new Person("Kutrapalli"));
		quePerson.offer(new Person("Penny"));

		Random random = new Random();
		int countCola = random.nextInt(10);

		Person tempPerson = new Person();
		for (int i = 0; i < countCola; i++) {
			tempPerson = quePerson.poll();
			if (tempPerson != null) {
				quePerson.offer(tempPerson);
				quePerson.offer(tempPerson);
			}
		}

		System.out.println("After drinking " + countCola + " Cola");
		System.out.println("Queue:");
		System.out.println(quePerson);

	}

}
