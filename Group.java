package oopLes3_hw;

public class Group {

	private Student[] arrStudents = new Student[10];
	private String groupID;

	// CONSTRUCTORS

	public Group(String groupID) {
		super();
		this.groupID = groupID;
	}

	public Group() {
		super();
	}

	// GET & SET

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public void addStudent(Student student) throws MyArrayFullException {

		for (int i = 0; i < arrStudents.length; i++) {
			if (arrStudents[i] == null) {
				arrStudents[i] = student;
				student.setGroupID(this.groupID);
				return;
			}
		}
		throw new MyArrayFullException();
	}

	public int findStudentIndexByName(String studentLastName) {
		for (int i = 0; i < arrStudents.length; i++) {
			if (arrStudents[i] == null) {
				continue;
			} else if (arrStudents[i].getLastName().equals(studentLastName)) {
				return i;
			}
		}
		return -1;
	}

	public Student findStudentByName(String studentLastName) {
		for (int i = 0; i < arrStudents.length; i++) {
			if (arrStudents[i] == null) {
				continue;
			} else if (arrStudents[i].getLastName().equals(studentLastName)) {
				return arrStudents[i];
			}
		}
		return null;// new Student();
	}

	public void deleteStudent(Student student) {
		for (;;) {
			int studentIndex = findStudentIndexByName(student.getLastName());
			if (studentIndex == -1) {
				System.out.println(student.getLastName() + " is absent in group!!!");
				return;
			}
			arrStudents[studentIndex] = null;
		}
	}

	public void deleteStudent(String studentLastName) {
		for (;;) {

			int studentIndex = findStudentIndexByName(studentLastName);
			if (studentIndex == -1) {
				System.out.println(studentLastName + " is absent in group!!!");
				return;
			}
			arrStudents[studentIndex] = null;
		}
	}

	@Override
	public String toString() {

		Student[] arrStudentsCopy = new Student[10];
		System.arraycopy(arrStudents, 0, arrStudentsCopy, 0, arrStudents.length);

		for (int i = 1; i < arrStudentsCopy.length; i++) {
			for (int j = i; (j >= 1) && ((arrStudentsCopy[j - 1] == null) || ((arrStudentsCopy[j]!= null)&&(arrStudentsCopy[j].getLastName().compareTo(arrStudentsCopy[j - 1].getLastName()) < 0)))                          ; j--) {
					Student a = arrStudentsCopy[j];
					arrStudentsCopy[j] = arrStudentsCopy[j-1];
					arrStudentsCopy[j-1] = a;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Group - " + groupID);
		sb.append(System.lineSeparator());
		for (int k = 0; k < arrStudentsCopy.length; k++) {
			if (arrStudentsCopy[k] != null) {
				sb.append((k + 1) + ") " + arrStudentsCopy[k]);
			} else {
				sb.append((k + 1) + ") " + " Free");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

}
