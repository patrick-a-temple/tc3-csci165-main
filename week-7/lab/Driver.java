// Patrick Temple
// Prof. Whitener
// CSCI165
// 20 March 2020 (the same day the midterm
// was supposed to happen, but was cancelled
// due to a worldwide public health crisis)

// Week 7 Lab: Driver
// Purpose: to prove the cohesiveness of
// all of the functions

// imported classes
import java.util.ArrayList; // for storing arrays of objects


public class Driver {

	public static void main(String[] args) {
		
		// arraylist for objects
		ArrayList<Point>    points    = new ArrayList<Point>();
		ArrayList<Circle>   circles   = new ArrayList<Circle>();
		ArrayList<Cylinder> cylinders = new ArrayList<Cylinder>();
		
		// sequentially make point objects in a loop,
		// store them in an ArrayList
		for(int i = 1; i <= 10; i++) {
			Point temp = new Point(i, i);
			points.add(temp);
		}
		
		System.out.println("Points:");
		// print the points out
		for(int i = 0; i < 10; i++) {
			Point temp = points.get(i);
			System.out.println(temp.toString());
		}
		
		// make these points the center of
		// some newly created circles
		for(int i = 0; i < 10; i++) {
			Circle temp = new Circle(points.get(i), 1);
			circles.add(temp);
		}
		
		// now print them out
		System.out.println("\nCircles:");
		
		for(int i = 0; i < 10; i++) {
			Circle temp = circles.get(i);
			System.out.println(temp.toString());
		}
		
		// cylinders
		for(int i = 0; i < 10; i++) {
			Point    tempPoint = new Point(i, i);
			Cylinder temp = new Cylinder(1.0, 10.0, tempPoint);
			cylinders.add(temp);
		}
		
		System.out.println("\nCylinders:");
		
		for(int i = 0; i < 10; i++) {
			Cylinder temp = cylinders.get(i);
			System.out.println(temp.toString() + '\n');
		}
		
		// demonstrate changes of Circle
		// class functions in Cylinders
		
		// change centers by one and change
		// radius to 10
		for(int i = 0; i < 10; i++) {
			Cylinder temp      = cylinders.get(i);
			Point    tPoint = new Point((i + 1), (i + 1));
			
			// change the points and radius, then
			// save the new radius value
			temp.setRadius(10.0);
			temp.setCenter(tPoint);
			
			cylinders.remove(i);
			cylinders.add(i, temp);
		}
		
		System.out.println("After changing radius and centers:");
		
		for(int i = 0; i < 10; i++) {
			Cylinder temp = cylinders.get(i);
			System.out.println(temp.toString() + '\n');
		}
		
		// sandbox section
		ArrayList<Circle> tester = new ArrayList<Circle>(10);
		
		for(int i = 0; i < 5; i++) {
			Point tempPoint   = new Point(-(i), -(i));
			Circle tempCircle = new Circle(tempPoint, 5.0);
			tester.add(tempCircle);
		}
		
		for(int i = 5; i < 10; i++) {
			Point    tempPoint = new Point(-(i), -(i));
			Cylinder tempCyl   = new Cylinder(5.0, 33.3, tempPoint);
			tester.add(tempCyl);
		}
		
		System.out.println("Polymorphism example:");
		
		for(int i = 0; i < 10; i++) {
			Circle temp = tester.get(i);
			System.out.println(temp.toString());
		}
		
		// Polymorphic behavior must go down rather
		// than up
		
		// For a function to be called from a down-
		// stream cast, each class must have a function
		// to appropriately handle the process. This is
		// the same for the below code.
		
		ArrayList<Object> objectList = new ArrayList<Object>(9);
		
		Point  p = points.get(4);
		Circle circ = circles.get(5);
		Cylinder cyl = cylinders.get(6);
		Temperature temp = new Temperature(48.0);
		
		objectList.add(p);
		objectList.add(circ);
		objectList.add(cyl);
		objectList.add(temp);
		
		System.out.println("\nAssortment of objects:");
		
		for(int i = 0; i < 4; i++) {
			Object obj = objectList.get(i);
			System.out.println(obj.toString());
		}
		
		// Java knows what to do because each class has
		// a toString function that is overridden
		
	}

}
