// Patrick Temple
// Prof. Whitener
// CSCI165
// 17 January 2020

// Purpose: to work with numbers in
// row-major order (like reading a book)

// classes
import java.util.Scanner;             // basic inputs from user and file
import java.io.File;                  // for the File class type
import java.io.FileNotFoundException; // for if there is no number list file


public class twoDArray {
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in); // input for row/column choice
		
		// load array
		int[][] numbers = new int[50][20]; // 50 rows of 20 columns
		fillArray(numbers);                // go to fillArray (void)
		
		// preview array
		System.out.println("Matrix loaded, now previewing:\n");
		printAllRows(numbers); // go to printAllRows (void)
		
		// obtain max number in entire matrix overall
		int maxResult = findMax(numbers);                         // go to findMax (int) and store result
		System.out.printf("Maximum of array: %d%n%n", maxResult); // print result
		
		// obtain min number in entire matrix overall
		int minResult = findMin(numbers);                         // go to findMin (int) and store result
		System.out.printf("Minimum of array: %d%n%n", minResult); // print result
		
		// maximums and minimums in rows //
		
		// get the row the user wants tested for
		// both minimums and maximums
		System.out.print("Enter the row to find the maximum and minimum in: ");
		int selectRow = reader.nextInt(); // save desired row here
		
		// find, print max and min for specified rows
		int maxRow = findMaxOfRow(numbers, (selectRow - 1));               // go to findMaxOfRow (int) and store result
		System.out.printf("Maximum of row %d: %d%n%n", selectRow, maxRow); // print result
		
		int minRow = findMinOfRow(numbers, (selectRow - 1));               // go to findMinOfRow (int) and store result
		System.out.printf("Minimum of row %d: %d%n", selectRow, minRow);   // print result
		
		// maximums and minimums in rows //
		
		// get the column the user wants tested for
		// both minimums and maximums
		System.out.print("\nEnter the column to find the maximum and minimum in: ");
		int selectColumn = reader.nextInt(); // save desired column number
		
		// find, print max and min for specified rows
		int maxColumn = findMaxOfColumn(numbers, (selectColumn - 1));               // go to findMinOfRow (int) and store result
		System.out.printf("Maximum of column %d: %d%n%n", selectColumn, maxColumn); // print result
		
		int minColumn = findMinOfColumn(numbers, (selectColumn - 1));               // go to findMinOfRow (int) and store result
		System.out.printf("Minimum of column %d: %d%n", selectColumn, minColumn);   // print result
		
		
		reader.close(); // close scanner object
	} // end main
	
	public static void fillArray(int matrix[][]) {
		try {
			
			File numberList = new File("number_list.txt"); // points to number list
			Scanner getNumbers = new Scanner(numberList);  // for scanning the number list
			
			// load array from file:
			// 50 rows (i) of 20 columns (j) 
			for(int i = 0; i < 50; i++) {
				
				for(int j = 0; j < 20; j++) {
					
					matrix[i][j] =  getNumbers.nextInt();
					
				} // end second level for
				
			} // end for
			getNumbers.close(); // close file scanner
			
		} // end try
		
		catch (FileNotFoundException e) { // files not found
			System.out.println("ERROR: File not found");
		} // end catch
		
	} // end fillArray (void)
	
	public static void printAllRows(int matrix[][]) {
		
		// print out the rows in the array
		for(int i = 0; i < 50; i++) {
			
			for(int j = 0; j < 20; j++) {
				
				System.out.printf("%d ", matrix[i][j]);
				
			} // end secondary for
			System.out.print('\n'); // print a new line
			
		} // end for
		System.out.print('\n'); // end with a new line
		
	} // end printAllRows (void)
	
	public static int findMax(int[][] matrix) {
		
		int maximum = Integer.MIN_VALUE; // for setting the bar in terms of maximums
		
		// find the maximum number in the entire array
		for(int i = 0; i < 50; i++) {
			
			for(int j = 0; j < 20; j++) {
				
				if(matrix[i][j] >= maximum) { // if higher number found
					
					System.out.printf("HIT: %d >= %d%n", matrix[i][j], maximum);
					maximum = matrix[i][j]; // save the new maximum
					
				} // end if 
				
			} // end secondary for
		} // end for
		
		return maximum; // go back to main
	} // end findMax (int)
	
	public static int findMin(int[][] matrix) {
		int minimum = Integer.MAX_VALUE;
		
		// find the minimum array in the entire array
		for(int i = 0; i < 50; i++) {
			
			for(int j = 0; j < 20; j++) {
				
				if(matrix[i][j] <= minimum) { // lower number found
					
					System.out.printf("HIT: %d <= %d%n", matrix[i][j], minimum);
					minimum = matrix[i][j]; // save the new minimum
					
				} // end if
				
			} // end secondary for
		} // end for
		
		return minimum; // go back to main
	} // end findMin (int)
	
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
				
				System.out.printf("HIT: In row %d, %d >= %d%n", (row + 1), matrix[row][j], rowMax);
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
				
				System.out.printf("HIT: In row %d, %d >= %d%n", (row + 1), matrix[row][j], rowMin);
				rowMin = matrix[row][j]; // save new minimum for the row
				
			} // end if
			
			
		} // end for
		
		return rowMin; // go back to main
		
	} // end findMinOfRow (int)
	
	public static int findMaxOfColumn(int[][] matrix, int column) {
		int colMax = Integer.MIN_VALUE;
		
		// find the maximum number in a column
		
		// like the maxes and mins of rows, we only
		// need i as we have a column specified to look
		// for a maximum
		for(int i = 0; i < 50; i++) {
			
			if(matrix[i][column] >= colMax) { // new max in column found
				
				System.out.printf("HIT: In column %d, %d >= %d%n", (column + 1), matrix[i][column], colMax);
				colMax = matrix[i][column]; // save new max for column
				
			} // end if
			
		} // end for
		
		return colMax; // go back to main
	} // end findMaxOfColumn (int)
	
	public static int findMinOfColumn(int[][] matrix, int column) {
		int colMin = Integer.MAX_VALUE;
		
		// find the minimum array in a column
		for(int i = 0; i < 50; i++) {
			
			if(matrix[i][column] <= colMin) { // new min in column found
				
				System.out.printf("HIT: In column %d, %d <= %d%n", column, matrix[i][column], colMin);
				colMin = matrix[i][column]; // save new min for column
				
			} // end if
			
		} // end for
		
		return colMin;
	} // end findMinOfColumn(int)
	
} // end class
