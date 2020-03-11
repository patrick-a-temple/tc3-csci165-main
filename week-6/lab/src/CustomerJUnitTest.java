import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerJUnitTest {

	@Test
	void testCustomerStringStringStringAddress() {
		
		// normal case scenerio - Pawnee Rock, KS
		Address street = new Address("123 US Route 56", "67567");
		Customer bruce = new Customer("Bruce", "Davis", "bdavis@gmail.com", street);
		
		String expectedFName   = "Bruce";
		String expectedLName   = "Davis";
		//String expectedAddress = "123 US Route 56";
		//String expectedZip     = "67567";
		//String expectedCity    = "Pawnee Rock";
		//String expectedState   = "KS";
		String expectedEmail   = "bdavis@gmail.com";
		
		assertEquals(expectedFName, bruce.getFirstName());
		assertEquals(expectedLName, bruce.getLastName());
		assertNotEquals("None on File", bruce.getEmail());
		assertEquals(expectedEmail, bruce.getEmail());
		
		Address expectedAddress = new Address("123 US Route 56", "67567");
		assertTrue(street.equals(expectedAddress));
		
		
		//fail("Not yet implemented");
		
	}

	@Test
	void testSetEmail() {
		
		// for the time being, let's say all the
		// customers live together to make it easier
		// address should come up as Sandwich, MD
		Address family = new Address("123 Deli Drive", "02537");
		
		Customer kelly = new Customer("Kelly", "Davis", "valid.email@temple.com", family);
		Customer mary = new Customer("Mary", "Davis", "also_valid123@gmail.com", family);
		
		// hopefully invalid cases
		Customer harry = new Customer("Harry", "Davis", "com.notvalid@hdavis", family);
		Customer larry = new Customer("Larry", "Davis", "should_not_be_v@l@d@aol.com", family);
		Customer barry = new Customer("Barry", "Davis", "barry.davis at gmail dot com", family);
		
		String kellyExpEmail = "valid.email@temple.com";
		String maryExpEmail  = "also_valid123@gmail.com";
		
		String harryInvEmail = "should_not_be_v@l@d@aol.com";
		String larryInvEmail = "should_not_be_v@l@d@aol.com";
		String barryInvEmail = "barry.davis at gmail dot com";
		
		String invalidExpEmail = "None on File";
		
		// test for validity
		assertNotEquals(invalidExpEmail, kelly.getEmail());
		assertNotEquals(invalidExpEmail, kelly.getEmail());
		
		assertEquals(kellyExpEmail, kelly.getEmail());
		assertEquals(maryExpEmail, mary.getEmail());
		
		// test for invalidity
		assertNotEquals(harryInvEmail, harry.getEmail());
		assertNotEquals(larryInvEmail, larry.getEmail());
		assertNotEquals(barryInvEmail, barry.getEmail());
		
		assertEquals(invalidExpEmail, harry.getEmail());
		assertEquals(invalidExpEmail, larry.getEmail());
		assertEquals(invalidExpEmail, barry.getEmail());
		
		
	}

	@Test
	void testEqualsCustomer() {
		
		// same address - Philidelphia Phillies
		// stadium, other address
		Address citizensBankStad = new Address("1 Citizens Bank Way", "19148");
		Address fenwayPark       = new Address("4 Jersey Street", "02215");
		
		Customer phanaticCloneA = new Customer("Phillie", "Phanatic",
				                      "phan@phillies.com", citizensBankStad);
		Customer phanaticCloneB = new Customer("Phillie", "Phanatic",
                                      "phan@phillies.com", citizensBankStad);
		
		Customer wally          = new Customer("Wally", "Green Monster",
				                      "w.g.monster@redsox.com", fenwayPark);
		
		assertTrue(phanaticCloneA.equals(phanaticCloneB));
		assertTrue(phanaticCloneB.equals(phanaticCloneA));
		
		assertFalse(phanaticCloneA.equals(wally));
		assertFalse(phanaticCloneB.equals(wally));
		
		
	}

	@Test
	void testToString() {
		
		Address testAddress = new Address("642 Main Street", "02537");
		Customer testCustomer = new Customer("Kyle", "Delhi", 
											"kdelhi@hotmail.com", testAddress);
		
		String expected = "Name: Kyle Delhi\nEmail: kdelhi@hotmail.com\n"
				+ "Mailing Address: 642 Main Street, East Sandwich, MA 02537";
		
		assertEquals(expected, testCustomer.toString());
		
	}

}
