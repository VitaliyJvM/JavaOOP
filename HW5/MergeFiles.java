package mvs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

	public void readFile(File f, TreeSet<String> wordSet) {

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
						if (s == null)
							continue;
						if (s.equals(""))
							continue;
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

	public SortedSet<String> mergeSets(Set<String> wordSet1, Set<String> wordSet2) {

		SortedSet<String> mergedSet = new TreeSet<String>();

		for (String s : wordSet1) {
			if (wordSet2.contains(s))
				mergedSet.add(s);
		}

		return mergedSet;
	}

	public void writeFile(String fn, Set<String> wordSet) {

		List<String> sortedStrings = new ArrayList<String>(wordSet);
		Collections.sort(sortedStrings);

		try (PrintWriter pw = new PrintWriter(fn)) {
			for (String string : sortedStrings) {
				pw.println(string);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR FILE WRITE");
		}
	}

	public void makeJob() {

		if (!setFile(1))
			return;
		if (!setFile(2))
			return;

		TreeSet<String> wordSet1 = new TreeSet<String>();
		TreeSet<String> wordSet2 = new TreeSet<String>();

		readFile(f1, wordSet1);
		readFile(f2, wordSet2);
		// System.out.println("f1 = " + f1.getPath() + " / count = " +
		// wordSet1.size());
		// System.out.println("f2 = " + f2.getPath() + " / count = " +
		// wordSet2.size());

		SortedSet<String> mergedSet = mergeSets(wordSet1, wordSet2);

		String newFileName = f1.getPath().substring(0, f1.getPath().indexOf(f1.getName()));
		newFileName = newFileName + "mergefile.txt";
		writeFile(newFileName, mergedSet);

		System.out.println("Done!");
	}

}
