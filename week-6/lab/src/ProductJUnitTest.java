import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductJUnitTest {

	@Test
	void testProductString() {
		
		// testing constructor that only
		// passes in an SKU
		// normal function test
		Product tester = new Product("0011234567");
		String expectedSKU = "0011234567";
		String actualSKU = tester.getSKU();
		
		assertEquals(expectedSKU, actualSKU);
		
		// see if it responds to too low character
		// count in SKU
		Product secondTester = new Product("002");
		expectedSKU = "0020000000";
		actualSKU = secondTester.getSKU();
		
		assertEquals(expectedSKU, actualSKU);
		
		// see if it responds to too high
		// character count in sku
		
		Product thirdTester = new Product("0036543210toolong!");
		expectedSKU = "0036543210";
		actualSKU = thirdTester.getSKU();
		
		assertEquals(expectedSKU, actualSKU);
		
		// what about if the starting is not correct?
		Product fourthTester = new Product("9876543");
		expectedSKU = "1109876543";
		
		assertNotEquals("9876543", fourthTester.getSKU());
		assertEquals(expectedSKU, fourthTester.getSKU());
		
		// more tested in testSetSKU and the
		// constructor below it
		
	}

	@Test
	void testProductStringStringDoubleString() {
		
		Product tester = new Product("Bran Flakes", "Bold new taste!",
				2.50, "0041234567");
		String expectedPName = "Bran Flakes";
		String expectedPDesc = "Bold new taste!";
		double expectedPrice = 2.50;
		
		assertEquals(expectedPName, tester.getName());
		assertEquals(expectedPDesc, tester.getDescription());
		assertEquals(expectedPrice, tester.getPrice());
		assertEquals(expectedPrice, tester.getPrice());
		
	}

	@Test
	void testSetPrice() {
		
		// normal test scenerio
		Product tester = new Product("Chocolate", "Candy Bar", 0.99, "1101122334");
		double expectedPrice = 0.99;
		
		assertEquals(expectedPrice, tester.getPrice());
		
		// fluke scenario: negative price
		Product fluke = new Product("Flute", "We pay you to buy it", -100.00, "1104332211");
		expectedPrice = 19.99;
		
		assertEquals(expectedPrice, fluke.getPrice());
		
	}

	@Test
	void testSetSKU() {
		
		// I already tested this in testProductString
		// and several of the past tests, but here
		// is a scenario
		Product blank = new Product();
		blank.setSKU("0012345678");
		
		String expectedSKU = "0012345678";
		assertEquals(expectedSKU, blank.getSKU());
		
		// fluke case: too short of a sku but
		// appropriate prefix
		Product fluke = new Product();
		fluke.setSKU("001");
		
		expectedSKU = "0010000000";
		assertNotEquals("001", fluke.getSKU());
		assertEquals(expectedSKU, fluke.getSKU());
		
	}

	@Test
	void testEqualsProduct() {
		
		Product twinA = new Product("Water Bottle", "Stay hydrated", 1.49, "1104567890");
		Product twinB = new Product("Water Bottle", "Stay hydrated", 1.49, "1104567890");
		
		Product somethingElse = new Product("Soda-Soula", "1 Liter of Cola",
											2.00, "0014567890");
		
		assertFalse(twinA.equals(somethingElse) && twinB.equals(somethingElse));
		assertTrue(twinA.equals(twinB));
		
	}

	@Test
	void testToString() {
		
		Product tester = new Product("Crackers", "Buttery tasting", 3.00, "0010010010");
		String expected = "Item: Crackers\nDescription: Buttery tasting\nPrice: $3.00\n"
				+ "SKU: 0010010010";
		
		assertEquals(expected, tester.toString());
		
	}

}
