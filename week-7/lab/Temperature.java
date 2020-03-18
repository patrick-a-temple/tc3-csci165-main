// Patrick Temple
// Prof. Whitener
// CSCI165
// 28 February 2020

// Week 5 Lab: Temperature
// Purpose: to  define the Temperature class

// Added into week 7 for a sand box section

public class Temperature {
	
	// non-changeable features of object
	public static enum tempUnit { C, F } // the measures of temperatures
	                                     // (Celsius and Fahrenheit)
	
	// variables inside a Temperature object
	private double degrees = 32.0;
	private tempUnit unit = tempUnit.F;
	
	
	// class prototypes
	public Temperature() {} // blank prototype, defaults to
	                        // top declaration
	
	// prototype for temperature only
	public Temperature(double temp) {
		// this rounding method was changed to
		// work to one decimal digits, but is
		// adapted from
		// https://java2blog.com/java-round-double-float-to-2-decimal-places/
		temp = Math.round(temp * 10.0) / 10.0; // round to 10ths place
		degrees = temp;                        // set the temperature in the object
	} // end public Temperature (temperature)
	
	// prototype for unit of measure only
	public Temperature(tempUnit measure) {
		unit = measure; // set which unit of measure to use
	} // end public Temperature (measure)
	
	// complete prototype (unit of measure and temperature)
	public Temperature(tempUnit measure, double temp) {
		unit = measure;                        // set unit of measurement
		temp = Math.round(temp * 10.0) / 10.0; // round temperature
		degrees = temp;                        // then set the object's temperature
	} // end public Temperature (measure and temperature)
	
	
	// additional setter - changes temperature
	// to something in the same measurement range
	public void setTemp(double newTemp) {
		newTemp = Math.round(newTemp * 10.0) / 10.0; // round and then
		degrees = newTemp;                           // set it
	} // end setTemp (void)
	
	// getters
	public double getTempInF() {
		if(unit == tempUnit.F) { // already in F
			return degrees; // simply return the temperature
		} // end if
		
		else { // needs to be converted to F
			double conversion = (double) ((9 * degrees) / 5) + 32; // calculate correct value
			conversion = Math.round(conversion * 10.0) / 10.0;     // then round it
			return conversion;                                     // and finally return the result
		} // end if
	} // end getTempInF (double)
	
	public double getTempInC() {
		if(unit == tempUnit.C) { // already in C
			return degrees;      // return current value
		} // end if
		
		else { // conversion required
			double conversion = (double) 5 * (degrees - 32) / 9; // do conversion
			conversion = Math.round(conversion * 10.0) / 10.0;   // round the conversion result
			return conversion;
		} // end else
	} // end getTempInc (double)
	
	// change unit at any time
	public void changeUnit() {
		if(unit == tempUnit.F) { // covert to Celcius
			unit = tempUnit.C;                           // change unit of measure to C
			degrees = (double) 5 * (degrees - 32) / 9;   // do conversion
			degrees = Math.round(degrees * 10.0) / 10.0; // set the current degrees to the
			                                             // rounded version of the conversion
		} // end if
		
		else { // convert to Farenheight
			unit = tempUnit.F;                           // change unit of measure to F
			degrees = (double) ((9 * degrees) / 5) + 32; // do conversion
			degrees = Math.round(degrees * 10.0) / 10.0; // round the conversion and then reset
			                                             // the temperature
		} // end else
		
	} // end changeUnit (void)
	
	// checkers
	// the Temperature before the dot (when called
	// in the "Driver") counts as "this"
	public boolean equals(Temperature t) {
		
		if(this.unit != t.unit) { // not the same unit - conversion required
			
			// change the unit of the variable to the
			// unit that "this" is set to
			if(this.unit == tempUnit.F) {
				double conversion = t.getTempInF(); // get conversion
				if(this.degrees == conversion) { // conversion matches this.degrees
					return true;
				} // end if
				else { // conversion does not match
					return false;
				} // end else
			} // end if
			else {
				double conversion = t.getTempInC(); // get conversion
				
				// do they match?
				if(this.degrees == conversion) {
					return true;
				} // end if
				else {
					return false;
				} // end else
			} // end else
		} // end if
		
		// both are at the same unit,
		// so are they equal?
		else if(this.degrees == t.degrees) {
			return true;
		} // end else if
		else {
			return false;
		} // end else
		
	} // end equals (boolean)
	
	public int compareTo(Temperature t) {
		if(this.unit != t.unit) { // they are not the same unit
			
			// do a unit conversion then do the
			// comparison
			double conversion = 0.0;
			if(this.unit == tempUnit.F) {
				conversion = t.getTempInF();
			} // end if
			else {
				conversion = t.getTempInC();
			} // end else
			
			if(this.degrees > conversion) { // this.degrees is more
				return 1;
			} // end if
			else if(this.degrees < conversion) { // this.degrees is less
				return -1;
			} // end else if
			else { // they both are the same
				return 0;
			} // end 
		}
		
		else { // they are the same unit
			if(this.degrees > t.degrees) { // this.degrees is more
				return 1;
			}
			else if(this.degrees < t.degrees) { // this.degrees is less
				return -1;
			}
			else { // they both are the same
				return 0;
			}
		}
	}
	
	// make a string that has the temperature and
	// the unit at the end, then return it
	@Override
	public String toString() {
		String current = "";
		current = degrees + "°" + (unit == tempUnit.C ? 'C' : 'F');
		
		return current;
	}
	
	
}
