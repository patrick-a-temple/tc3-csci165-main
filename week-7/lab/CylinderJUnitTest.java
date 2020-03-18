import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class CylinderJUnitTest {
	
	// Cylinders to test
	Point aPoint = new Point(6, 6);
	Cylinder a   = new Cylinder(5.0, 10.0, aPoint);
	
	Cylinder copy = new Cylinder(a);
	
	Point bPoint = new Point(0, 0);
	Cylinder b   = new Cylinder(3.0, 5.0, bPoint);
	
	// fluke objects
	String imposter   = "CAN you please treat me as a cylinder?";
	Cylinder nullTest = null;
	

	@Test
	void testEqualsObject() {
		
		// see if an object is equal to itself
		assertTrue(a.equals(a));
		
		// can it find a null variable?
		assertFalse(a.equals(nullTest));
		
		// can it find a non-Cylinder?
		assertFalse(a.equals(imposter));
		
		// can it find when a variable is not
		// equal to another?
		assertFalse(a.equals(b));
		
		// can it find when a variable is
		// actually equal?
		assertTrue(a.equals(copy));
		
	}

	@Test
	void testGetVolume() {
		
		// test on cylinder a
		double expected = (Math.PI * Math.pow(5, 2) * 10);
		double actual   = a.getVolume();
		
		assertEquals(expected, actual);
		
		expected = (Math.PI * Math.pow(3, 2) * 5);
		actual   = b.getVolume();
		assertEquals(expected, actual);
		
	}

}
