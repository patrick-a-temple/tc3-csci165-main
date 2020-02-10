// Patrick Temple
// Prof. Whitener
// CSCI165
// 10 February 2020 - HEAVILY REVISED

// Revision reason: needed to eliminate switch statements
// which were not allowed

// Week 2 HW: KSpeak
// Purpose: to transliterate numbers
// into "KenSpeak", inputted from the terminal

// classes
import java.util.Scanner;       // for input
import java.lang.String;        // for special string methods
import java.lang.StringBuilder; // for concatenating strings

public class KSpeak {

    public static String translate(String original) { // string translation engine
        // language diagram
        String codes = "*BEA@FK%RM";   // current translation pattern, ex. 1 = B

        // variables used in translation
        String translated = "";        // holds result at the end
        char currentNumAsChar = ' ';   // temporarily holds current number but as ASCII value
        int currentNumber = 0;         // holds the actual number to be translated
        char currentTranslation = '*'; // holds the current KSpeak value aligned with CurrentNumber

        // what constructs my strings
        StringBuilder makeTranslation = new StringBuilder();

        // make the translation
        for(int i = 0; i < (original.length()); i++) {
            currentNumAsChar = original.charAt(i);                       // get current index's number from 
                                                                         // string to be translated
            currentNumber = Character.getNumericValue(currentNumAsChar); // store the above result as an actual
                                                                         // number, not an ASCII value
            currentTranslation = codes.charAt(currentNumber);            // get the correct translation by matching
                                                                         // the number with the correct translation
            makeTranslation.append(currentTranslation);                  // append onto string builder
        } // end for

        translated = makeTranslation.toString(); // unload the string builder into a string
        return translated;                       // finally return the value, and return to main
    } // end function translate

    public static void main(String[] args){

        // Scanner for input
        Scanner reader = new Scanner(System.in);

        // Get the string of numbers
        System.out.println("Welcome to KenSpeak™: Terminal Edition");
        System.out.print("\nEnter a line of numbers to be translated: ");
        String toTranslate = reader.nextLine();
        
        // get solution
        String solution = translate(toTranslate);                // goes to translate function above
        System.out.printf("Solution should be: %s%n", solution); // print result

        reader.close(); // close input reader


    } // end main
} // end public class