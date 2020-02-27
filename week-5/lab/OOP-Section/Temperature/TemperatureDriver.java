// Patrick Temple
// Prof. Whitener
// CSCI165
// 28 February 2020

// Week 5 Lab: Temperature
// Purpose: to explore general OOP concepts,
// debugging, Java unit testing, and more
// as we make a program that deals with temperatures.

public class TemperatureDriver {

	public static void main(String[] args) {
		
		// make some Temperature instances
		Temperature drydenNY = new Temperature(46.0);                                  // setting degrees in constructor
		                                                                               // (defaults to F)
		Temperature mexicoNY = new Temperature();                                      // blank constructor
		Temperature snotFreezeUS = new Temperature(Temperature.tempUnit.F, -40.0);     // setting temp units and degrees
		                                                                               // (fake place, thought -40F was snot freezing point)
		
		Temperature calgaryABCanada = new Temperature(Temperature.tempUnit.C, 0.6);    // specifying celsius as a measure
		Temperature snotFreezeCanada = new Temperature(Temperature.tempUnit.C, -40.0); // (also fake name)
		
		
		// let's use some of the functions introduced
		// in temperature
		mexicoNY.setTemp(39.0);                                                       // change temp from 32 (default) to 39
		
		System.out.println("Weather report.");
		System.out.printf("In %s, it's %s%n", "Dryden, NY", drydenNY.toString());     // use toString to give nice format temperature
		System.out.printf("In Celsius: %f degrees%n", drydenNY.getTempInC());         // use getTempInC to get instant conversion
		System.out.printf("In Mexico, NY, it's %f degrees%n", mexicoNY.getTempInF()); // use getTempInF to get current F
		
		System.out.println("\nDoes -40°C = -40°F?");
		
		System.out.println((snotFreezeUS.equals(snotFreezeCanada)) ? "Yes" : "No");   // use equals to prove -40°C = -40°F
		
		System.out.println("\nIs it warmer in Dryden or Calgary?");
		int tempDifference = drydenNY.compareTo(calgaryABCanada);                     // get an integer from compareTo to
		                                                                              // see who's warmer right now
		
		if(tempDifference == 1) {
			System.out.println("Dryden is warmer");
		} // end if
		else if(tempDifference == -1) {
			System.out.println("Calgary is warmer");
		} // end else if
		else {
			System.out.println("They are both just as warm");
		} // end else
		
	}

}
