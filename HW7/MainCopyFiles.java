package mvs;

public class MainCopyFiles {

	public static void main(String[] args) {
		
		CopyFiles cf = new CopyFiles();
		
		MenuCopyFiles menu = new MenuCopyFiles(cf);
		menu.startMenu();

		System.out.println("Bye!");

	}

}
