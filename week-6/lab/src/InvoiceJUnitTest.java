// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6 Lab: InvoiceJUnitTest
// Purpose: to test Invoice

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class InvoiceJUnitTest {
	
	Product a            = new Product("Two Foot Sub", "Read the Title", 10.00, "0101234567");
	Product b            = new Product("Lobster", "From Alaska", 20.00, "0012345678");
	
	Date abbyAcctCreated = new Date (1, 1, 2019);
	Date abbyInvoiceMade = new Date (2, 7, 2020);
	Address abbyAddress  = new Address("4 Main Street", "18810"); // Athens PA
	Customer abbyBailey  = new Customer("Abby", "Bailey", "ab-bailey@gmail.com", abbyAddress);
	Account  abbyAccount = new Account(159, abbyBailey, 500.00, 100.00,
			                           abbyAcctCreated, 0.02);
	Invoice  abbyInvoice = new Invoice("BBY", abbyAccount, 30.00, abbyInvoiceMade);
	
	Date     billAcctCreated    = new Date(2, 2, 2016);
	Date     billInvoiceCreated = new Date(2, 13, 2019);
	Address  billAddress        = new Address("952 Beautiful Place", "12845"); // Lake George NY
	Customer billyMays          = new Customer("Billy", "Mays", "b.mays@telebrands.com", billAddress);
	Account  billAccount        = new Account(984, billyMays, 1999.99, 3000.00,
											  billAcctCreated, 0.08);
	Invoice billInvoice         = new Invoice("BMAYS", billAccount, 20.00, billInvoiceCreated);
	

	@Test
	void testInvoiceStringAccountDoubleDate() {
		
		assertEquals("BBY", abbyInvoice.getInvoiceNumber());
		assertTrue(abbyAccount.equals(abbyInvoice.getAccount()));
		assertEquals(30.00, abbyInvoice.getAmount());
		assertTrue(abbyInvoice.getDate().equals(abbyInvoiceMade));
		//fail("Not yet implemented");
		
	}

	@Test
	void testInvoiceInvoice() {
		
		// for testing copy constructor
		Invoice copy = new Invoice(abbyInvoice);
		assertFalse(copy == abbyInvoice);
		assertTrue(copy.equals(abbyInvoice));
		
	}

	@Test
	void testGetAccount() {
		
		Account copy = abbyInvoice.getAccount();
		assertFalse(copy == abbyInvoice.getAccount());
		assertTrue(copy.equals(abbyInvoice.getAccount()));
		//fail("Not yet implemented");
		
	}

	@Test
	void testSetAccount() {
		
		Invoice copy     = new Invoice(abbyInvoice);
		Account replacer = new Account(abbyAccount);
		copy.setAccount(replacer);
		
		assertFalse(replacer == copy.getAccount());
		assertTrue(replacer.equals(copy.getAccount()));
		//fail("Not yet implemented");
		
	}

	@Test
	void testSetDate() {
		
		Invoice copy  = new Invoice(abbyInvoice);
		Date replacer = new Date(2, 20, 2019);
		copy.setDate(replacer);
		
		assertFalse(replacer == copy.getDate());
		assertTrue(replacer.equals(copy.getDate()));
		
		//fail("Not yet implemented");
		
	}

	@Test
	void testAddProduct() {
		
		abbyInvoice.addProduct(a);
		ArrayList<Product> testList = abbyInvoice.getProducts();
		Product tester = testList.get(0);
		assertFalse(testList.isEmpty());
		assertTrue(tester.equals(a));
		//fail("Need to come back to this");

	}
	
	@Test
	void testGetProducts() {
		
		abbyInvoice.addProduct(b);
		ArrayList<Product> testList = abbyInvoice.getProducts();
		Product tester = testList.get(0);
		assertFalse(testList.isEmpty());
		assertTrue(tester.equals(b));
		//fail("Needs to be implemented");
		
	}

	@Test
	void testCompareTo() {
		
		Invoice copy = new Invoice(abbyInvoice);
		billInvoice.addProduct(b);
		
		assertTrue(copy.compareTo(abbyInvoice) == 0);
		assertTrue(abbyInvoice.compareTo(billInvoice) == 1);
		assertTrue(billInvoice.compareTo(abbyInvoice) == -1);
		
		
		//fail("Not yet implemented");
		
	}	

}
