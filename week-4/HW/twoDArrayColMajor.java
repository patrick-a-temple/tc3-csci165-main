// Patrick Temple
// Prof. Whitener
// CSCI165
// 17 January 2020

// Purpose: to fill an array in column-major
// order (reading up to down), then do several
// different checks on the numbers.

// classes
import java.util.Scanner;             // for input including files
import java.io.File;                  // for File class type
import java.io.FileNotFoundException; // for if the text file is not found

public class twoDArrayColMajor {
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in); // for basic input
		int numbers[][] = new int[50][20];       // holds numbers from number_list.txt
		
		fillArray(numbers);    // go to fillArray (void) - fills numbers[][]
		                       // from number_list.txt
		printAllRows(numbers); // go to printAllRows (void) - prints all
		                       // rows in array
		
		// ask for row they would like to see
		System.out.print("Enter the row you would like displayed from the matrix: ");
		int selectRow = reader.nextInt();
		
		// ask how many columns they would
		// like the desired row to be in
		System.out.print("Enter how many columns you would like the result to be displayed in: ");
		int columns = reader.nextInt();
		
		printRow(numbers, (selectRow - 1), columns); // go to printRow (void) - prints row
		                                             // from specified location in a desired
		                                             // number of columns
		
		// show the row with the lowest amount of change
		int lowestRow = smallestChange(numbers);
		System.out.printf("Smallest row of change at: %d%n", (lowestRow + 1));
		
		
		reader.close(); // close the reader object
		
	} // end main
	
	public static void fillArray(int[][] array) {
		
		// load array in a column-major fashion
		// that is, read each column from top to bottom,
		// then move to the right
		try {
			File numberList = new File("number_list.txt"); // file location here
			Scanner getNumber = new Scanner(numberList);
			
			// j and i are flipped to load the
			// array in column major fashion
			
			for(int j = 0; j < 20; j++) {
				
				for(int i = 0; i < 50; i++) {
					
					array[i][j] = getNumber.nextInt(); 
					
				} // end secondary for
				
			} // end for
			
			getNumber.close();
			
		} //  end try
		
		catch (FileNotFoundException e) {
			System.out.println("ERROR: Cannot find file.");
		} // end catch
		
	}
	
	// print the rows from the array
	public static void printAllRows(int array[][]) {
		
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 20; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.print('\n');
		}
		System.out.print('\n');
		
	}
	
	// print only the specified row, and format
	// the result into a desired number of columns
	public static void printRow(int[][] matrix, int row, int num_cols) {
		
		for (int j = 0; j < 20; j++) {
			System.out.print(matrix[row][j] + " ");
			
			// skip to next line if the program has
			// made two columns in one row
			// (not sure how to correctly describe it in words)
			
			if((j + 1) % num_cols == 0) {
				System.out.print('\n'); // go to the next line
			}
		}
		
	}
	
	// find the row that has the smallest change by
	// going through every row, collecting their maximums
	// and minimums, calculating the differences between the
	// two, and keep track of what the smallest difference is
	// (which the smallest difference will be returned)
	public static int smallestChange(int[][] matrix) {
		
		int[] change = new int[50];               // holds the changes after max - min is found
		int lowestChangeRow = 0;                  // holds lowest current row (which will be returned)
		int currentMax = 0;                       // holds value of current row's maximum number
		int currentMin = 0;                       // holds value of current row's minimum number
		int overallChangeLow = Integer.MAX_VALUE; // holds the lowest difference to compare to
		                                          // other rows
		
		for(int i = 0; i < 50; i++) {
			currentMax = findMaxOfRow(matrix, i); // get the max of current row
			                                      // (go to findMaxOfRow (int))
			currentMin = findMinOfRow(matrix, i); // get the min of current row
			                                      // (go to findMinOfRow (int))
			change[i] = currentMax - currentMin;  // calculate difference between
			                                      // max and min to get change
			
			// state current amount of change
			System.out.printf("Row %d: change in row is: %d%n", (i + 1), change[i]);
			
			if (change[i] < overallChangeLow) { // new minimum found
				System.out.printf("LCR HIT at row %d: %d <= %d%n", (i + 1), change[i], overallChangeLow);
				overallChangeLow = change[i]; // set new smallest change
				lowestChangeRow = i;          // note which row had the smallest change
			}
		}
		
		
		
		
		
		return lowestChangeRow;
	}
	
	// the following two functions were adapted from part
	// two for some of the tasks done in smallestChange (int)
	public static int findMaxOfRow(int[][] matrix, int row) {
		
		// find the maximum number in a row
		int rowMax = Integer.MIN_VALUE; // use this value to hold the maximum
		                                // and to compare values as the program
		                                // continues to look through the array
		
		// we only need to use j because
		// we know we only want to look at
		// one row, and that row was passed in
		for(int j = 0; j < 20; j++) {
			
			if(matrix[row][j] >= rowMax) { // new maximum in row found
				
				rowMax = matrix[row][j]; // save new maximum for the row
				
			} // end if
			
		} // end for
		
		return rowMax; // go back to main
		
	} // end findMaxOfRow (int)
	
	public static int findMinOfRow(int[][] matrix, int row) {
		
		int rowMin = Integer.MAX_VALUE;
		
		// find the minimum array in a row
		for(int j = 0; j < 20; j++) {
			
			if(matrix[row][j] <= rowMin) { // new minimum in row found
				
				rowMin = matrix[row][j]; // save new minimum for the row
				
			} // end if
			
			
		} // end for
		
		return rowMin; // go back to main
		
	} // end findMinOfRow (int)
	
	
} // end class
