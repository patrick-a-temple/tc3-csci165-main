// Patrick Temple
// Prof. Whitener
// CSCI165
// 3 February 2020

// Lab 2: Java Primitives
// Purpose: to experiment with Primitive types in Java

// Libraries:
import java.util.Scanner; // for inputs
import java.lang.Math;    // math library for pow() function

public class Primitives {

	public static void main(String[] args) {
		
		// assign primitive variables


		byte nom = 12;
		short stout = 450;
		int classic = 5432;
		long tallAndHandsome = 981_919_684;
		float buoy = 526.314F;
		double trouble = 321.5234;
		boolean onOrOff = true;
		char actualLetter = 'Q';
		char numberAsLetter = 82;

		// print out all of the variables
		System.out.printf("nom, the byte, is: %d%n", nom);
		System.out.printf("stout, the short, is: %d%n", stout);
		System.out.printf("classic, the int is: %d%n", classic);
		System.out.printf("tallAndHandsome, the long, is: %d%n", tallAndHandsome);
		System.out.printf("buoy, the float is: %f%n", buoy);
		System.out.printf("trouble, the double, is %f%n", trouble);
		System.out.printf("onOrOff, the boolean, is: %b%n", onOrOff);
		System.out.printf("actualLetter, the char (actual letter), is: %c%n", actualLetter);
		System.out.printf("numberAsLetter, the char (as a number), is: %c%n", numberAsLetter);
		
		// shortened type casting
		int numberK = 75; // value of capital K as a number (ASCII)
		char letterK = (char) numberK;
		
		// widened type casting
		int  veryBigInt = Integer.MAX_VALUE;  // integer at it's maximum capacity
		long veryBigLong = (long) veryBigInt; // transfer veryBig into a long variable
		
		// demonstrate type casting visibly to the user
		// int -> char: show the number and the character it translated to
		System.out.printf("%nThe number %d translates to the letter %c when casted from int to char.%n", numberK, letterK);
		System.out.printf("%nThe int veryBigInt cannot be added to after %d, but it can%nif converted to a long like veryBigLong.%n", veryBigInt);
		System.out.printf("To be clear, both variables equal %d before adding any numbers.%n", veryBigInt);
		System.out.printf("veryBigInt + 1 = %d, veryBigLong + 1 = %d%n", (veryBigInt + 1), (veryBigLong + 1));

		// Task 2
		Scanner reader = new Scanner(System.in); //open a Scanner object
		int baseOfExponent = 0;                  //number to be raised to different powers

		// ask for number to be inputted
		System.out.print("\nEnter an integer number to be raised to the second, third, and fourth powers: ");
		baseOfExponent = reader.nextInt(); // reads integer value inputted

		// make calculations
		double squared = Math.pow(baseOfExponent, 2);     // inputted number ^ 2
		double cubed = Math.pow(baseOfExponent, 3);       // inputted number ^ 3
		double fourthPower = Math.pow(baseOfExponent, 4); // inputted number ^ 4

		// print results
		System.out.printf("%n%d squared: %f%n", baseOfExponent, squared);
		System.out.printf("%d cubed: %f%n", baseOfExponent, cubed);
		System.out.printf("%d to the fourth power %f%n", baseOfExponent, fourthPower);
		
		// System.out.println("\n" + baseOfExponent + " squared: " + squared);
		// System.out.println(baseOfExponent + " cubed: " + cubed);
		// System.out.println(baseOfExponent + " to the fourth power: " + fourthPower);
		
		// Task 3.1
		
		int biggestInteger = Integer.MAX_VALUE;  // largest value for integer
		int smallestInteger = Integer.MIN_VALUE; // smallest value for integer

		// print the limits out
		System.out.println("\nBiggest integer value: " + biggestInteger);
		System.out.println("Smallest integer value: " + smallestInteger);
		
        // Task 3.2 - signed and unsigned methods
		System.out.println("\n****************");
		System.out.println("This program will now test the difference between two Java methods:");
		System.out.println("compare() and compareUnsigned(). When run, the program will say which");
		System.out.println("side is bigger by using 0 when both are equal, 1 if the left number");
		System.out.println("is bigger, or -1 if the number on the right is bigger.");
		System.out.println("****************\n");
		
		// testing signed methods (CONTROL GROUP)
		System.out.println("Signed: 5 and 5: " + Integer.compare(5, 5));      // all of these results seem to
		System.out.println("Signed: 10 and 5: "+ Integer.compare(10, 5));     // be mathmatically correct
		System.out.println("Signed: 5 and 10: " + Integer.compare(5, 10));
		System.out.println("Signed: -5 and 5: " + Integer.compare(-5, 5));
		System.out.println("Signed: 5 and -5: " + Integer.compare(5, -5));
		System.out.println("Signed: -10 and 5: " + Integer.compare(-10, 5));
		System.out.println("Signed: 10 and -5: " + Integer.compare(10, -5));
		System.out.println("Signed: -5 and 10: " + Integer.compare(-5, 10));
		System.out.println("Signed: 5 and -10: " + Integer.compare(5, -10));
		System.out.println("Signed: -10 and -5: " + Integer.compare(-10, -5));
		System.out.println("Signed: -5 and -10: " + Integer.compare(-5, -10));
		System.out.println("Signed: -5 and -5: " + Integer.compare(-5, -5));

		System.out.println("\nUnsigned: 0 and 0: " + Integer.compareUnsigned(5, 5)); // seemingly regular results...
        System.out.println("Unsigned: 10 and 5: "+ Integer.compareUnsigned(10, 5)); 
        System.out.println("Unsigned: 5 and 10: " + Integer.compareUnsigned(5, 10)); // until after this point
        System.out.println("Unsigned: -5 and 5: " + Integer.compareUnsigned(-5, 5));        // gives an anomolous response
        System.out.println("Unsigned: 5 and -5: " + Integer.compareUnsigned(5, -5));        // until noted (see comment below)
		System.out.println("Unsigned: -10 and 5: " + Integer.compareUnsigned(-10, 5));      // basically the comparisons
        System.out.println("Unsigned: 10 and -5: " + Integer.compareUnsigned(10, -5));      // are flipped
        System.out.println("Unsigned: -5 and 10: " + Integer.compareUnsigned(-5, 10));
        System.out.println("Unsigned: 5 and -10: " + Integer.compareUnsigned(5, -10));      // end anomolous responses at this line
        System.out.println("Unsigned: -10 and -5: " + Integer.compareUnsigned(-10, -5));
        System.out.println("Unsigned: -5 and -10: " + Integer.compareUnsigned(-5, -10));
        System.out.println("Unsigned: -5 and -5: " + Integer.compareUnsigned(-5, -5));

		// From what I can see, the signs are reversed when only one variable is negative.
		// For example, if I am comparing 5 and -10, unsigned methods would show me that
		// -10 was bigger, even if 5 was the actual biggest number according to real
		// arithmatic.
		
		// Task 4

		/* I was told to skip this question.
		float a;
		double b;

		// ask for two decimal numbers
		System.out.print("Enter a decimal number: ");
		a = reader.nextFloat();

		System.out.print("Enter another decimal number: ");
		b = reader.nextDouble();
		*/
		

		// Question 5
		// define variables

		int dividend = 0;  // number to be divided
		int divisor = 0;   // number to be divides the dividend
		int quotient = 0;  // result of division by sign
		int remainder = 0; // remainder of modulus division by sign

		int floorDivQuotient = 0;  // result of division by Math.floorDiv
		int floorModRemainder = 0; // result of modulus division my Math.floorMod
		
		// print instructions and obtain the dividend and divisor
		System.out.println("\nDivision:");
		System.out.println("Dividend ÷ Divisor = Quotient");

		System.out.println("___ ÷ ___ = ?");
		System.out.print("Enter a whole number dividend: ");
		dividend = reader.nextInt(); // get dividend

		System.out.println(dividend + " ÷ ___ = ?");
		System.out.print("Enter a whole number divisor: ");
		divisor = reader.nextInt();  // get divisor

		if(divisor == 0) { // divide by zero attempted
			System.out.println("ERROR: Attempt to divide by zero. Terminating program.");
			System.exit(1); // stop program immediately
		}
		else { // everything is OK
			// using typical division means
			quotient = dividend / divisor;
			remainder = dividend % divisor;
			System.out.println("Using normal signs:");
			System.out.printf("%d ÷ %d = %d, remainder of %d%n", dividend, divisor, quotient, remainder);

			// using Math.floorDiv and Math.floorMod
			// functions like using the regular signs
			floorDivQuotient = Math.floorDiv(dividend, divisor);
			floorModRemainder = Math.floorMod(dividend, divisor);

			System.out.println("\nUsing functions:");
			System.out.printf("%d ÷ %d = %d, remainder of %d%n", dividend, divisor, floorDivQuotient, floorModRemainder);
			
			// the class methods do the same thing as using / and %

		}

		// Close scanner object
		reader.close();
	}


}

