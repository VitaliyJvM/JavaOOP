package mvs;

public class BlackList {

	private Class<?>[] arrayClass;

	public BlackList() {
		super();
		arrayClass = new Class[10];
	}

	public BlackList(int length) {
		super();
		arrayClass = new Class[length];
	}

	public void add(Class<?> cl) {

		// check if this class is in a list
		boolean isClass = false;
		for (int i = 0; i < arrayClass.length; i++)
			if (arrayClass[i] == cl)
				isClass = true;

		if (isClass)
			return;

		for (int i = 0; i < arrayClass.length; i++) {
			if (arrayClass[i] == null) {
				arrayClass[i] = cl;
				return;
			}
		}

	}

	public boolean check(Object obj) {
		Class<?> classObj = obj.getClass();
		for (int i = 0; i < arrayClass.length; i++)
			if (arrayClass[i] == classObj)
				return true;

		return false;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Black list:");
		sb.append(System.lineSeparator());
		for (int k = 0; k < arrayClass.length; k++) {
			if (arrayClass[k] != null) {
				sb.append((k + 1) + ") " + arrayClass[k]);
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}

}
