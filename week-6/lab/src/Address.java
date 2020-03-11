// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6 and 7 Lab: Address
// Purpose: to define address class

// imported Java classes
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Address {
	
	// class features
	private String street;
	private String city;
	private String state;
	private String zip;
	
	// object that holds all zip codes
	// from a CSV file
	private String[][] zipData = new String[43000][3];
	
	
	// blank constructor
	public Address() {  }
	
	public Address(String loadStreet, String loadZip) {
		
		zipData = fillZipData(); // fill zip code data
		setStreet(loadStreet);   // load the street
		
		// query the zip code list, and get the
		// town and state
		String zipQuery[] = new String [2];
		zipQuery = lookupTownAndState(loadZip);
		
		// save results from zip query
		setCity(zipQuery[0]);
		setState(zipQuery[1]);
		setZip(loadZip); // save the zip
		
	} // end complete Address constructor
	
	// copy constructor
	public Address(Address toCopy) {
		this.street = toCopy.street;
		this.city   = toCopy.city;
		this.state  = toCopy.state;
		this.zip    = toCopy.zip;
	}
	
	// get info for cities and zips
	private String[][] fillZipData() {
		
		String[][] data = new String[43000][3];
		
		try {
			
			// for the zip code file
			File       zipFile = new File("zip_code_database.csv");
			Scanner    reader  = new Scanner(zipFile);
			
			// get the first line (the heading)
			// so the function cannot
			String     dummy   = reader.nextLine();
			
			
			for(int i = 0; reader.hasNext(); i++) {
				
				String   currentLine = reader.nextLine();
				String[] splitLine   = currentLine.split(",");
				data[i][0] = splitLine[0]; // get zip code
				
				// get city, remove quotes if needed
				if(splitLine[3].contains(" ") && splitLine[3].contains("\"")) {
					
					String cityNoQuote = splitLine[3].substring(1, (splitLine[3].length() - 1));
					data[i][1] = cityNoQuote;
					
				}
				else {
					data[i][1] = splitLine[3];
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
				data[i][2] = splitLine[currentIndex];
			}
			
			// close Scanner object
			reader.close();
			
			
		}
		
		catch (FileNotFoundException error) {
			System.out.println("ERROR: In Address Class: Cannot find zip database file.");
		}

		return data;
	}
	
	private String[] lookupTownAndState(String zip) {
		
		String[] results = new String[2];
		int foundAt = -1;
		for(int i = 0; i < 42632; i++) {
			if(zipData[i][0].equals(zip)) {
				foundAt = i;
			}
		}
		if(foundAt < 0) {
			results[0] = "Not Found";
			results[1] = "NA";
		}
		else {
			results[0] = zipData[foundAt][1]; // city
			results[1] = zipData[foundAt][2]; // state
		}
		
		return results;
		
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public boolean equals(Address anotherAddress) {
		
		return this.street.equals(anotherAddress.street) &&
			   this.city.equals(anotherAddress.city)     &&
			   this.state.equals(anotherAddress.state)   &&
			   this.zip.equals(anotherAddress.zip);
		
	}

	@Override
	public String toString() {
		
		String result = "";
		result = String.format("%s, %s, %s %s", street, city, state, zip);
		return result;
		
	}
	
	
	
}
