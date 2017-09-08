package oopLes2_hw_1;

public class Square extends Shape {

	private Point[] points = new Point[4];
	private boolean filled;
	private String fillColor;

	// CONSTRUCTORS

	public Square(Point[] points, boolean filled, String fillColor) {
		super();
		this.points = points;
		this.filled = filled;
		this.fillColor = fillColor;
	}

	public Square(Point[] points) {
		super();
		this.points = points;
		this.filled = false;
		this.fillColor = "";
	}

	public Square() {
		super();
	}

	// GET & SET
	public Point[] getPoints() {
		return points;
	}

	public boolean setPoints(Point[] points) {
		// do control
		boolean isOk = true;
		double a = getLength(points[0], points[1]);
		if (a != getLength(points[1], points[2])) {
			isOk = false;
		}
		if (a != getLength(points[2], points[3])) {
			isOk = false;
		}
		if (a != getLength(points[3], points[0])) {
			isOk = false;
		}
		if (isOk) {
			this.points = points;
		}
		return isOk;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
		if (!filled) {
			this.fillColor = "";
		}
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	@Override
	public String toString() {
		return "Square [side length = " + getLength(points[0], points[1]) + "\n" + " filled=" + filled + ", fillColor="
				+ fillColor + "\n" + " perimetr=" + getPerimetr() + ", area=" + getArea() + "]";
	}

	private double getLength(Point a, Point b) {

		double lengthA = a.getY() - b.getY();
		double lengthB = a.getX() - b.getX();

		return Math.sqrt(lengthA * lengthA + lengthB * lengthB);
	}

	@Override
	public double getPerimetr() {
		return getLength(points[0], points[1]) * 4;
	}

	@Override
	public double getArea() {
		double a = getLength(points[0], points[1]);
		return a * a;
	}

}
