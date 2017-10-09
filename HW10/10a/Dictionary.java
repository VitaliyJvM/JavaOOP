package mvs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Dictionary implements Serializable {

	private static final long serialVersionUID = 2412631362690142830L;

	private HashMap<String, String> dictHM = new HashMap<>();
	private String dictFileName = "dictionary.dat";

	public void readDictionary() {

		Dictionary tempDict = new Dictionary();
		try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(dictFileName))) {
			tempDict = (Dictionary) OIS.readObject();
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println("File of a Dictionary is not found !!!");
		} catch (IOException | ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("ERROR load Dictionary !!!");
		}
		dictHM = tempDict.dictHM;
	}

	public void saveDictionary() {

		try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(dictFileName))) {
			OOS.writeObject(this);
			System.out.println("Dictionary saved to the disk !!!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR save Dictionary !!!");
		}

	}

	public Dictionary() {
		super();
	}

	public String getValue(String key) {
		return dictHM.getOrDefault(key, key);
	}

	private void addWord() {

		String wordInEng;
		for (;;) {
			wordInEng = String.valueOf(JOptionPane.showInputDialog("Input english word")).toLowerCase();
			break;
		}
		String wordInUkr;
		for (;;) {
			wordInUkr = String.valueOf(JOptionPane.showInputDialog("Input ukrainian word")).toLowerCase();
			break;
		}
		dictHM.put(wordInEng, wordInUkr);

	}
	
	private void deleteWord() {

		String wordInEng;
		for (;;) {
			wordInEng = String.valueOf(JOptionPane.showInputDialog("Input english word for removing")).toLowerCase();
			break;
		}
		
		if (!dictHM.containsKey(wordInEng)) {
			System.out.println(wordInEng + " is absent in dictionary!");
			return;
			
		}
		dictHM.remove(wordInEng);
		System.out.println(wordInEng + " was removed!");
	}

	public void dictMenu() {

		String[] options = { "Add", "Delete", "Save data", "Print", "Return" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Dictionary menu",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);

			switch (x) {
			case 0:
				addWord();
				continue;
			case 1:
				deleteWord();
				continue;
			case 2:
				saveDictionary();
				continue;				
			case 3:
				System.out.println("-----------");
				dictHM.forEach((key, value) -> System.out.println(key + " - " + value));
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
