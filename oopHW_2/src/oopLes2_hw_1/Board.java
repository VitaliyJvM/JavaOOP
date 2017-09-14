package oopLes2_hw_1;

public class Board {

	private double sideLength;
	private Shape[] shapesArray = new Shape[4];

	// CONSTRUCTORS

	public Board(double sideLength) {
		super();
		this.sideLength = sideLength;
	}

	public Board() {
		super();
	}

	// GET & SET

	public double getSideLength() {
		return sideLength;
	}

	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}

	private int getIndex(char part) {

		int partIndex = 0;
		switch (part) {
		case 'a':
			partIndex = 0;
			break;
		case 'b':
			partIndex = 1;
			break;
		case 'c':
			partIndex = 2;
			break;
		case 'd':
			partIndex = 3;
			break;
		default:
			partIndex = 0;
			break;
		}
		return partIndex;
	}

	private char getPartName(int partIndex) {

		char partName = 'a';
		switch (partIndex) {
		case 0:
			partName = 'a';
			break;
		case 1:
			partName = 'b';
			break;
		case 2:
			partName = 'c';
			break;
		case 3:
			partName = 'd';
			break;
		default:
			partName = 'a';
			break;
		}
		return partName;
	}

	public void addShape(Shape shape, char part) {

		int partIndex = getIndex(part);
		if (shapesArray[partIndex] == null) {
			if (shape.getMaxSide() > sideLength) {
				System.out.println("Shape is bigger than part of the board! It is denied to insert this shape!");
				return;
			}
			
			shapesArray[partIndex] = shape;
			return;
		}

		System.out.println("Part " + part + " is full! Choose another part!!!");
	}

	public void eraseShape(char part) {

		int partIndex = getIndex(part);
		if (shapesArray[partIndex] == null) {
			System.out.println("There is no one shape in part " + part + "! Choose another part!!!");
			return;
		}
		shapesArray[partIndex] = null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		double totalArea = 0;
		for (int i = 0; i < shapesArray.length; i++) {
			sb.append("**** Part - " + getPartName(i));
			sb.append(System.lineSeparator());

			if (shapesArray[i] != null) {
				sb.append(shapesArray[i]);
				totalArea += shapesArray[i].getArea();
			} else {
				sb.append("It is free.");
			}
			sb.append(System.lineSeparator());
		}
		sb.append("----------------------");
		sb.append(System.lineSeparator());
		sb.append("Total used area is " + totalArea);
		return sb.toString();
	}

}
