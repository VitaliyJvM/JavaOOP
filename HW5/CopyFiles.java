package mvs;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class CopyFiles implements FileFilter {

	private String inDir;
	private String outDir;
	private String ext;

	// CONSTRUCTORS
	public CopyFiles() {
		super();
	}

	// GET & SET
	public String getInDir() {
		return inDir;
	}

	public void setInDir(String inDir) {
		this.inDir = inDir;
	}

	public String getOutDir() {
		return outDir;
	}

	public void setOutDir(String outDir) {
		this.outDir = outDir;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public void copyFile(File in, File out) throws IOException {
		if (in == null || out == null) {
			throw new IllegalArgumentException("Null file Pointer");
		}
		try (InputStream fis = new FileInputStream(in); OutputStream fos = new FileOutputStream(out)) {
			byte[] buffer = new byte[1024 * 1024];
			int byteRead = 0;

			for (; (byteRead = fis.read(buffer)) > 0;) {
				fos.write(buffer, 0, byteRead);
			}

		} catch (IOException e) {
			throw e;
		}
	}

	public void setDir(int k) {
		// k = 0 => IN dir
		// k = 1 => OUT dir

		String typeDir = "IN";
		String dirName;

		switch (k) {
		case 0:
			typeDir = "IN";
			break;
		case 1:
			typeDir = "OUT";
			break;
		}

		for (;;) {
			dirName = String.valueOf(JOptionPane.showInputDialog("Input path for " + typeDir + " directory"));

			if (dirName.equals("exit")) {
				return;
			}

			if (!new File(dirName).exists()) {
				System.out.println(dirName + " doesn't exist! Input existing path or type 'exit' to quit");
				continue;
			}
			break;
		}

		switch (k) {
		case 0:
			setInDir(dirName);
			break;
		case 1:
			setOutDir(dirName);
			break;
		}
	}

	public void chooseExt() {

		String[] options = { "doc", "jpg", "txt", "xls", "Return" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Choose your extension",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);

			if ((x == -1) || (x == 4)) {
				return;
			}
			setExt(options[x]);
			return;
		}
	}

	// Menu

	public void copyMenu() {

		String[] options = { "Set IN dir", "Set OUT dir", "Choose extension", "Print sets", "Return" };
		for (;;) {
			int x = JOptionPane.showOptionDialog(null, "Make your choice", "Settings menu", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[3]);

			switch (x) {
			case 0:
				setDir(0);
				continue;
			case 1:
				setDir(1);
				continue;
			case 2:
				chooseExt();
				continue;
			case 3:
				System.out.println(toString());
				continue;
			case 4:
				break;
			default:
				continue;
			}
			break;
		}
	}

	@Override
	public String toString() {
		return "CopyFiles:\n inDir=" + inDir + ",\n outDir=" + outDir + ",\n ext=" + ext + ".";
	}

	@Override
	public boolean accept(File pathname) {

		int pointerIndex = pathname.getName().lastIndexOf(".");
		if (pointerIndex == -1) {
			return false;
		}
		String ext = pathname.getName().substring(pointerIndex + 1);
		return this.ext.equals(ext);
	}

}
