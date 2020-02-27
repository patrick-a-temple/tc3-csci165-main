import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// Patrick Temple
// Prof. Whitener
// CSCI165
// 28 February 2020

// Week 5 Lab: Temperature
// Purpose: to validate the output from
// various functions using unit testing

class TemperatureJUnitTest {

	
	
	// objects used in testing
	Temperature adjustMe = new Temperature(Temperature.tempUnit.F);
	
	Temperature a = new Temperature(Temperature.tempUnit.C, -40.0);
	Temperature b = new Temperature(Temperature.tempUnit.F, -40.0);
	
	Temperature c = new Temperature(Temperature.tempUnit.C, 50.0);
	Temperature d = new Temperature(Temperature.tempUnit.F, 50.0);
	
	Temperature boiling = new Temperature(Temperature.tempUnit.C, 100.0);
	
	@Test
	void testSetTemp() { // this just sets the temp if someone wanted to change it
		
		double correct = 100.0;                      // the correct value
		adjustMe.setTemp(100.0);                     // run function to test
		double resultToTest = adjustMe.getTempInF(); // get the result the program...
		                                             // thinks should be right
		assertEquals(correct, resultToTest);         // begin testing
		
	}
	
	// test getTempIn(measurement)'s conversions
	// by using the opposite measurement
	@Test
	void testGetTempInF() {
		
		double correct = -40.0;
		double resultToTest = b.getTempInF();
		assertEquals(correct, resultToTest);
		
	}

	@Test
	void testGetTempInC() {
		
		double correct = -40.0;
		double resultToTest = a.getTempInC();
		assertEquals(correct, resultToTest);
		
	}
	
	// test changing the unit of measure
	@Test
	void testChangeUnit() {
		
		double correct = 212.0;                     // boiling in F
		
		// run function on boiling
		boiling.changeUnit();                       // change boiling (in C) to F
		double resultToTest = boiling.getTempInF(); // get the temp in F after calculating
		assertEquals(correct, resultToTest);        // check the results
		
	}
	
	// see if equals can tell if something
	// is not equal
	@Test
	void testEqualsTemperature() {

		boolean correct = false;
		boolean resultToTest = b.equals(d);
		assertEquals(correct, resultToTest);
		
	}
	
	// see if compareTo can find higher values (boiling)
	// when compared to a lower value (c)
	@Test
	void testCompareTo() {
		
		int correct = 1;
		int resultToTest = boiling.compareTo(c);
		assertEquals(correct, resultToTest);
		
	}
	
	// test the behaviors of toString
	@Test
	void testToString() {
		
		String correct = "-40.0°F";
		String resultToTest = b.toString();
		assertEquals(correct, resultToTest);
		
	}

}
