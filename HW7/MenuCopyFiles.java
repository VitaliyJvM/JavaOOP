package mvs;

import javax.swing.JOptionPane;

public class MenuCopyFiles {

	private CopyFiles cf;

	// CONSTRUCTORS
	public MenuCopyFiles(CopyFiles cf) {
		super();
		this.cf = cf;
	}

	// Working methods
	public void startMenu() {

		String[] options = { "Settings", "Copy files", "Quit" };
		
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Main menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			switch (x) {
			case 0:
				settingsMenu();
				continue;
			case 1:
				cf.makeCopy();
				continue;
			case 2:
				break;
			default:
				continue;
			}
			break;
		}

	}
	
	public void settingsMenu() {

		String[] options = { "Set copy file", "Set new file", "Print sets", "Return" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Settings menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[3]);

			switch (x) {
			case 0:
				cf.setDir(0);
				continue;
			case 1:
				cf.setDir(1);
				continue;
			case 2:
				System.out.println(cf.toString());
				continue;
			case 3:
				break;
			default:
				continue;
			}
			break;
		}
	}

	
}
