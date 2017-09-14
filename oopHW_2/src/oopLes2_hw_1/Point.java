package oopLes2_hw_1;

public class Point {

	private double x;
	private double y;
	private String color;

	public Point(double x, double y, String color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.color = "black";
	}	
	
	public Point() {
		super();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", color=" + color + "]";
	}

}
