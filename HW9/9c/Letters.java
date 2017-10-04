package mvs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Letters {

	class SortedByCount implements Comparator<Letter> {

		public int compare(Letter obj1, Letter obj2) {

			if ((obj1 == null) && (obj2 == null)) {
				return 0;
			}
			if (obj1 == null) {
				return +1;
			}
			if (obj2 == null) {
				return -1;
			}

			if (obj1.getCount() > obj2.getCount())
				return -1;
			if (obj1.getCount() < obj2.getCount())
				return 1;
			return 0;

		}
	}

	private List<Letter> letters = new ArrayList<>();

	public Letters() {
		super();
	}

	public List<Letter> getLetters() {
		return letters;
	}

	@Override
	public String toString() {
		
		letters.sort(new SortedByCount());
		
		StringBuilder sb = new StringBuilder();
		sb.append("Letters in file:");
		sb.append(System.lineSeparator());
		int k = 0;
		for (Letter letter : letters) {
			if (letter != null) {
				sb.append((++k) + ") " + letter);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();

	}

	
}
