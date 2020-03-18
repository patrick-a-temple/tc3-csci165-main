// Patrick Temple
// Prof. Whitener
// CSCI165
// 20 March 2020

// Week 7 Lab: Cylinder
// Purpose: to extend the Circle class to make a 
// Cylinder object.

public class Cylinder extends Circle {
	
	// additional class features/variables
	private double height = 1.0;
	
	// blank constructor, all defaults
	public Cylinder() {
		super();      // call the constructor for the superclass
		height = 1.0;
	}
	
	public Cylinder(double radius) {
		super(radius);
		this.height = 1.0;
	}
	
	public Cylinder(double radius, double height) {
		super(radius);
		this.height = height;
	}
	
	public Cylinder(double radius, double height, Point center) {
		super(new Point(center), radius);
		this.height = height;
	}
	
	public Cylinder(Cylinder otherCylinder) {
		// copying another superclass' data:
		// https://stackoverflow.com/a/52763184
		super(otherCylinder);
		this.height = otherCylinder.height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	// calculate Cylinder volume: PI * radius^2 * height
	public double getVolume() {
		return (Math.PI * Math.pow(super.getRadius(), 2) * this.height);
	}
	
	// overrided methods
	
	@Override
	public boolean equals(Object otherObj) {
		
		// same memory address
		if(this == otherObj)                    return true;
		
		// is the other object null?
		if(otherObj == null)                    return false;
		
		// was the other object once a point?
		if(getClass() != otherObj.getClass())   return false;
		
		// make the object a Cylinder again
		Cylinder other = (Cylinder) otherObj;
		
		return super.equals(other)                &&    // call equals method 
				                                        // from super class
		       this.height == other.height;
	}
	
	@Override
	public String toString() {
		return String.format("Cylander:%nHeight: %.3f%nBase: %s", this.height,
		                     super.toString());
	}
	
	
}
