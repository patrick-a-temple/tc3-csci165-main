// Patrick Temple
// Prof. Whitener
// CSCI165
// 21 March 2020 (midterm cancelled)

// Week 7 Lab: Circle
// Purpose: to define the Circle class


public class Circle {
	
	// class features/variables
	private Point  center = new Point(0, 0);
	private double radius = 1;
	
	// class constructors
	public Circle() {  } // blank constructor 
	
	// circle defined without a Point object
	public Circle(int centerX, int centerY, double radius) {
		
		this.center = new Point(centerX, centerY);
		this.radius = radius;
		
	}
	
	// circle defined with a Point object
	public Circle(Point center, double radius) {
		
		this.center = new Point(center);
		this.radius = radius;
		
	}
	
	// just the radius (used in Cylinder class)
	public Circle(double radius) {
		this.radius = radius;
	}
	
	// copy constructor
	public Circle(Circle otherCircle) {
		this.center = new Point(otherCircle.center);
		this.radius = otherCircle.radius;
	}
	
	
	public double getRadius() {
		return this.radius;
	}
	
	public void setRadius(double rad) {
		this.radius = rad;
	}
	
	public Point getCenter() {
		return new Point(this.center);
	}
	
	public void setCenter(Point newCenter) {
		this.center = new Point(newCenter);
	}
	
	public int getCenterX() {
		// create new point obj to preserve security
		Point temp = new Point(this.center);
		return temp.getX();
	}
	
	public void setCenterX(int x) {
		Point temp = new Point(this.center);
		temp.setX(x);
		this.center = new Point(temp);
	}
	
	public int getCenterY() {
		Point temp = new Point(this.center);
		return temp.getY();
	}
	
	public void setCenterY(int y) {
		Point temp = new Point(this.center);
		temp.setY(y);
		this.center = new Point(temp);
	}
	
	public void getCenterXY() {
		// copy center to protect security,
		// and use copy to get needed data
		Point temp = new Point(this.center);
		int[] results = new int[2];
		results[0] = temp.getX();
		results[1] = temp.getY();
	}
	
	public void setCenterXY(int x, int y) {
		this.center = new Point(x, y);
	}
	
	// circle area: PI * radius^2
	public double getArea() {
		// return result of area formula
		return (Math.PI * (Math.pow(this.radius, 2)));
	}
	
	// circle circumference: 2 * PI * r
	public double getCircumference() {
		// return result of circumference formula
		return (2 * Math.PI * this.radius);
	}
	
	// calculate distance from circle centers
	public double distance(Circle anotherCircle) {
		Point copy = new Point(anotherCircle.center);
		double result = this.center.distance(copy);
		return result;
	}
	
	// overridden methods
	
	@Override
	public boolean equals(Object otherObj) {
		
		// same memory address
		if(this == otherObj)                      return true;
		
		// is the other object null?
		if(otherObj == null)                      return false;
		
		// was the other object once a point?
		if(getClass() != otherObj.getClass())     return false;
		
		// copy the object into the Circle class
		Circle other = (Circle) otherObj;
		
		return this.center.equals(other.center) &&
		       this.radius == other.radius;
		
	}
	
	@Override
	public String toString() {
		return String.format("Circle[radius = %.3f, center = (%d, %d)]",
				this.radius, this.center.getX(), this.center.getY());
	}
	
}
