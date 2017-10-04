package mvs;

public class Person {

	private String lastName;

	public Person(String lastName) {
		super();
		this.lastName = lastName;
	}

	public Person() {
		super();
		lastName = "";
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return lastName;
	}	
	
}
