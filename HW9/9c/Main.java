package mvs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

	private static File f1;

	public static boolean setFile() {

		String fileName;
		for (;;) {
			fileName = String.valueOf(JOptionPane.showInputDialog("Input path for file "));

			if (fileName.equals("exit")) {
				System.out.println("Bye!");
				return false;
			}

			if (!new File(fileName).exists()) {
				System.out.println(fileName + " doesn't exist! Input existing path or type 'exit' to quit");
				continue;
			}
			break;
		}

		f1 = new File(fileName);
		return true;
	}

	public static void readFile(Letters lettersClass) {

		List<Letter> letters  = lettersClass.getLetters();
		
		FileReader fileReader;
		BufferedReader reader;
		String line = null;

		try {
			fileReader = new FileReader(f1);
			reader = new BufferedReader(fileReader);

			try {
				reader.read();
				while ((line = reader.readLine()) != null) {
					for (char sim : line.toCharArray()) {

						Letter let = new Letter(sim);
						let.increaseCount();
						int letterIndex = letters.lastIndexOf(new Letter(sim));
						if (letterIndex == -1) {
							letters.add(let);
							continue;
						} else {
							letters.get(letterIndex).increaseCount();
						}

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		if (!setFile())
			return;

		Letters letters = new Letters();

		readFile(letters);
		
		System.out.println(letters);

	}

}
