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
    public static void main(String[] args) {
        try {

            // input file and object
            File numberFile = new File("numbers.txt"); // file for numbers to be used
            Scanner numbers = new Scanner(numberFile); // how the numbers from numbers.txt file will be read
            
            // output object
            FileWriter exportTranslated = new FileWriter("encodedNumbers.txt");
            
            String[] toTranslate = new String[5000]; // holds lines from 
            
            
            int index = 0; // acts as array index for loop below
                           // and like a maximum number for the
                           // translation array

            // get the numbers and store them
            // into their own array, which will
            // be translated into KenSpeak
            while(numbers.hasNext()) {

                toTranslate[index] = numbers.nextLine();
                index++;

            } // end while

            numbers.close(); // close numbers, as we are done obtaining data

            String[] translated = new String[index];        // holds results
            String currentLine = "";                        // holds current line in following array
            String result = "";                             // temporarily holds result and 
            StringBuilder makeResult = new StringBuilder(); // holds strings as they are being concatenated

            for(int i = 0; i < index; i++){

                // if there is content in makeResult, wipe
                // everything to clear it for the next string
                if(makeResult.length() != 0) {
                    makeResult.delete(0, (makeResult.length()));
                } // end if

                currentLine = toTranslate[i]; // set string to be concatenated
                                              // from original array

                // the following algorithm is adapted from my original KSpeak program
                for(char c : currentLine.toCharArray()) {
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
                } // end secondary for (deals with switch statement)

                translated[i] = makeResult.toString();

            } // end primary for for (cycles through all elements in the array)

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