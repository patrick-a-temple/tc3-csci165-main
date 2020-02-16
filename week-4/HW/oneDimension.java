// Patrick Temple
// Prof. Whitener
// CSCI165
// 17 February 2020

// Purpose: to fill arrays from a text file with numbers
// and to do various sorting tasks with the loaded numbers

// Function definitions are not needed for functions
// below main in Java, unlike C++ (note to self)

// classes
import java.util.Scanner;             // for getting numbers from number_list.txt
import java.io.File;                  // for including the File type
import java.io.FileNotFoundException; // for if number_list.txt is missing



class oneDimension {
    public static void main(String[] args) {
        
        // define array to hold numbers and have it filled
        int[] numbers = new int[1000];
        fillArray(numbers); // go to (void) fillArray function

        // get the maximum number from the array
        int maxResult = findMax(numbers);              // go to (int) findMax function
        System.out.printf("Maximum: %d%n", maxResult); // print the result

        // get the minimum result
        int minResult = findMin(numbers);              // go to (int) findMin function
        System.out.printf("Minimum %d%n", minResult);  // print the result

        // get the percent changes and store them
        // in an array
        int[] changes = new int[1000];                 // array for storing the percent changes
        changes = percentChange(numbers);              // go to (int[]) percentChange function

    } // end main

    public static void fillArray(int[] array) {
        try {

            // File and Scanner variables
            File numberList = new File("number_list.txt");
            Scanner getNumbers = new Scanner(numberList);

            // get the contents of number_list.txt
            for(int i = 0; i < 1000; i++) {
                array[i] = getNumbers.nextInt();
            } // end for
            
            getNumbers.close(); // close the Scanner

        } // end try
        
        catch (FileNotFoundException e) { // file not found
            System.out.println("ERROR: File not found");
        } // end catch

    } // end fillArray

    public static int findMax(int[] array) {

        int maximum = Integer.MIN_VALUE; // stores maximum number, our result to be
                                         // returned, using MIN_VALUE, to state the
                                         // absolute minimum value for an integer
        
        // check for highest number in array
        for(int i = 0; i < 1000; i++) {
            if(array[i] >= maximum) {
                maximum = array[i];
            }
        }

        return maximum; // return the maximum to
                        // the variable in main

    } // end findMax

    public static int findMin(int[] array) {
        int minimum = Integer.MAX_VALUE; // stores minimum number, our result to be
                                         // returned, to MAX_VALUE, to state the
                                         // absolute minimum value for an integer


        // check for lowest number in the array,
        // then store it if one is found
        for(int i = 0; i < 1000; i++) {
            if(array[i] <= minimum) {
                minimum = array[i];
            }
        }

        return minimum; // return the minimum to
                        // the variable in main

    } // end findMin

    public static int[] percentChange(int[] array) {

        // make a temporary variable for
        // holding the percent changes
        int storeChanges[] = new int[1000];

        int previousIndex = 0; // make a variable to compare the
                               // last index to the current index
                               // in the following loop

        for(int i = 1; i < 1000; i++) {

            previousIndex = array[i - 1]; // get previous index
            
            // do math on percent change and
            // write to the array
            storeChanges[i] = (int) (100.00 * ((double)(array[i] - previousIndex) / previousIndex));
        }

        return storeChanges; // return array contents to
                             // the specified array in main
        
    } // end percentChange
}