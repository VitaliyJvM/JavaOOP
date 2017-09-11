package oopLes2_hw_1;

public class Circle extends Shape {

	private Point pointC;
	private double radius;
	private boolean filled;
	private String fillColor;

	// CONSTRUCTORS

	public Circle(Point pointC, double radius, boolean filled, String fillColor) {
		super();
		this.pointC = pointC;
		this.radius = radius;
		this.filled = filled;
		this.fillColor = fillColor;
	}

	public Circle(Point pointC, double radius) {
		super();
		this.pointC = pointC;
		this.radius = radius;
		this.filled = false;
		this.fillColor = "";
	}

	public Circle() {
		super();
	}

	// GET & SET

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
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
	public double getMaxSide() {
		return 2 * radius;
	}
	
	@Override
	public double getPerimetr() {
		return 2 * Math.PI * radius;
	}

	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public String toString() {
		return "Circle [center X = " + pointC.getX() + ", center Y = " + pointC.getY() + ", radius = " + radius + "\n"
				+ " filled=" + filled + ", fillColor=" + fillColor + "\n"
				+ " perimetr=" + getPerimetr() + ", area=" + getArea() + "]";
	}



}
