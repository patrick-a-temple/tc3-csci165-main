// Patrick Temple
// Prof. Whitener
// CSCI165
// 7 February 2020

// Week 2 HW: KSpeak
// Purpose: to transliterate numbers
// into "KenSpeak", inputted from the terminal

// classes
import java.util.Scanner;       // for input
import java.lang.String;        // for special string methods
import java.lang.StringBuilder; // for concatenating strings

public class KSpeak {
    public static void main(String[] args){

        // Scanner for input
        Scanner reader = new Scanner(System.in);

        // Get the string of numbers
        System.out.println("Welcome to KenSpeak™: Terminal Edition");
        System.out.print("\nEnter a line of numbers to be translated: ");
        String toTranslate = reader.nextLine();
        
        // get length of inputted numbers
        int lengthOfInput = toTranslate.length();
        
        // see if there are letters or characters with a loop
        // ( adapted from https://stackoverflow.com/a/18591352 )
        for(char c : toTranslate.toCharArray()) { // for all characters in inputted numbers

            if(!(Character.isDigit(c))) { // character/letter found
                System.out.println("ERROR: No letters or characters allowed in input.");
                System.out.println("This program will be terminated.");
                System.exit(1); // terminate program immediately
            } // end if

        } // end for
        
        // translate the numbers by running the numbers
        // through a switch statement
        StringBuilder makeResult = new StringBuilder(); // handles concatenation
        String translated = ""; // this is where the result lands later
        for(char c : toTranslate.toCharArray()) {
            switch (c) {

                // for each matching character, add on
                // the appropriate translated character
                // to translate into KenSpeak
                case '0': {
                    makeResult.append('*'); // add a * into the StringBuilder
                    break; // skip the rest of the statements
                } // end case 0

                // each case works the same as the
                // first case
                case '1': {
                    makeResult.append('B');
                    break;
                } // end case 1

                case '2': {
                    makeResult.append('E');
                    break;
                } // end case 2

                case '3': {
                    makeResult.append('A');
                    break;
                } // end case 3

                case '4': {
                    makeResult.append('@');
                    break;
                } // end case 4

                case '5': {
                    makeResult.append('F');
                    break;
                } // end case 5

                case '6': {
                    makeResult.append('K');
                    break;
                } // end case 6

                case '7': {
                    makeResult.append('%');
                    break;
                } // end case 7

                case '8': {
                    makeResult.append('R');
                    break;
                } // end case 8

                case '9': {
                    makeResult.append('M');
                    break;
                } // end case 9

                // if there was a problem that was not
                // handled prior to this, print a message
                // to the console
                default: {
                    System.out.println("Something is wrong with a statement, and this text should not be showing.");
                    break;
                } // end default case

            } // end switch

        } // end for statement

        // give the concatenated string to the user
        translated = makeResult.toString();
        System.out.printf("Translated number: %s%n", translated);

        reader.close();


    } // end main
} // end public class