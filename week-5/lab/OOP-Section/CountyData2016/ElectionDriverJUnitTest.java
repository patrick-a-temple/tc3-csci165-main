// Patrick Temple
// Prof. Whitener
// CSCI165
// 28 February 2020

// Week 5 Lab: ElectionDriverJUnitTest
// Purpose: to assure the quality of
// findLargestMargin (with or without state)
// and getStateTotals

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ElectionDriverJUnitTest {


	@Test
	void testFindLargestMarginArrayListOfCountyResults2016() {
		
		ArrayList<CountyResults2016> results = new ArrayList<CountyResults2016>();
		ElectionDriver.fillList(results);
		
		// after reviewing the CSV in a spreadsheet application,
		// Los Angeles County, California has the biggest margin
		
		double actual = 1_273_485;
		CountyResults2016 test = ElectionDriver.findLargestMargin(results);
		
		double resultToTest = test.getDifference(); // get what the program thinks
                                                    // is the highest margin
		assertEquals(actual, resultToTest);         // run the test
		
	} // end void

	@Test
	void testFindLargestMarginArrayListOfCountyResults2016String() {
		
		ArrayList<CountyResults2016> results = new ArrayList<CountyResults2016>();
		ElectionDriver.fillList(results);
		
		// we are testing this with Rhode Island
		double actual = 51276; // (Providence County I think?)
		CountyResults2016 test = ElectionDriver.findLargestMargin(results, "RI");
		
		double resultToTest = test.getDifference(); // get what the program thinks
		                                            // is the highest margin
		assertEquals(actual, resultToTest);         // run the test
		
	} // end void

	@Test
	void testGetStateTotals() {
		
		ArrayList<CountyResults2016> results = new ArrayList<CountyResults2016>();
		ElectionDriver.fillList(results);
		
		// tested by manually getting data from CSV
		// and formatting it how I made the program
		// to give answers
		String actual = "DE: 235581.000000 Democratic votes, 185103.000000 GOP votes."
				+ " Margin of victory: 50478.000000. Winner: Democratic Party.";
		String data[] = new String[60]; // where the results will be stored
		data = ElectionDriver.getStateTotals(results);
		String resultToTest = data[9]; // resembles Delaware
		assertEquals(actual, resultToTest); // run the test
		
		
	} // end void

} // end class
