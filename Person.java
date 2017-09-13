package oopLes3_hw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

	private boolean sex; // false-female, true-male
	private String firstName;
	private String lastName;
	private Date birthDate;
	private long tin;

	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

	// CONSTRUCTORS
	public Person(String firstName, String lastName, boolean sex, String sBirthDate, long tin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.tin = tin;

		try {
			this.birthDate = dateformat.parse(sBirthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Person(String firstName, String lastName, boolean sex, Date birthDate, long tin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.birthDate = birthDate;
		this.tin = tin;
	}

	public Person() {
		super();
		this.firstName = "";
		this.lastName = "";
	}

	// GET & SET
	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setBirthDate(String sBirthDate) {
		try {
			this.birthDate = dateformat.parse(sBirthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public long getTin() {
		return tin;
	}

	public void setTin(long tin) {
		this.tin = tin;
	}

	@Override
	public String toString() {
		return "Person: [" + getFirstName() + " " + getLastName() + ", sex = " + ((isSex()) ? "male" : "female")
				+ ", TIN = " + getTin() + ", date of birth: " + getBirthDate() + "]";
	}
}
