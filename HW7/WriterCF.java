package mvs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriterCF implements Runnable {

	private CopyFiles cf;

	public WriterCF(CopyFiles cf) {
		super();
		this.cf = cf;
	}

	@Override
	public void run() {

		try {
			OutputStream fos = new FileOutputStream(cf.getNewFileName());
			while (cf.isProcessWrite()) {
				cf.writeBlock(fos);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Done!");

	}

}
