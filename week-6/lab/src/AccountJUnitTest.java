import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountJUnitTest {

	@Test
	void testAccountIntCustomerDoubleDoubleDateDouble() {
		
		// let's say Mavis Beacon works at
		// a keyboard company's HQ now
		// (zip should say Fremont, CA)
		Date     mbAcctDate = new Date(1, 1, 2018);
		Address  corsairHQ  = new Address("47100 Bayside Pkwy.", "94538");
		Customer mavisB     = new Customer("Mavis", "Beacon", "mbeacon@gmail.com", corsairHQ);
		Account  mbAccount  = new Account(1234, mavisB, 1000.00, 2000.00, mbAcctDate, 0.04);
		
		// verify fields with data validation
		assertTrue(mavisB.equals(mbAccount.getCustomer()));
		assertEquals(1000.00, mbAccount.getBalance());
		assertEquals(2000.00, mbAccount.getCreditLimit());
		assertEquals(0.04, mbAccount.getDiscountLevel());
		//fail("Not yet implemented");
		
	}

	@Test
	void testSetBalance() {
		
		Date     acctCreated  = new Date(1, 1, 2020);
		Address  kellyAddress = new Address("11 Davis Road", "14850");
		Customer kellyB       = new Customer("Kelly", "Blue", "k.blue@yahoo.com", kellyAddress);
		Account  kbAccount    = new Account(1111, kellyB, 0.00, 0.00, acctCreated, 0.00);
		
		kbAccount.setBalance(1000.00);
		
		// ensure the method set everything
		// up correctly
		assertNotEquals(0.00, kbAccount.getBalance());
		assertNotEquals(-1000.00, kbAccount.getBalance());
		
		// testing for the actual result
		assertEquals(1000.00, kbAccount.getBalance());
		
		// fluke: set balance to a negative number
		kbAccount.setBalance(-900.00);
		assertNotEquals(-900.00, kbAccount.getBalance());
		assertEquals(900.00, kbAccount.getBalance());
		
	}

	@Test
	void testSetCreditLimit() {
		
		Date     acctCreated  = new Date(1, 1, 2019);
		Address  margeAddress = new Address("742 Evergreen Terrace", "01101");
		Customer margeS       = new Customer("Marge", "Simpson", "ms@aol.com", margeAddress);
		Account  msAccount    = new Account(1111, margeS, 2000.00, 2500.00, acctCreated, 0.00);
		
		// normal test scenerio
		assertNotEquals(0.00, msAccount.getCreditLimit());
		assertNotEquals(-2500.00, msAccount.getCreditLimit());
		
		assertEquals(2500.00, msAccount.getCreditLimit());
		
		// fluke: attempt to set the amount of
		// the credit limit to something too large
		// (we would want the balance to be $4,000)
		msAccount.setCreditLimit(1_000_000.00);
		
		assertNotEquals(1_000_000.00, msAccount.getCreditLimit());
		assertEquals(4000.00, msAccount.getCreditLimit());
		
		// fluke: attempt to make a negative credit
		// limit, should reset to $4,000
		msAccount.setCreditLimit(-3500.00);
		
		assertNotEquals(-3500.00, msAccount.getCreditLimit());
		assertEquals(3500.00, msAccount.getCreditLimit());
		//fail("Not yet implemented");
		
	}

	@Test
	void testSetDiscountLevel() {
		
		Date     acctCreated   = new Date(1, 1, 2010);
		Address  haroldAddress = new Address("602 W Senaca St.", "14850");
		Customer haroldJ       = new Customer("Harold", "Jenkins", 
				                     "h-jenkins@spectrum.com", haroldAddress);
		Account hjAccount      = new Account(5555, haroldJ, 1000.00, 1750.00,
				                             acctCreated, 0.10);
		
		// fluke scenario:
		// The declaration was incorrect when
		// I first wrote it, should be 0.20 according
		// to the assignment requirements. I decided
		// to maintain the constructor as it is a perfect
		// data validation opportunity.
		assertNotEquals(0.10, hjAccount.getDiscountLevel());
		assertEquals(0.20, hjAccount.getDiscountLevel());
		
		// normal operation (the dog lives in Rexville, NY)
		Date     rexSignup  = new Date(1, 1, 2015);
		Address  rexAddress = new Address("101 Dalmation Alley", "14877");
		Customer rex        = new Customer("Rex", "The Dog",
				                           "rex.in.rexville@chewy.com", rexAddress);
		Account rexAcc     = new Account(9484, rex, 300.00, 500.00,
				                         rexSignup, 0.10);
		
		rexAcc.setDiscountLevel(0.10);
		
		assertEquals(0.10, rexAcc.getDiscountLevel());
		
		
		
		// fail("Not yet implemented");
		
	}

	@Test
	void testCompareTo() {
		
		Date     kAcctCreated = new Date(1, 1, 2020);
		Address  kellyAddress = new Address("11 Davis Road", "14850");
		Customer kellyB       = new Customer("Kelly", "Blue", "k.blue@yahoo.com", kellyAddress);
		Account  kbAccount    = new Account(1111, kellyB, 0.00, 0.00, kAcctCreated, 0.00);
		
		Date     hAcctCreated  = new Date(1, 1, 2010);
		Address  haroldAddress = new Address("602 W Senaca St.", "14850");
		Customer haroldJ       = new Customer("Harold", "Jenkins", 
				                     "h-jenkins@spectrum.com", haroldAddress);
		Account  hjAccount     = new Account(5555, haroldJ, 1000.00, 1750.00,
				                             hAcctCreated, 0.10);
		
		assertTrue(kbAccount.compareTo(hjAccount) == -1);
		assertTrue(hjAccount.compareTo(kbAccount) == 1);
		
		Account haroldClone = new Account(kbAccount);
		
		assertTrue(haroldClone.compareTo(kbAccount) == 0);
		
		
	}

	@Test
	void testEqualsAccount() {
		
		Date     rexSignup  = new Date(1, 1, 2015);
		Address  rexAddress = new Address("101 Dalmation Alley", "14877");
		Customer rex        = new Customer("Rex", "The Dog",
				                           "rex.in.rexville@chewy.com", rexAddress);
		Account  rexAccount = new Account(9484, rex, 300.00, 500.00,
				                         rexSignup, 0.10);
		
		Date     garySignup   = new Date(1, 1, 2020);
		Address  garyAddresss = new Address("123 Main Street", "38329");
		Customer garyCustomer = new Customer("Gary", "Melvin",
									"gmelvin77@yahoo.com", garyAddresss);
		Account  garyAccount  = new Account(9876, garyCustomer, 1500.00,
				                    2500.00, garySignup, 0.00);
		
		Account  rexClone     = new Account(rexAccount);
		
		assertTrue(rexClone.equals(rexAccount));
		assertFalse(rexAccount.equals(garyAccount));
		assertFalse(garyAccount.equals(rexClone));
		
		//fail("Not yet implemented");
		
	}

	@Test
	void testToString() {
		
		Date     mbAcctDate = new Date(1, 1, 2018);
		Address  corsairHQ  = new Address("47100 Bayside Pkwy.", "94538");
		Customer mavisB     = new Customer("Mavis", "Beacon", "mbeacon@gmail.com", corsairHQ);
		Account  mbAccount  = new Account(1234, mavisB, 1000.00, 2000.00, mbAcctDate, 0.04);
		
		String mbCustString = "Name: Mavis Beacon\nEmail: mbeacon@gmail.com\n"
				+ "Mailing Address: 47100 Bayside Pkwy., Fremont, CA 94538";
		
		String expected = "Account ID: 1234\nCustomer:\n\n" + mbCustString 
				+ "\n\nBalance: $1000.00\nCredit Limit: $2000.00\nDate Created: "
				+ "1/1/2018\nDiscount Level: 4%";
		
		assertEquals(expected, mbAccount.toString());
				
		
		//fail("Not yet implemented");
		
	}

}
