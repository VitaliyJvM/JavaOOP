package mvs;

public class Letter {
	
	private char simbol;
	private int count = 0;
	
	public Letter(char simbol) {
		super();
		this.simbol = simbol;
	}

	public Letter() {
		super();
	}

	public char getSimbol() {
		return simbol;
	}

	public void setSimbol(char simbol) {
		this.simbol = simbol;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void increaseCount() {
		this.count += 1;
	}

	@Override
	public String toString() {
		return "Letter [simbol=" + simbol + ", count=" + count + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + simbol;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Letter other = (Letter) obj;
		if (simbol != other.simbol)
			return false;
		return true;
	}

}
