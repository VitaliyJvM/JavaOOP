package mvs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ReaderCF implements Runnable {

	private CopyFiles cf;

	public ReaderCF(CopyFiles cf) {
		super();
		this.cf = cf;
	}

	@Override
	public void run() {

		try {
			InputStream fis = new FileInputStream(cf.getCopyFileName());
			
			while (cf.isProcessRead()) {
				cf.readBlock(fis);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
