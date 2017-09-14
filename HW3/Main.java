package mvs;

//import java.util.Date;

public class Main {

	public static void main(String[] args) throws MyArrayFullException {

		Person personOne = new Person();
		personOne.setLastName("Davidson");
		personOne.setFirstName("Mike");
		personOne.setSex(true);
		System.out.println(personOne);

		Person personTwo;
		personTwo = new Person("John", "Douwn", true, "17/07/1989", 2834458750L);
		System.out.println(personTwo);

		Person personThree;
		personThree = new Person("Karla", "Volkova", false, "24/02/1973", 2434458999L);
		System.out.println(personThree);

		Student studentOne = new Student(personOne, "01/09/2008", null, "");
		System.out.println(studentOne);

		Student studentTwo = new Student(personTwo, "01/09/2012", null, "G12");
		System.out.println(studentTwo);

		Student studentThree = new Student(personThree, "15/09/2014", null, "G12");
		System.out.println(studentThree);

		Group groupOne = new Group("G12399");
		groupOne.addStudent(studentThree);
		groupOne.addStudent(studentTwo);
		groupOne.addStudent(studentOne);
		groupOne.addStudent(studentTwo);

		System.out.println(groupOne);

		groupOne.deleteStudent("Smith");
		System.out.println(groupOne);

		try {
			for (int i = 0; i < 10; i++) {
				groupOne.addStudent(studentTwo);
			}
		} catch (MyArrayFullException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(groupOne);

		groupOne.deleteStudent("Douwn");
		System.out.println(groupOne);

	}

}
