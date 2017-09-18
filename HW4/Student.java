package mvs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student extends Person {

	private Date admissionDate;
	private Date graduationDate;
	private String groupID;
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

	// CONSTRUCTORS
	public Student(String firstName, String lastName, boolean sex, String sBirthDate, long tin, String sAdmissionDate,
			String sGraduationDate, String groupID) {
		super(firstName, lastName, sex, sBirthDate, tin);
		this.groupID = groupID;

		try {
			this.admissionDate = (sAdmissionDate != null) ? dateformat.parse(sAdmissionDate) : null;
			this.graduationDate = (sGraduationDate != null) ? dateformat.parse(sGraduationDate) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Student(String sAdmissionDate, String sGraduationDate, String groupID) {
		super();
		this.groupID = groupID;

		try {
			this.admissionDate = (sAdmissionDate != null) ? dateformat.parse(sAdmissionDate) : null;
			this.graduationDate = (sGraduationDate != null) ? dateformat.parse(sGraduationDate) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public Student(Person personA, String sAdmissionDate, String sGraduationDate, String groupID) {
		super(personA.getFirstName(), personA.getLastName(), personA.isSex(), personA.getBirthDate(), personA.getTin());
		this.groupID = groupID;

		try {
			this.admissionDate = (sAdmissionDate != null) ? dateformat.parse(sAdmissionDate) : null;
			this.graduationDate = (sGraduationDate != null) ? dateformat.parse(sGraduationDate) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Student() {
		super();
	}

	// GET & SET
	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	@Override
	public String toString() {
		return "Student: [" + getFirstName() + " " + getLastName() + ", group = " + getGroupID()+ ", age = " + getAge()  + "]";
	}

}
