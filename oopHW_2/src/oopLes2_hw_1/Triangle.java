package oopLes2_hw_1;

public class Triangle extends Shape {

	private Point pointA;
	private Point pointB;
	private Point pointC;
	private boolean filled;
	private String fillColor;

	// CONSTRUCTORS
	public Triangle(Point pointA, Point pointB, Point pointC, boolean filled, String fillColor) {
		super();
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		this.filled = filled;
		this.fillColor = fillColor;
	}

	public Triangle(Point pointA, Point pointB, Point pointC) {
		super();
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		this.filled = false;
		this.fillColor = "";
	}

	public Triangle() {
		super();
	}

	// GET & SET
	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
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
		return "Triangle [sideA=" + getLength(pointA,pointB) + ", sideB=" + getLength(pointB,pointC) + ", sideC=" + getLength(pointA,pointC) + "\n"
				+ " filled=" + filled + ", fillColor=" + fillColor + "\n"
				+ " perimetr=" + getPerimetr() + ", area=" + getArea() + "]";
	}

	private double getLength(Point a, Point b) {

		double lengthA = a.getY() - b.getY();
		double lengthB = a.getX() - b.getX();

		return Math.sqrt(lengthA * lengthA + lengthB * lengthB);
	}

	@Override
	public double getPerimetr() {
		return getLength(pointA,pointB) + getLength(pointB,pointC) + getLength(pointA,pointC);
	}

	@Override
	public double getArea() {
		double a = getLength(pointA,pointB);
		double b = getLength(pointB,pointC);
		double c = getLength(pointA,pointC);
		
		double polP = (a + b + c) / 2;
		return Math.sqrt(polP * (polP - a) * (polP - b) * (polP - c));
	}

}
