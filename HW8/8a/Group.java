package mvs;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class Group implements FileFilter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1645295276340728127L;

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
	private static String groupExt = "gxt";

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

	public static Group readGroup() {

		String readingGroup;
		for (;;) {
			readingGroup = String.valueOf(JOptionPane.showInputDialog("Input group ID to read"));
			break;
		}
		if ((readingGroup == null) || (readingGroup.equals(""))) {
			System.out.println("Wrong group ID !!!");
			return null;
		}

		Group tempGroup = new Group();
		try (ObjectInputStream OIS = new ObjectInputStream(
				new FileInputStream("groupsFolder/" + readingGroup + "." + groupExt))) {
			tempGroup = (Group) OIS.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File of a group is not found !!!");		
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR load group !!!");
		}
		return tempGroup;
	}

	public void writeGroup() {

		try (ObjectOutputStream OOS = new ObjectOutputStream(
				new FileOutputStream("groupsFolder/" + groupID + "." + groupExt))) {
			OOS.writeObject(this);
			System.out.println("Group saved to the disk !!!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR save group !!!");
		}

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

		String[] options = { "by Name", "by Age" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "How sort students?", "Print students to display",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			switch (x) {
			case 0:
				Arrays.sort(arrStudents, new SortedByName());
				System.out.println("by Name");
				break;
			case 1:
				Arrays.sort(arrStudents, new SortedByAge());
				System.out.println("by Age");
				break;
			case -1:
				continue;
			}
			break;
		}

		System.out.println(toString());
	}

	public void setID() {

		String newGroupID;
		for (;;) {
			newGroupID = String.valueOf(JOptionPane.showInputDialog("Input new group name"));
			break;
		}
		setGroupID(newGroupID);
		for (Student student : arrStudents) {
			if (student == null)
				continue;
			student.setGroupID(newGroupID);
		}

	}

	public void groupMenu() {

		String[] options = { "Set ID group", "Add", "Delete", "Print", "Return" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Group menu / group ID: " + getGroupID(),
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);

			switch (x) {
			case 0:
				setID();
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

	private boolean check(String ext) {

		if (groupExt.equals(ext))
			return true;
		return false;

	}

	@Override
	public boolean accept(File pathname) {
		int pointerIndex = pathname.getName().lastIndexOf(".");
		if (pointerIndex == -1) {
			return false;
		}
		String ext = pathname.getName().substring(pointerIndex + 1);
		return check(ext);
	}

	public String groupName(String fileName) {
		int pointerIndex = fileName.lastIndexOf(".");
		if (pointerIndex == -1) {
			return fileName;
		}
		return fileName.substring(0, pointerIndex);
	}

	public void listGroups() {

		System.out.println("--------------------");
		System.out.println("List of groups:");
		File folder = new File("groupsFolder");
		File[] fileList = folder.listFiles(this);
		for (File file : fileList) {
			System.out.println(groupName(file.getName()));
		}
		System.out.println("--------------------");

	}

}
