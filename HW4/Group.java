package mvs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class Group implements Voenkom {

	private boolean outToFile = false; // false-display, true-file

	class SortedByName implements Comparator<Student> {

		public int compare(Student obj1, Student obj2) {

			if ((obj1 == null) && (obj2 == null)) {
				return 0;
			}
			if (obj1 == null) {
				return +1;
			}
			if (obj2 == null) {
				return -1;
			}
			String str1 = obj1.getLastName();
			String str2 = obj2.getLastName();

			return str1.compareTo(str2);
		}
	}

	class SortedByAge implements Comparator<Student> {

		public int compare(Student obj1, Student obj2) {

			if ((obj1 == null) && (obj2 == null)) {
				return 0;
			}
			if (obj1 == null) {
				return +1;
			}
			if (obj2 == null) {
				return -1;
			}

			double age1 = obj1.getAge();
			double age2 = obj2.getAge();

			if (age1 > age2) {
				return 1;
			} else if (age1 < age2) {
				return -1;
			} else {
				return 0;
			}
		}
	}

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
			System.out.println(studentLastName + " was removed from group!!!");
		}
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Group - " + groupID);
		sb.append(System.lineSeparator());
		for (int k = 0; k < arrStudents.length; k++) {
			if (arrStudents[k] != null) {
				sb.append((k + 1) + ") " + arrStudents[k]);
			} else {
				sb.append((k + 1) + ") " + " Free");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public void toFile() {

		String fn = "group.txt";
		try (PrintWriter pw = new PrintWriter(fn)) {
			pw.println(groupID);
			for (Student stu : arrStudents) {
				if (stu == null)
					continue;

				String sBirthDate = stu.getBirthDateToString();
				if (sBirthDate.equals(""))
					sBirthDate = "null";

				pw.println(stu.getFirstName() + ";" + stu.getLastName() + ";" + ((stu.isSex()) ? 1 : 0) + ";"
						+ sBirthDate);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR FILE WRITE");
		}
	}

	public void importGroup() {

		String fn = "group.txt";

		FileReader fileReader;
		BufferedReader reader;
		String line = null;

		// clean
		for (int i = 0; i < arrStudents.length; i += 1) {
			arrStudents[i] = null;
		}

		try {
			fileReader = new FileReader(fn);
			reader = new BufferedReader(fileReader);

			try {
				reader.read();
				boolean isFirstRow = true;
				while ((line = reader.readLine()) != null) {
					if (isFirstRow) {
						setGroupID(line);
						isFirstRow = false;
						continue;
					}

					String[] subStrings = line.split(";");
					if (subStrings.length != 4) {
						System.out.println("Wrong string in file: " + line);
						continue;
					}
					int a;
					try {
						a = Integer.valueOf(subStrings[2]);
					} catch (NumberFormatException e) {
						a = 0;
					}
					boolean sexStu = (a == 0) ? false : true;
					String sBirthDate = (subStrings[3].equals("null")) ? "" : subStrings[3];

					Student studentOne = new Student(subStrings[0], subStrings[1], sexStu, sBirthDate, 0L, null, null,
							"");
					addStudent(studentOne);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MyArrayFullException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String readyToArmy() {

		StringBuilder sb = new StringBuilder();
		sb.append("Group - " + groupID);
		sb.append(System.lineSeparator());
		int i = 0;
		for (int k = 0; k < arrStudents.length; k++) {
			if (arrStudents[k] == null) {
				continue;
			}
			if (arrStudents[k].getAge() < 18) {
				continue;
			}
			if (!arrStudents[k].isSex()) {
				continue;
			}
			sb.append("(" + (++i) + ") " + arrStudents[k]);
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	// Menu
	public void addMenu() {

		String firstName;
		for (;;) {
			firstName = String.valueOf(JOptionPane.showInputDialog("Input first name"));
			break;
		}
		String lastName;
		for (;;) {
			lastName = String.valueOf(JOptionPane.showInputDialog("Input last name"));
			break;
		}
		boolean sex;
		for (;;) {
			try {
				int a = Integer.valueOf(JOptionPane.showInputDialog("Input sex: 0-female, 1-male"));
				sex = (a == 0) ? false : true;
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error number format");
			}
		}
		String sBirthDate;
		for (;;) {
			sBirthDate = String.valueOf(JOptionPane.showInputDialog("Input BirthDate"));
			break;
		}
		Student studentOne = new Student(firstName, lastName, sex, sBirthDate, 0L, "01/09/2017", null, "");
		try {
			addStudent(studentOne);
		} catch (MyArrayFullException e) {
			// JOptionPane.showMessageDialog(null, "Error number format");
		}
	}

	public void deleteMenu() {

		String lastName;
		for (;;) {
			lastName = String.valueOf(JOptionPane.showInputDialog("Input last name for removing"));
			break;
		}
		deleteStudent(lastName);
	}

	public void printMenu() {

		String[] options = { "by Name", "by Age", "Settings" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "How sort students?",
					"Print students to " + ((outToFile) ? "file" : "display"), JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			switch (x) {
			case 0:
				Arrays.sort(arrStudents, new SortedByName());
				System.out.println("by Name");
				break;
			case 1:
				Arrays.sort(arrStudents, new SortedByAge());
				System.out.println("by Age");
				break;
			case 2:
				String[] outOptions = { "to Display", "to File" };
				int k = JOptionPane.showOptionDialog(null, "Choose direction:", "Print students to",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, outOptions, outOptions[0]);

				switch (k) {
				case 0:
					this.outToFile = false;
					break;
				case 1:
					this.outToFile = true;
					break;
				}
				continue;
			case -1:
				continue;
			}
			break;
		}

		if (outToFile) {
			toFile();
		} else {
			System.out.println(toString());
		}
	}

	public void groupMenu() {

		String[] options = { "Import from file", "Add", "Delete", "Print", "Return" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Group menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);

			switch (x) {
			case 0:
				importGroup();
				continue;
			case 1:
				addMenu();
				continue;
			case 2:
				deleteMenu();
				continue;
			case 3:
				printMenu();
				continue;
			case 4:
				break;
			default:
				continue;
			}
			break;
		}
	}

}
