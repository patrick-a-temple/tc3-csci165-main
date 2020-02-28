// Patrick Temple
// Prof. Whitener
// CSCI165
// 28 February 2020

// Week 5 Lab: ElectionDriver
// Purpose: to create an ArrayList of
// CountyResults2016 objects, and to get
// various useful data from the CR2016 objects.

import java.util.ArrayList;           // for holding out CR2016 objects in one place
import java.util.Scanner;             // for reading data from the 
import java.io.File;                  // for the File class
import java.io.FileNotFoundException; // for when files hide or disappear

public class ElectionDriver {

	public static void main(String[] args) {
		
		// create and fill array for results
		ArrayList<CountyResults2016> results = new ArrayList<CountyResults2016>();
		fillList(results); // go to fillList (void)
		
		// find the largest margin of victory in the
		// entire US, make an object for the county it came from, 
		// and call its toString() function
		CountyResults2016 lgMarginOverall = findLargestMargin(results);
		System.out.println(lgMarginOverall.toString() + "\n");
		
		// find the largest margin of victory in Rhode
		// Island, make an object for the county it came from,
		// and call its toString() function
		CountyResults2016 lgMarginInRI = findLargestMargin(results, "RI");
		System.out.println(lgMarginInRI.toString() + "\n");
		
		// get all of the state totals and store
		// them in a string array
		String[] stateStats = new String[60];
		stateStats = getStateTotals(results);
		
		System.out.println(stateStats[35]);  // example: NYS
		
	} // end main
	
	public static void fillList(ArrayList<CountyResults2016> list) {
		
		try {
			
			// open the file, make a scanner, and skip the
			// first line
			File rawElectionResults = new File("2016_US_County_Level_Presidential_Results.csv");
			Scanner readResults = new Scanner(rawElectionResults);
			String dummy = readResults.nextLine(); // ignore the heading on the CSV file
			
			while(readResults.hasNext()) {
				
				
				String currentLine = readResults.nextLine(); // get the next line
				String[] splitLine = new String[25];         // array to store the result
				                                             // of splitting currentLine by
				                                             // commas
				splitLine = currentLine.split(",");          // separate the values
				
				// store the comma separated values into an
				// appropriate variable
				
				// splitLine[0] will not need to be
				// recieved, this is a row number
				
				double demVotes = Double.valueOf(splitLine[1]);
				double gopVotes = Double.valueOf(splitLine[2]);
				double totalVotes = Double.valueOf(splitLine[3]);
				double percentDem = Double.valueOf(splitLine[4]);
				double percentGOP = Double.valueOf(splitLine[5]);
				
				// we have a predicament: the difference is
				// encapsulated in quotes, and comma separation
				// has fragmented them all. we need to remove the
				// quotes and bring them together all in one double.
				
				// array locations can get tricky coming up,
				// so currentIndex will note where to go for
				// the next information
				int currentIndex = 6;
				
				double difference = 0.0; // to store result after these if statements
				
				if((splitLine[6].contains("\"")) == false) {   // no quotes, up to 3 digit margin
					
					difference = Double.valueOf(splitLine[6]); // simply get the variable
					                                           // from splitLine and make it a double
					currentIndex = 7;                          // number to continue looking for info
					                                           // is 7
				} // end if
				else if(splitLine[6].contains("\"") && splitLine[7].contains("\"")) { // up to six digit difference
					
					String firstHalf = splitLine[6];          // get the first half
					firstHalf = firstHalf.substring(1);       // remove the quote mark at the beginning
					String secondHalf = splitLine[7];         // do the same with the second half...
					secondHalf = secondHalf.substring(0, 3);  // except remove the " from the end
					String combined = firstHalf + secondHalf; // concatenate the two halfs...
					difference = Double.valueOf(combined);    // and make it a double
					
					currentIndex = 8;                         // continue from location 8
				} // end else if
				else { // more than six digit difference
					String firstPortion = splitLine[6];          // get first part, remove "
					firstPortion = firstPortion.substring(1);
					String secondPortion = splitLine[7];         // this section does not have a "
					String thirdPortion = splitLine[8];          // now get third part and remove " from end
					thirdPortion = thirdPortion.substring(0, 3); 
					String combined = firstPortion + secondPortion + thirdPortion; // make them one string
					difference = Double.valueOf(combined);       // then a double
					currentIndex = 9;                            // get information from index 9 of splitString
				} // end else
				
				// use currentInded to dynamically locate the
				// rest of the information inside splitLine
				
				// get the % diff as a string, remove % sign
				// then store as a double
				String ppDiffString = splitLine[currentIndex];
				ppDiffString = ppDiffString.substring(0, ppDiffString.indexOf('%'));
				double percentDifference = Double.valueOf(ppDiffString);
				
				String stateAbbreviation = splitLine[currentIndex + 1];  // store state abbrev.
				String county = splitLine[currentIndex + 2];             // and county name as Strings
				int fips = Integer.valueOf(splitLine[currentIndex + 3]); // store fips number as int
				
				// now create a CR2016 object with
				// all of the information we just collected
				CountyResults2016 cr = new CountyResults2016(demVotes, gopVotes, totalVotes,
						                   percentDem, percentGOP, difference, percentDifference,
						                   stateAbbreviation, county, fips);
				
				list.add(cr); // add to ArrayList
				
			} // end for
			
			readResults.close(); // close reader object
			
		} // end try
		catch (FileNotFoundException e) { // cannot find file
			System.out.println("ERROR: Cannot find file.");
		} // end catch
		
	} // end fillList (void)
	
	public static CountyResults2016 findLargestMargin(ArrayList<CountyResults2016> list) {
		double highestMargin = Double.MIN_VALUE; // holds highest margin,
		                                         // uses lowest integer to compare
		int hiMarginIndex = 0;                   // which index of ArrayList list
		                                         // can we find the highest margin?
		
		for(int i = 0; i < list.size(); i++) {
			CountyResults2016 temp = list.get(i); // temporarily make an object
			                                      // to test each index location
			
			if(temp.getDifference() > highestMargin) {
				hiMarginIndex = i;                    // store array location
				highestMargin = temp.getDifference(); // store highest margin
			} // end if
		} // end for
		
		// make a copy of the CR2016 object from
		// within the ArrayList, and then return it
		CountyResults2016 highestCountyMargin = list.get(hiMarginIndex);
		return highestCountyMargin;
	} // end findLargestMargin (no state) (CR2016)
	
	public static CountyResults2016 findLargestMargin(ArrayList<CountyResults2016> list, String state) {
		
		// first we need to make a new ArrayList to
		// store any CR2016 objects that are labled as
		// the specified state
		
		ArrayList<CountyResults2016> matchingState = new ArrayList<CountyResults2016>();
		for(int i = 0; i < list.size(); i++) {
			CountyResults2016 temp = list.get(i); // check using temporary object
			
			if(temp.getState().equals(state)) {
				matchingState.add(temp); // add to matchingState when a CR2016 obj. is
				                         // inside the matching state
			} // end if
		} // end for
		
		// values to compare margins and store
		// where the highest margin was found
		// in the ArrayList of objects inside of
		// the specified state
		double highestMargin = Double.MIN_VALUE;
		int hiMarginIndex = 0;
		
		for(int i = 0; i < matchingState.size(); i++) {
			CountyResults2016 check = matchingState.get(i);
			
			// note the margin and index if 
			if(check.getDifference() > highestMargin) {
				hiMarginIndex = i;
				highestMargin = check.getDifference();
			} // end if
		} // end for
		
		// return the object with the highest victory
		// margin in the specified state
		CountyResults2016 highestCountyMargin = matchingState.get(hiMarginIndex);
		return highestCountyMargin;
		
	} // end findLargestMargin (state specified) (CR2016)
	
	public static String[] getStateTotals(ArrayList<CountyResults2016> list) {
		
		String[] dataByState = new String[60]; // holds totals data about to be made
		                                       // (using size of 60 in case non-state
		                                       // territories were included in the data)
		String currentState = "";              // holds current state
		double totalDem = 0.0;                 // holds current total Dem votes
		double totalGOP = 0.0;                 // holds current total GOP votes
		int dbsIndex = 0;                      // holds current array location for
		                                       // dataByState
		
		for(int i = 0; i < list.size(); i++) {
			
			CountyResults2016 temp = list.get(i); // make a temporary object to get values
			
			if(temp.getState() != currentState && currentState == "") { // first time running
				
				currentState = temp.getState(); // get the first state abbrev.
				
				// calculate totals as normal
				totalDem += temp.getDemVotes();
				totalGOP += temp.getGOPVotes();
				
			} // end if
			else if(!(temp.getState().equals(currentState))) { // end of current state
				
				// calculate margin by subtracting
				// loser from winner
				double margin = 0.0;
				if(totalGOP > totalDem) {
					margin = totalGOP - totalDem;
				} // end if
				else {
					margin = totalDem - totalGOP;
				} // end else
				
				// form a string and store it in dataByState
				String output = String.format("%s: %f Democratic votes, %f GOP votes"
						+ ". Margin of victory: %f. Winner: %s Party.",
						currentState, totalDem, totalGOP, margin,
						((totalGOP > totalDem) ? "Republican/GOP" : "Democratic"));
				
				dbsIndex++;
				dataByState[dbsIndex] = output;
				
				// reset totals for votes
				totalDem = 0.0;
				totalGOP = 0.0;
				
				// get new state, and continue
				// calculating totals
				currentState = temp.getState();
				totalDem += temp.getDemVotes();
				totalGOP += temp.getGOPVotes();
			} // end else if
			else if (i + 1 == list.size()) { // last time collecting data from objects
				
				totalDem += temp.getDemVotes();
				totalGOP += temp.getGOPVotes();
				
				double margin = 0.0;
				if(totalGOP > totalDem) {
					margin = totalGOP - totalDem;
				} // end if
				else {
					margin = totalDem - totalGOP;
				} // end else
				
				String output = String.format("%s: %f Democratic votes, %f GOP votes"
						+ ". Margin of victory: %f. Winner: %s Party.",
						currentState, totalDem, totalGOP, margin,
						((totalGOP > totalDem) ? "Republican/GOP" : "Democratic"));
				
				
				dataByState[dbsIndex] = output;
				dbsIndex++;
				
			} // end else if
			else { // same state, still more to go in ArrayList
				totalDem += temp.getDemVotes();
				totalGOP += temp.getGOPVotes();
			} // end else
		
		} // end for
		
		// return the data 
		return dataByState;
	} // end getStateTotals (String[])
} // end class
