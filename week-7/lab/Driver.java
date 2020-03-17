import java.util.ArrayList;

// Patrick Temple
// Prof. Whitener
// CSCI165
// 20 March 2020 (the same day the midterm
// was supposed to happen, but was cancelled
// due to a worldwide public health crisis)

// Week 7 Lab: Driver
// Purpose: to prove the cohesiveness of
// all of the functions

public class Driver {

	public static void main(String[] args) {
		
		// sequentially make point objects in a loop,
		// store them in an ArrayList
		ArrayList<Point> points = new ArrayList<Point>();
		
		for(int i = 1; i <= 10; i++) {
			Point temp = new Point(i, i);
			points.add(temp);
		}
		
		// now print them out
		System.out.println("Points:");
		
		for(int i = 0; i < 10; i++) {
			Point temp = points.get(i);
			System.out.println(temp.toString());
		}
		
	}

}
