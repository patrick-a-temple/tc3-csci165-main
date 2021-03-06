// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6 Lab: AddressTest
// Purpose: to test the Address class

// had trouble here: null pointer exceptions

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddressTest {


	@Test
	void testAddressStringString() {
		
		// make a test object and see if
		// it reports back the same
		Address tester = new Address("123 Main Street", "14883");
		
		String expectedAddress = "26 Hulbert Hollow Road";
		String expectedTown    = "Spencer";
		String expectedState   = "NY";
		String expectedZIP     = "14883";
		
		// make sure values do not go
		// to the failure state
		assertFalse(tester.getCity().equals("Not Found"));
		assertFalse(tester.getState().equals("NA"));
		
		assertTrue(tester.getStreet().equals(expectedAddress));
		assertTrue(tester.getCity().equals(expectedTown));
		assertTrue(tester.getState().equals(expectedState));
		assertTrue(tester.getZip().equals(expectedZIP));
		
		
	}
	
	@Test
	void testEqualsAddress() {
		
		// two like addresses: main Apple
		// Computer Co. Headquarters
		Address twinA = new Address("1 Infinite Loop", "95014");
		Address twinB = new Address("1 Infinite Loop", "95014");
		
		// an unlike address: Oracle HQ
		Address unlike = new Address("500 Oracle Pkwy.", "94065");
		
		// verify the two like address are
		// considered equal
		assertTrue(twinA.equals(twinB));
		assertTrue(twinB.equals(twinA));
		
		// verify the unlike address does not
		// match both of the like addresses
		assertFalse(twinA.equals(unlike));
		assertFalse(twinB.equals(unlike));
		
	}

}
