import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

class MapTest {
	
	// see if the Map can find Creatures with isSpaceOccupied
	@Test
	void testIsSpaceOccupied() {
		
		// required ArrayLists
		ArrayList<Creature> occupants = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		
		// some locations for a Hobbit and a Nazgul
		int[] hobbitLocation = { 5, 5 };
		int[] itemLocation = { 7, 7 };
		int[] nazgulLocation = { 10, 10 }; // should not be detected
		
		Hobbit hobbit = new Hobbit(hobbitLocation, 5, 5, 5);
		Nazgul nazgul = new Nazgul(nazgulLocation, 7, 7, 7);
		MagicalItem superFood = new MagicalItem(itemLocation, 5, Item.itemType.NOURISHMENT, 2);
		
		occupants.add(hobbit);
		occupants.add(nazgul);
		items.add(superFood);
		
		Map session = new Map(occupants, items);
		
		assertTrue(session.isSpaceOccupied(hobbitLocation));
		assertTrue(session.isSpaceOccupied(nazgulLocation));
		assertFalse(session.isSpaceOccupied(itemLocation));
		
	}
	
	// can it find items at a specific location
	@Test
	void testDoesSpaceHaveItem() {
		
		ArrayList<Creature> occupants = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		
		int[] itemLocation  = { 7, 7 };
		int[] blankLocation = { 7, 0 };
		Item coolItem = new Item(itemLocation, 3, Item.itemType.OFFENSE);
		items.add(coolItem);
		
		Map session = new Map(occupants, items);
		
		assertTrue(session.doesSpaceHaveItem(itemLocation));
		assertFalse(session.doesSpaceHaveItem(blankLocation));
		
	}
	
	// see if it can find an item at a specific location
	@Test
	void testGetItem() {
		
		ArrayList<Creature> occupants = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		Random rng = new Random();
		
		for(int i = 0; i < 4; i++) {
			int[] location = { (Math.abs(rng.nextInt() % 100)), (Math.abs(rng.nextInt() % 100)) };
			Item temp = new Item(location, (Math.abs(rng.nextInt() % 10)), Item.itemType.SIGHT);
			items.add(temp);
		}
		
		int[] realItemLocation = { 50, 50 };
		Item realItem = new Item(realItemLocation, 3, Item.itemType.DEFENSE);
		items.add(realItem);
		Map session = new Map(occupants, items);
		
		Item result = session.getItem(realItemLocation);
		assertEquals(realItem, result);
		
		// fluke: there was no item in the first place
		// (should return null)
		
		int[] noItemHere = { 0, 0 };
		
		result = session.getItem(noItemHere);
		assertNull(result);
		
	}

	@Test
	void testDeleteItem() {
		
		ArrayList<Creature> occupants = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		int[] itemLocation = { 50, 50 };
		
		Item pickedUp = new Item(itemLocation, 6, Item.itemType.HEALTH);
		items.add(pickedUp);
		
		Map session = new Map(occupants, items);
		
		session.deleteItem(itemLocation);
		assertTrue(items.isEmpty());
		
	}
	
	// see if it can find neighbors correctly within a radius
	@Test
	void testFindNeighbors() {
		
		// expected result: since a Nazgul has a default
		// radius of 2, it should only see two neighbors:
		// oneAway and twoAray
		ArrayList<Creature> occupants = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		
		int[] lookerLocation    = { 50, 50 };
		int[] oneAwayLocation   = { 49, 50 };
		int[] twoAwayLocation   = { 52, 52 };
		int[] threeAwayLocation = { 53, 53 }; // out of radius
		int[] notNearLocation   = { 72, 72 }; // out of radius
		
		// Nazgul have a default radius of 2
		Nazgul looker = new Nazgul(lookerLocation, 6, 9, 8);
		Hobbit oneAway = new Hobbit(oneAwayLocation, 5, 3, 6);
		Hobbit twoAway = new Hobbit(twoAwayLocation, 4, 7, 4);
		Nazgul threeAway = new Nazgul(threeAwayLocation, 2, 6, 3);
		Hobbit notNear   = new Hobbit(notNearLocation, 10, 3, 6);
		
		occupants.add(looker);
		occupants.add(oneAway);
		occupants.add(twoAway);
		occupants.add(threeAway);
		occupants.add(notNear);
		
		Map session = new Map(occupants, items);
		
		// get the results from findNeighbors
		ArrayList<Creature> results = session.findNeighbors(looker.getLocation(), looker.getRadiusSize());
		
		assertFalse(results.size() == 5); // make sure outliers did
		                                  // make their way in
		assertFalse(results.isEmpty());   // also make sure the results
		                                  // are filled
		
		assertTrue(results.size() == 2);
		assertFalse(results.contains(looker)); // results should not include
		                                       // looker's location
		
		// see if the Creatures that are supposed to be
		// in the data made it into the data
		assertTrue(results.contains(oneAway));
		assertTrue(results.contains(twoAway));
		assertFalse(results.contains(threeAway));
		assertFalse(results.contains(notNear));
		
	}
	
	@Test
	void testRemoveDeadCreatures() {
		
		ArrayList<Creature> occupants = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		
		Random rng = new Random();
		int[] deadHobbitLocation = { 25, 25 };
		int[] deadNazgulLocation = { 75, 75 };
		
		// make random, live creatures
		for(int i = 0; i < 4; i++) {
			int[] location = { (Math.abs(rng.nextInt() % 100)), (Math.abs(rng.nextInt() % 100)) };
			Nazgul temp = new Nazgul(location, (Math.abs(rng.nextInt() % 15)) + 1,
					          (Math.abs(rng.nextInt() % 10)) + 1, (Math.abs(rng.nextInt() % 10)));
			occupants.add(temp);
		}
		
		for(int i = 0; i < 4; i++) {
			int[] location = { (Math.abs(rng.nextInt() % 100)), (Math.abs(rng.nextInt() % 100)) };
			Hobbit temp = new Hobbit(location, (Math.abs(rng.nextInt() % 15)) + 1,
					          (Math.abs(rng.nextInt() % 10)) + 1, (Math.abs(rng.nextInt() % 10)));
			occupants.add(temp);
		}
		
		
		// make some dead creatures
		Nazgul deadNazgul = new Nazgul(deadNazgulLocation, 0, 1, 1);
		Hobbit deadHobbit = new Hobbit(deadHobbitLocation, 0, 1, 1);
		
		occupants.add(deadHobbit);
		occupants.add(deadNazgul);
		
		// expecting: eight remaining occupants,
		// and for the dead creatures to be eliminated
		// from the occupants
		
		Map session = new Map(occupants, items);
		
		session.removeDeadCreatures();
		assertFalse(occupants.size() == 10);
		assertTrue(occupants.size() == 8);
		
		assertFalse(occupants.contains(deadHobbit));
		assertFalse(occupants.contains(deadHobbit));
		
	}
	
	@Test
	void testRemoveDeadCreaturesButNoneAreDead() {
		ArrayList<Creature> occupants = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		
		Random rng = new Random();
		
		for(int i = 0; i < 4; i++) {
			int[] location = { (Math.abs(rng.nextInt() % 100)), (Math.abs(rng.nextInt() % 100)) };
			Hobbit temp = new Hobbit(location, (Math.abs(rng.nextInt() % 15)) + 1,
					          (Math.abs(rng.nextInt() % 10)) + 1, (Math.abs(rng.nextInt() % 10)));
			occupants.add(temp);
		}
		
		Map session = new Map(occupants, items);
		session.removeDeadCreatures();
		
		assertTrue(occupants.size() == 4);
		
	}

}
