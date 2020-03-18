// Patrick Temple
// Prof. Whitener
// CSCI165
// 20 March 2020

// Week 7 Lab: CircleJUnitTest
// Purpose: to test the Circle class

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
class CircleJUnitTest {
	
	// circles to test with
	Point aCenter = new Point(6, 6);
	Circle a      = new Circle(aCenter, 5);
	
	Point bCenter = new Point();
	Circle b      = new Circle(bCenter, 3);

	@Test
	void testGetArea() {
		
		// verify math with manually inputed math
		double expected = (Math.PI * Math.pow(5, 2));
		double actual   = a.getArea();
		assertEquals(expected, actual);
		
		expected = (Math.PI * Math.pow(3, 2));
		actual   = b.getArea();
		assertEquals(expected, actual);
		
	}

	@Test
	void testGetCircumference() {
		
		// verify math with manually inputed math
		double expected = (2 * Math.PI * 5);
		double actual   = a.getCircumference();
		assertEquals(expected, actual);
		
		expected = (2 * Math.PI * 3);
		actual   = b.getCircumference();
		assertEquals(expected, actual);
		
	}

	@Test
	void testDistance() {
		
		// for this, we need to calculate the
		// distance from a's center to b's center
		
		double expected = Math.sqrt(Math.pow((0 - 6), 2) + Math.pow(0 - 6, 2));
		double actual   = a.distance(b);
		assertEquals(expected, actual);
		
	}

	@Test
	void testEqualsObject() {
		
		Circle nullTester = null;
		String imposter = "Hello, my fellow Circles!";
		Circle copy = new Circle(a);
		
		// find a circle's reflection
		// (aka. is this circle equal to itself?)
		assertTrue(a.equals(a));
		
		// null object: not equal
		assertFalse(a.equals(nullTester));
		
		// find the object that's not a Circle
		assertFalse(a.equals(imposter));
		
		// an actual circle that is not equal
		assertFalse(a.equals(b));
		
		// a circle that is equal to another circle
		assertTrue(a.equals(copy));
	}

}
