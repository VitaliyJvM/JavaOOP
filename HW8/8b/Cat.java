package mvs;

public class Cat implements Bite, Comparable {
	private int age;
	private String name;
	private String color;

	public Cat(int age, String name, String color) {
		super();
		this.age = age;
		this.name = name;
		this.color = color;
	}

	public Cat() {
		super();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Cat [age=" + age + ", name=" + name + ", color=" + color + "]";
	}

	@Override
	public boolean isTasty() {
		return this.age < 4;
	}

	@Override
	public int compareTo(Object o) {
		Cat anotherCat = (Cat) o;

		if (this.age > anotherCat.age) {
			return 1;
		}
		if (this.age < anotherCat.age) {
			return -1;
		}
		return 0;
	}

}
