package oopLes2_hw_1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Triangle triOne = new Triangle(new Point(3,2), new Point(3,5), new Point(7,2));
		System.out.println(triOne);
		
		Point[] points = new Point[4];
		points[0] = new Point(2,2);
		points[1] = new Point(2,4);
		points[2] = new Point(4,4);
		points[3] = new Point(4,2);
		Square squOne = new Square(points,true,"red");
		System.out.println(squOne);
		
		points[0].setX(2); points[0].setY(2);
		points[1].setX(3); points[1].setY(5);
		points[2].setX(7); points[2].setY(1);
		points[3].setX(4); points[3].setY(2);
		Square squTwo = new Square();
		squTwo.setFilled(true);
		squTwo.setFillColor("green");
		boolean res = squTwo.setPoints(points);
		
		if (res) {
			System.out.println(squTwo);
		} else {
			System.out.println("Wrong length between points of square!");
		}		
		
		Circle cirOne = new Circle(new Point(3,2), 4);
		System.out.println(cirOne);


	}

}
