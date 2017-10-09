package mvs;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Dictionary dict = new Dictionary();
		dict.readDictionary();
		Translator trans = new Translator(dict);
		
		String[] options = { "Dictionary", "Translator", "Quit" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice",
					"Main menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			switch (x) {
			case 0:
				dict.dictMenu();
				continue;
			case 1:
				trans.transMenu();
				continue;
			case 2:
				break;
			default:
				continue;
			}
			break;
		}
		
		System.out.println("Bye!");
		
	}

}
