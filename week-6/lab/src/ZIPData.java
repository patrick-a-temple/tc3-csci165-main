import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6 Lab: ZIPData
// Purpose: to reduce the system impact
// created by only fetching zip data once each
// time the program is run.

// This was how I was trying to look up
// my zip codes without causing Address
// to use too much memory/CPU

public class ZIPData {
	
	// class feature: the ZIP database
	private String[][] data = new String[43000][3];
	
	public ZIPData() {
		
		fillZipData(); // this just calls the method
		               // to get the zip data
		
	}
	
	public void fillZipData() {
		
		
		try {
			
			// for the zip code file
			File       zipFile = new File("zip_code_database.csv");
			Scanner    reader  = new Scanner(zipFile);
			
			// get the first line (the heading)
			// so the function cannot
			String     dummy   = reader.nextLine();
			
			// the method below appears to be inefficient
			// when getting many zips at a time, and under
			// high stress, the program crashes
			for(int i = 0; reader.hasNext(); i++) {
				
				String   currentLine = reader.nextLine();
				String[] splitLine   = currentLine.split(",");
				this.data[i][0] = splitLine[0]; // get zip code
				
				// get city, remove quotes if needed
				if(splitLine[3].contains(" ") && splitLine[3].contains("\"")) {
					
					String cityNoQuote = splitLine[3].substring(1, (splitLine[3].length() - 1));
					this.data[i][1] = cityNoQuote;
					
				}
				else {
					this.data[i][1] = splitLine[3];
				}
				
				int currentIndex = 4;
				
				// skip acceptable cities
				if(splitLine[4].contains("\"")) {
					
					//currentIndex++; // 
					if(!(splitLine[currentIndex].charAt(splitLine[currentIndex].length() - 1) == '\"')) {
						currentIndex++;
						while(splitLine[currentIndex].contains("\"") == false) {
							currentIndex++;
						}
						currentIndex++;
					}
					else {
						currentIndex++;
					}
					
				}
				else {
					currentIndex++;
				}
				
				// skip unacceptable cities
				// if there is a " present
				if(splitLine[currentIndex].contains("\"")) {
					
					//currentIndex++; // add to 
					
					// see if the same splitLine location
					// has another " in it
					// below line causes StringOutOfBoundsException
					if(!(splitLine[currentIndex].charAt(splitLine[currentIndex].length() - 1) == '\"')) {
						currentIndex++;
						while(splitLine[currentIndex].contains("\"") == false) {
							currentIndex++;
						}
						currentIndex++;
					}
					
					/*else if(splitLine[currentIndex].isBlank()) {
						currentIndex += 2;
					}*/
					
					else {
						currentIndex++;
					}
					
				}
				else { // no "
					currentIndex++; // add in currentIndex
				}
				
				// now we can get the state!
				this.data[i][2] = splitLine[currentIndex];
			}
			
			// close Scanner object
			reader.close();
			
			
		}
		
		catch (FileNotFoundException error) {
			System.out.println("ERROR: In Address Class: Cannot find zip database file.");
		}
	}
	
	public String[][] getData() {
		return this.data;
	}
	
}
