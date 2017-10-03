package mvs;

public class Stack {
	private Object[] arrayObject;
	private BlackList bl;

	public Stack() {
		super();
		arrayObject = new Object[10];
	}
	
	public Stack(int length) {
		super();
		arrayObject = new Object[length];
	}
	
	public Stack(int length, BlackList bl) {
		super();
		arrayObject = new Object[length];
		this.bl = bl;
	}

	public void setBl(BlackList bl) {
		this.bl = bl;
	}
	
	public void add(Object obj) {

		if (bl.check(obj)) {
			System.out.println(obj.toString() + " is in a BlackList!");
			return;
		}
		
		for (int i = 0; i < arrayObject.length; i++) {
			if (arrayObject[i] == null) {
				arrayObject[i] = obj;
				return;
			}
		}

	}
	
	public Object get() {

		Object obj = null;
		for (int i = arrayObject.length-1; i >= 0; i--) {
			if (arrayObject[i] == null) continue;
			obj = arrayObject[i];
			break;
		}
		return obj;

	}
	
	public Object getOut() {

		Object obj = null;
		for (int i = arrayObject.length-1; i >= 0; i--) {
			if (arrayObject[i] == null) continue;
			obj = arrayObject[i];
			arrayObject[i] = null;
			break;
		}
		return obj;

	}
	
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Stack:");
		sb.append(System.lineSeparator());
		for (int k = 0; k < arrayObject.length; k++) {
			if (arrayObject[k] != null) {
				sb.append((k + 1) + ") " + arrayObject[k]);
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
	

}
