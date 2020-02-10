// Patrick Temple
// Prof. Whitener
// CSCI165
// 7 February 2020

// HW 2: KSpeakWithFiles
// Purpose: to use the same KenSpeak algorithm
// but with input and output files

// classes
import java.util.Scanner;             // for basic input
import java.io.FileWriter;            // for write files
import java.io.IOException;           // for if there is a problem writing a file
import java.io.File;                  // for File class to point to an existing file
import java.io.FileNotFoundException; // for if the file is missing
import java.lang.String;              // for dealing with strings
import java.lang.StringBuilder;       // for concatenating strings


class KSpeakWithFiles {

    // same function as KSpeak.java - also translates to KSpeak
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

    public static void main(String[] args) {
        try {

            // input file and object
            File numberFile = new File("numbers.txt"); // file for numbers to be used
            Scanner numbers = new Scanner(numberFile); // how the numbers from numbers.txt file will be read
            
            // object to make a new file
            FileWriter exportTranslated = new FileWriter("encodedNumbers.txt");
            
            String[] toTranslate = new String[5000]; // holds lines from file
            
            
            int index = 0; // acts as array index for loop below
                           // and like a maximum number for the
                           // translation array

            // get the numbers and store them
            // into their own array, which will
            // be translated into KenSpeak
            while(numbers.hasNext()) {

                toTranslate[index] = numbers.nextLine(); // add number string to original number array
                index++;                                 // increment index

            } // end while

            numbers.close(); // close numbers, as we are done obtaining data

            String[] translated = new String[index];        // holds results
            for(int i = 0; i < index; i++) {
                translated[i] = translate(toTranslate[i]);  // get translation thru function
                                                            // and save to translation array
            } // end for


            // save everything to the output file
            for(int i = 0; i < index; i++) {
                exportTranslated.write(translated[i] + "\n");
            } // end for

            // say the program is all done
            System.out.println("Success: Numbers from numbers.txt translated into KSpeak and sent to encodedNumbers.txt");
            
            exportTranslated.close(); // close scanner 

        } // end try

        catch (FileNotFoundException e) { // file not fould
            System.out.println("ERROR: numbers.txt cannot be found.");
        } // end catch

        catch (IOException e) { // cannot write a file
            System.out.println("ERROR: Cannot make a file.");
        } // end catch
    } // end main
} // end class