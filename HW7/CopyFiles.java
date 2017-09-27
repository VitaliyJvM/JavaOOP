package mvs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class CopyFiles {

	private String copyFileName;
	private String newFileName;

	private boolean processRead = false;
	private boolean processWrite = false;
	private boolean turnWriteBlock = false;

	private byte[] buffer = new byte[1024 * 1024];
	private int byteRead = 0;

	// CONSTRUCTORS
	public CopyFiles() {
		super();
	}

	// GET & SET
	public String getCopyFileName() {
		return copyFileName;
	}

	public void setCopyFileName(String copyFileName) {
		this.copyFileName = copyFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public boolean isProcessRead() {
		return processRead;
	}

	public void setProcessRead(boolean processRead) {
		this.processRead = processRead;
	}

	public boolean isProcessWrite() {
		return processWrite;
	}

	public void setProcessWrite(boolean processWrite) {
		this.processWrite = processWrite;
	}

	public boolean isTurnWriteBlock() {
		return turnWriteBlock;
	}

	public void setTurnWriteBlock(boolean turnWriteBlock) {
		this.turnWriteBlock = turnWriteBlock;
	}

	@Override
	public String toString() {
		return "CopyFile:\n from = " + copyFileName + ",\n to = " + newFileName + ".";
	}

	// Working methods

	public void setDir(int k) {
		// k = 0 => copyFileName
		// k = 1 => newFileName

		String typePath = "FileName to copy";
		String pathName;

		switch (k) {
		case 0:
			typePath = "FileName to copy";
			break;
		case 1:
			typePath = "new FileName";
			break;
		}

		for (;;) {
			pathName = String.valueOf(JOptionPane.showInputDialog("Input path for " + typePath));

			if (pathName.equals("exit")) {
				return;
			}

			if ((!new File(pathName).exists()) && (k == 0)) {
				System.out.println(pathName + " doesn't exist! Input existing path or type 'exit' to quit");
				continue;
			}
			break;
		}

		switch (k) {
		case 0:
			setCopyFileName(pathName);
			break;
		case 1:
			setNewFileName(pathName);
			break;
		}
	}

	public synchronized void writeBlock(OutputStream fos) {

		for (; turnWriteBlock == false;) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		try {
			if (byteRead > 0) {
				fos.write(buffer, 0, byteRead);
			} else {
				setProcessWrite(false);
			}
			turnWriteBlock = false;
			notifyAll();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized void readBlock(InputStream fis) {

		for (; turnWriteBlock == true;) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		try {
			byteRead = fis.read(buffer);
			turnWriteBlock = true;
			if (byteRead <= 0) {
				setProcessRead(false);
			}
//			System.out.println("Send number -> " + byteRead);
			notifyAll();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void makeCopy() {

		if (copyFileName == null) {
			System.out.println("FileName to copy is empty!");
			return;
		}
		if (newFileName == null) {
			System.out.println("New FileName is empty!");
			return;
		}

		WriterCF provider = new WriterCF(this);
		ReaderCF receiver = new ReaderCF(this);

		Thread threadOne = new Thread(provider);
		Thread threadTwo = new Thread(receiver);
		setProcessRead(true);
		setProcessWrite(true);

		threadOne.start();
		threadTwo.start();

	}

}
