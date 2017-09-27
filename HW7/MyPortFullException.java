package mvs;

public class MyPortFullException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Port is full now. Adding boats is blocked!!!";
	}

}
