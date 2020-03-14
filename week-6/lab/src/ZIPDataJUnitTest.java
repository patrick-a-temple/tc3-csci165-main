import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ZIPDataJUnitTest {

	@Test
	void testAddress() {
		
		ZIPData zd = new ZIPData();
		String test[][] = new String[43000][3];
		test = zd.getData();
		
		// Dryden's information should be at
		// [5005][]
		
		String expZIP = "13053";
		String expTown = "Dryden";
		String expState = "NY";
		assertTrue(expZIP.equals(test[5005][0]));
		assertTrue(expTown.equals(test[5005][1]));
		assertTrue(expState.equals(test[5005][2]));
		//assertEquals(expected, actual);
		//fail("Not yet implemented");
		
	}

}
