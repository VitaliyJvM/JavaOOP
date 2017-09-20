package mvs;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MainCopyFiles {

	public static void makeCopy(CopyFiles copyFiles) {

		boolean refuse = false;
		if (copyFiles.getInDir() == null) {
			System.out.println("IN dir is not chosen! Copy will be cancel.");
			refuse = true;
		}
		if (copyFiles.getOutDir() == null) {
			System.out.println("OUT dir is not chosen! Copy will be cancel.");
			refuse = true;
		}
		if (copyFiles.getExt() == null) {
			System.out.println("EXT is not chosen! Copy will be cancel.");
			refuse = true;
		}
		if (refuse) {
			return;
		}

		File folder = new File(copyFiles.getInDir());
		File[] fileList = folder.listFiles(copyFiles);
		for (File file : fileList) {
			try {
				File out = new File(copyFiles.getOutDir()+file.getName());
				copyFiles.copyFile(file, out);
				System.out.println("Copy " + file);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		CopyFiles copyFiles = new CopyFiles();

		String[] options = { "Settings", "Copy files", "Quit" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Main menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			switch (x) {
			case 0:
				copyFiles.copyMenu();
				continue;
			case 1:
				makeCopy(copyFiles);
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
