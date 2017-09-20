package mvs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class MergeFiles {

	private File f1;
	private File f2;

	public boolean setFile(int i) {

		String fileName;
		for (;;) {
			fileName = String.valueOf(JOptionPane.showInputDialog("Input path for file " + i));

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
		if (i == 1)
			f1 = new File(fileName);
		if (i == 2)
			f2 = new File(fileName);

		return true;
	}

	public void readFile(File f, Set<String> wordSet) {

		FileReader fileReader;
		BufferedReader reader;
		String line = null;

		try {
			fileReader = new FileReader(f);
			reader = new BufferedReader(fileReader);

			try {
				reader.read();
				while ((line = reader.readLine()) != null) {
					line = line.replace('.', ' ');
					line = line.replace(',', ' ');
					line = line.replace('"', ' ');
					line = line.replace('(', ' ');
					line = line.replace(')', ' ');
					line = line.replace('<', ' ');
					line = line.replace('>', ' ');
					line = line.replace(':', ' ');
					line = line.replace(';', ' ');
					line = line.replace('=', ' ');
					line = line.replace('-', ' ');
					line = line.replace('\t', ' ');

					String[] subStrings = line.split(" ");

					for (String s : subStrings) {
						wordSet.add(s);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void writeFile(String fn, Set<String> wordSet) {

		List<String> sortedStrings = new ArrayList<String>(wordSet);
        Collections.sort(sortedStrings);
		
		FileWriter fileWriter;
		BufferedWriter bw;

		try {
			fileWriter = new FileWriter(new File(fn));
			bw = new BufferedWriter(fileWriter);

			for (String string : sortedStrings) {
				bw.write(string);
				bw.newLine();;
			}
			bw.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void makeJob() {

		if (!setFile(1))
			return;
		if (!setFile(2))
			return;

		Set<String> wordSet = new LinkedHashSet<String>();

		readFile(f1, wordSet);
		readFile(f2, wordSet);

		wordSet.removeAll(Arrays.asList("", null));

		String newFileName = f1.getPath().substring(0, f1.getPath().indexOf(f1.getName()));
		newFileName = newFileName + "mergefile.txt";
		writeFile(newFileName, wordSet);

		System.out.println("Done!");

	}

}
