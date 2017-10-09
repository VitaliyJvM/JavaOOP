package mvs;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Translator {

	private String fileIn = "English.in";
	private String fileOut = "Ukrainian.out";
	private Dictionary dict;
	
	public Translator(Dictionary dict) {
		super();
		this.dict = dict;
	}
	
	public Translator() {
		super();
	}

	private String translateWord(String inWord) {

		return dict.getValue(inWord);
	}
	
	private void translateLine(String line, PrintWriter pw) {

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
		line = line.replace('?', ' ');
		line = line.replace('“', ' ');
		line = line.replace('”', ' ');
		line = line.replace('\t', ' ');
		String[] subStrings = line.split(" ");

		boolean isFirstWord = true;
		for (String s : subStrings) {
			if (s == null)
				continue;
			if (s.equals(""))
				continue;
			if (!isFirstWord) {
				pw.print(" ");
			}
			pw.print(translateWord(s));
			isFirstWord = false;
		}

		pw.println("");

	}
	
	private void translateFile() {

		try (PrintWriter pw = new PrintWriter(fileOut)) {
			Files.lines(Paths.get(fileIn)).map(a -> a.toLowerCase()).forEach(n -> translateLine(n, pw));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR FILE WRITE");
		} catch (Exception e) {
			
		}
		System.out.println("File was translated!!!");
	}

	private void translateByWord() {
		
		String wordInEng;
		for (;;) {
			wordInEng = String.valueOf(JOptionPane.showInputDialog("Input english word")).toLowerCase();
			break;
		}
		System.out.println(wordInEng + " - " + translateWord(wordInEng));
	}

	public void transMenu() {

		String[] options = { "By word", "By file", "Return" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Translator menu",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);

			switch (x) {
			case 0:
				translateByWord();
				continue;
			case 1:
				translateFile();
				continue;
			case 2:
				break;
			default:
				continue;
			}
			break;
		}
	}

}
