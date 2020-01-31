// Patrick Temple
// Prof. Whitener
// CSCI165
// 30 January 2020

// LAB 2: Initials.java 
// Purpose: To obtain initials from a first
// and last name (as a single string) by
// using string methods

import java.lang.String;  // library for dealing with strings
import java.util.Scanner; // library for reading input

public class Initials {
	public static void main(String args[]) {
		
		Scanner reader = new Scanner(System.in); // Scanner object

		String name = "";        // the name about to be taken in
		int spaceHere = 0;       // where the space falls in the name
		char firstInitial = ' '; // first initial and last initials ... 
		char lastInitial = ' ';  // are saved into their own char variables

		// get the name
		System.out.print("Type your first and last name, then press Enter: ");
		name = reader.nextLine();

		spaceHere = name.indexOf(' ');              // get the location of the space
		firstInitial = name.charAt(0);              // get the first letter from the first name
		lastInitial = name.charAt((spaceHere + 1)); // get the first letter from the last name
		System.out.println("\nFirst initial: " + firstInitial + ", last initial: " + lastInitial);

		// convert initials to number by casting
		int firstAsNumber = (int)firstInitial;   // first letter as numerical ASCII value
                int lastAsNumber = (int)lastInitial;    // last letter as numerical ASCII value
		int initialSum = firstAsNumber + lastAsNumber;

		System.out.println("First initial's ASCII number: " + firstAsNumber + ", last initial's ASCII number: " + lastAsNumber);
		System.out.println("Both initial's ASCII numbers combined: " + initialSum);

		// close Scanner objects
		reader.close();
	}
}

// Ken: How am I doing with comments/styling? I looked up Oracle's
// Style Guide, and I adapted my code on that slightly.
