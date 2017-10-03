package mvs;

public class Dog {
	private String name;
	private int age;

	public Dog(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Dog() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + "]";
	}

	public void biteSomeThing(Bite bite) {

		System.out.println("I bited " + bite);
		if (bite.isTasty()) {
			System.out.println("It is GOOD");
		} else {
			System.out.println("It is BAD");
		}
	}
}
