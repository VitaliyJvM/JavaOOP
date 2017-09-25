package mvs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
//			e.printStackTrace();
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

	public String getBirthDateToString() {
		if (birthDate==null) return "";
		return dateformat.format(birthDate);
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

	public Integer getAge()
	{
	    Calendar dob = Calendar.getInstance();
	    Calendar today = Calendar.getInstance();
	 
	    try {
	    	dob.setTime(this.birthDate);
		} catch (NullPointerException e) {
			System.out.println("Person: [" + getFirstName() + " " + getLastName() + " doesn't have birthdate");
			return 0;
		}
	    
	    
	    // include day of birth
	    dob.add(Calendar.DAY_OF_MONTH, -1);
	 
	    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
	    if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR)) {
	        age--;
	    }
	    return age;
	}
	
	@Override
	public String toString() {
		return "Person: [" + getFirstName() + " " + getLastName() + ", sex = " + ((isSex()) ? "male" : "female")
				+ ", TIN = " + getTin() + ", date of birth: " + getBirthDate() + "]";
	}
}
