// Patrick Temple
// Prof. Whitener
// CSCI165
// 20 March 2020 (midterm cancelled)

// Week 7 Lab: PointJUnitTest
// Purpose: to test the Point Class

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PointJUnitTest {
	
	// variables to use in tests
	Point origin = new Point();
	Point five   = new Point(5, 5);
	Point six    = new Point(6, 6);
	Point copy   = new Point(five);
	Point upOnly = new Point(0, 9);
	

	@Test
	void testDistanceIntInt() {
		
		// see if calculations are right
		// by manually entering the correct math in
		double expected = Math.sqrt(Math.pow(5 - (-5), 2) + Math.pow(5 - (-5), 2));
		double actual   = five.distance(-5, -5);
		
		assertEquals(expected, actual);
		
		expected = Math.sqrt(Math.pow(5 - 5, 2) + Math.pow(5 - 5, 2));
		actual   = five.distance(5, 5);
		assertEquals(expected, actual);
		
	}

	@Test
	void testDistancePoint() {
		
		double expected = Math.sqrt(2);
		double actual   = five.distance(six);
		
		assertEquals(expected, actual);
		
	}

	@Test
	void testDistance() {
		
		double expected = 9;
		double actual   = upOnly.distance();
		
		assertEquals(expected, actual);
		
	}

	@Test
	void testEqualsObject() {
		
		assertTrue(five.equals(copy));
		assertFalse(five.equals(origin));
		assertFalse(upOnly.equals(six));
		
		// other scenerio: am I equal to myself?
		assertTrue(six.equals(six));
		
		// fluke cases:
		
		Point nullTester = null;
		String notPoint = "This is not a point! It should not pass.";
		
		assertFalse(six.equals(nullTester)); // null object
		
		assertFalse(six.equals(notPoint));   // not a Point
		
	}

}
