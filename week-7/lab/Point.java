// Patrick Temple
// Prof. Whitener
// CSCI165
// 20 March 2020 (midterm cancelled)

// Week 7 Lab: Point
// Purpose: to define the Point Class

public class Point {
	
	// class features/variables
	private int x = 0;
	private int y = 0;
	
	// constructors
	
	// blank constructor - the way I created
	// my object variables automatically configures
	// x and y to be zero
	public Point() {  }
	
	// regular constructor
	public Point(int x, int y) {
		
		setXY(x, y);
		
	}
	
	public Point(Point otherPoint) {
		
		this.x = otherPoint.x;
		this.y = otherPoint.y;
		
	}
	
	// getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int[] getXY() {
		int[] result = new int[2];
		
		result[0] = this.x;
		result[1] = this.y;
		
		return result;
	}
	
	public void setXY(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	// distance calculators:
	// to find the difference, we need to
	// use the distance formula:
	//  __________________________
	// √((x2 - x1)²) = (y2 - y1)²)
	// more info:
	// https://www.purplemath.com/modules/distform.htm
	
	// distance from this point
	// to the given point at (X, Y)
	public double distance(int x, int y) {
		
		// first, we will start out by
		// squaring the difference of the xs,
		// then the same with y
		double xSquaredDiff = Math.pow((x - this.x), 2);
		double ySquaredDiff = Math.pow((y - this.y), 2);
		
		double squaredDiffs = xSquaredDiff + ySquaredDiff; // add them together
		
		// now we can get the square root then
		// return the calculated difference
		double calcDistance = Math.sqrt(squaredDiffs);
		return calcDistance;
		
	}
	
	// distance from this point to
	// the given instance of Point
	public double distance(Point otherPoint) {
		
		// do the same as the above version
		// of this overloaded method, except
		// using the points from the
		// other object
		
		double xSquaredDiff = Math.pow((otherPoint.x - this.x), 2);
		double ySquaredDiff = Math.pow((otherPoint.y - this.y), 2);
		
		double squaredDiffs = xSquaredDiff + ySquaredDiff;
		
		double calcDistance = Math.sqrt(squaredDiffs);
		return calcDistance;
		
	}
	
	// distance from this point to (0, 0)
	public double distance() {
		
		// distance, but assuming from the origin
		// (center) of the Cartesian plot
		
		double xSquaredDiff = Math.pow((0 - this.x), 2);
		double ySquaredDiff = Math.pow((0 - this.y), 2);
		
		double squaredDiffs = xSquaredDiff + ySquaredDiff;
		
		double calcDistance = Math.sqrt(squaredDiffs);
		return calcDistance;
		
		
	}
	
	// methods with @Override before them
	
	@Override
	public boolean equals(Object otherObj) {
		
		// is otherObj at the same memory address?
		if(this == otherObj)                    return true;
		
		// is otherObj null?
		if(otherObj == null)                    return false;
		
		// was otherObj once a Point?
		if(getClass() != otherObj.getClass())   return false;
		
		// copy otherObj into a Point variable so it
		// thinks it's a point again; this is
		// done by type casting
		Point other = (Point) otherObj;
		
		return this.x == other.x &&
			   this.y == other.y;
		
	}
	
	@Override
	public String toString() {
		
		return String.format("(%d, %d)", this.x, this.y);
		
	}
	
}
