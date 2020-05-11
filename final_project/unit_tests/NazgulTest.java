// Patrick Temple
// Prof. Whitener
// CSCI165
// 11 May 2020

// Final Project: NazgulTest
// Purpose: to test the Nazgul class

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class NazgulTest {
	
	// make sure a Nazgul goes somewhere else when it
	// does not see any other Creature (ie. moving in
	// a random direction
	@Test
	void testChooseActionEmptyArea() {
		
		int[] nLocation  = { 50, 50 };
		int[] unexpected = { 50, 50 };
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul n = new Nazgul(nLocation, 6, 5, 5);
		
		world.add(n);
		n.chooseAction(world);
		assertFalse(Arrays.equals(unexpected, n.getLocation()));
		
	}
	
	// make sure a Nazgul stays where it is if surrounded by
	// other Nazgul
	@Test
	void testChooseActionSurroundedByNazgul() {
		
		// anticipated result: tester cannot move at all
		int[] testerLocation     = { 50, 50 }; // location of subject to test
		int[] upBlockLocation    = { 50, 49 }; // locations of subjects to
		int[] downBlockLocation  = { 50, 51 }; // surround tester so it can't move
		int[] rightBlockLocation = { 51, 50 };
		int[] leftBlockLocation  = { 49, 50 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Nazgul tester     = new Nazgul(testerLocation, 5, 5, 5);
		Nazgul upBlock    = new Nazgul(upBlockLocation, 5, 5, 5);
		Nazgul downBlock  = new Nazgul(downBlockLocation, 5, 5, 5);
		Nazgul rightBlock = new Nazgul(rightBlockLocation, 5, 5, 5);
		Nazgul leftBlock  = new Nazgul(leftBlockLocation, 5, 5, 5);
		
		world.add(tester);
		world.add(upBlock);
		world.add(downBlock);
		world.add(rightBlock);
		world.add(leftBlock);
		
		tester.chooseAction(world); // combine into neighborData replacement
		
		// see if the Nazgul is still there
		assertTrue(Arrays.equals(testerLocation, tester.getLocation()));
		
	}
	
	// see if a Nazgul moves toward a fellow Nazgul on
	// the left
	@Test
	void testChooseActionWithFriendOnLeft() {
		
		int[] friendLoc  = { 48, 50 };
		int[] primaryLoc = { 50, 50 };
		int[] expectedMovement = { 49, 50 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul friend  = new Nazgul(friendLoc, 7, 4, 2);
		Nazgul primary = new Nazgul(primaryLoc, 8, 6, 6); // main test subject
		
		world.add(friend);
		world.add(primary);
		
		primary.chooseAction(world);
		assertTrue(Arrays.equals(expectedMovement, primary.getLocation()));
		
	}
	
	// see what a Nazgul does when it sees a Hobbit
	// below it
	@Test
	void testChooseActionWithHobbitBelow() {
		// expected: the Nazgul attacks the Hobbit
		// and then moves towards it
		
		// also, these are the only two creatures on the map, so
		// we do not have to worry about where it can go
		int[] hobbitLocation = { 50, 52 };
		int[] nazgulLocation = { 50, 50 };
		int[] expectedMove   = { 50, 51 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul n = new Nazgul(nazgulLocation, 10, 5, 2);
		Hobbit h = new Hobbit(hobbitLocation, 10, 5, 2);
		
		world.add(n);
		world.add(h);
		
		n.chooseAction(world);
		assertTrue(Arrays.equals(expectedMove, n.getLocation()));
		
	}
	
	// test the actions of a Nazgul if it kills a Hobbit
	// (it should not move)
	@Test
	void chooseActionKillHobbit() {
		
		// see if a Nazgul stays where it was if
		// it kills a Hobbit
		int[] hobbitLocation = { 50, 52 };
		int[] nazgulLocation = { 50, 50 };
		int[] unexpectedMove = { 50, 51 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul n = new Nazgul(nazgulLocation, 10, 11, 2);
		Hobbit h = new Hobbit(hobbitLocation, 10, 5, 1);
		
		world.add(n);
		world.add(h);
		
		n.chooseAction(world);
		assertFalse(Arrays.equals(unexpectedMove, n.getLocation()));
		assertTrue(h.isDead());
		
	}
	
	// ensure Nazgul cannot penetrate any wall
	@Test
	void testCannotMoveThruWalls() {
		
		int[] upper = { 50, 0 } ;
		int[] left  = { 0, 50 } ;
		int[] lower = { 50, 99 };
		int[] right = { 99, 50 };
		
		Nazgul north = new Nazgul(upper, 5, 2, 3);
		Nazgul south = new Nazgul(lower, 5, 3, 3);
		Nazgul east  = new Nazgul(right, 5, 5, 5);
		Nazgul west  = new Nazgul(left, 6, 3, 3);
		
		north.move(Creature.direction.UP);
		assertNotEquals(lower, north.getLocation());
		assertEquals(upper, north.getLocation());
		
		south.move(Creature.direction.DOWN);
		assertNotEquals(upper, south.getLocation());
		assertEquals(lower, south.getLocation());
		
		east.move(Creature.direction.RIGHT);
		assertNotEquals(left, east.getLocation());
		assertEquals(right, east.getLocation());
		
		west.move(Creature.direction.LEFT);
		assertNotEquals(right, west.getLocation());
		assertEquals(left, west.getLocation());
		
	}
	
	// see if a Nazgul can move in all four directions
	@Test
	void testNormalMovement() {
		
		int[] startingLocation  = { 50, 50 };
		int[] expectedStepOne   = { 49, 50 };
		int[] expectedStepTwo   = { 49, 49 };
		int[] expectedStepThree = { 50, 49 };
		
		Nazgul mover = new Nazgul(startingLocation, 10, 2, 1);
		
		mover.move(Creature.direction.LEFT);
		assertTrue(Arrays.equals(expectedStepOne, mover.getLocation()));
		
		mover.move(Creature.direction.UP);
		assertTrue(Arrays.equals(expectedStepTwo, mover.getLocation()));
		
		mover.move(Creature.direction.RIGHT);
		assertTrue(Arrays.equals(expectedStepThree, mover.getLocation()));
		
		mover.move(Creature.direction.DOWN);
		assertTrue(Arrays.equals(startingLocation, mover.getLocation()));
		
	}
	
	// test the attack function
	@Test
	void testAttack() {
		
		int[] victimLocation = { 48, 50 };
		int[] nazgulLocation = { 50, 50 };
		
		Hobbit victim = new Hobbit(victimLocation, 10, 5, 2);
		Nazgul attacker = new Nazgul(nazgulLocation, 8, 6, 3);
		attacker.attack(victim);
		
		// in case of critical hit, either 6 HP
		// or 0 HP is an expected number
		assertTrue(victim.health == 6 || victim.health == 0);
		
	}
	
	// see how a Nazgul behaves when it replicates
	// with an open field
	@Test
	void testReplicateWithEmptyField() {
		
		int[] parentLocation = { 50, 50 };
		int[] babyGoesHere   = { 50, 49 }; // where baby should go
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul parent = new Nazgul(parentLocation, 10, 5, 3);
		parent.turnsSinceReproduction = 8;
		parent.refreshDirectSpaces(world);
		
		assertTrue(parent.canReplicate());
		
		Creature baby = parent.replicate();
		
		assertTrue(Arrays.equals(babyGoesHere, baby.location));
		
	}
	
	
	// test the behaviors of stay
	@Test
	void testStay() {
		
		int[] healthyLocation = { 10, 10 };
		int[] illLocation     = { 5, 5 };
		
		// test a healthy Nazgul
		Nazgul healthy = new Nazgul(healthyLocation, 10, 5, 5);
		// assuming default sustenance for a Nazgul is 8
		// and turnsSinceReproduction is 0
		healthy.stay();
		assertEquals(7, healthy.sustenance);
		assertEquals(1, healthy.turnsSinceReproduction);
		
		// test an unwell Nazgul that will "die" after this turn
		Nazgul ill = new Nazgul(illLocation, 6, 2, 3);
		ill.sustenance = 1;
		ill.stay();
		
		assertTrue(ill.isDead());
		assertEquals(0, ill.health);
		
	}
	
	// see if Nazgul change to the appropriate color
	@Test
	void testColor() {
		
		int[] healthyLocation = { 10, 10 };
		int[] unwellLocation  = { 8, 8 };
		int[] illLocation     = { 6, 6 };
		
		Nazgul healthy = new Nazgul(healthyLocation, 10, 5, 5);
		// assuming default sustenance for a Nazgul is 8
		
		Nazgul unwell = new Nazgul(unwellLocation, 8, 6, 7);
		unwell.sustenance = 2;
		
		Nazgul ill = new Nazgul(illLocation, 6, 2, 3);
		ill.sustenance = 1;
		
		assertEquals(Color.BLACK, healthy.color());
		assertEquals(Color.GRAY, unwell.color());
		assertEquals(Color.LIGHT_GRAY, ill.color());
		
	}
	
	// test the bahaviors for when a Nazgul gets an item
	@Test
	void testGetItem() {
		
		int[] filler = { 0, 0 };
		int[] carrierLocation = { 5, 5 };
		
		Item healthItem  = new Item(filler, 3, Item.itemType.HEALTH);
		Item offenseItem = new Item(filler, 3, Item.itemType.OFFENSE);
		Item defenseItem = new Item(filler, 3, Item.itemType.DEFENSE);
		Item sightItem   = new Item(filler, 3, Item.itemType.SIGHT);
		Item foodItem    = new Item(filler, 6, Item.itemType.NOURISHMENT);
		
		Nazgul carrier = new Nazgul(carrierLocation, 9, 6, 6);
		// default: radiusSize = 8, sustenance = 8
		
		// test behaviors 
		carrier.getItem(healthItem);
		assertEquals(12, carrier.health);
		
		carrier.getItem(offenseItem);
		assertEquals(9, carrier.offense);
		
		carrier.getItem(defenseItem);
		assertEquals(9, carrier.defense);
		
		carrier.getItem(sightItem);
		assertEquals(5, carrier.radiusSize);
		
		// should not change because Nazguls cannot eat food
		carrier.getItem(foodItem);
		assertNotEquals(11, carrier.sustenance);
		assertEquals(8, carrier.sustenance);
		
		// as reach is a Hobbit only stat, it is not tested here
	}
	
	// see when a Nazgul thinks they can replicate
	@Test
	void testCanReplicate() {
		
		int[] healthyLocation = { 9, 10 };  // blocks left for one of the tests
		int[] illLocation     = { 10, 9 };  // blocks top
		int[] youngLocation   = { 10, 11 }; // blocks bottom
		int[] rightLocation   = { 11, 10 }; // blocks right
		int[] blockedLocation = { 10, 10 }; // blocked for a test
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul healthy = new Nazgul(healthyLocation, 10, 5, 5); // blocks left
		healthy.turnsSinceReproduction = 8;
		
		Nazgul ill = new Nazgul(illLocation, 1, 2, 3); // block up
		ill.sustenance = 1;
		ill.turnsSinceReproduction = 8;
		
		Nazgul young = new Nazgul(youngLocation, 6, 2, 3);
		young.turnsSinceReproduction = 2;
		Nazgul rightBlocker = new Nazgul(rightLocation, 6, 9, 2);
		Nazgul blocked = new Nazgul(blockedLocation, 6, 9, 3);
		
		healthy.refreshDirectSpaces(world);
		ill.refreshDirectSpaces(world);
		young.refreshDirectSpaces(world);
		rightBlocker.refreshDirectSpaces(world);
		blocked.refreshDirectSpaces(world);
		
		// add to a tester ArrayList of Creatures
		// then test which ones can reproduce
		world.add(healthy);
		world.add(ill);
		world.add(young);
		world.add(rightBlocker);
		world.add(blocked);
		
		assertTrue(healthy.canReplicate());
		assertFalse(ill.canReplicate());
		assertFalse(young.canReplicate());
		assertFalse(rightBlocker.canReplicate());
		assertFalse(blocked.canReplicate());
		
	}
	
	// make sure a Nazgul cannot replicate against a wall
	@Test
	void testCanReplicateAgainstWall() {
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		int[] blockedLocation  = { 50, 0 };
		int[] blockingLeftLoc  = { 49, 0 };
		int[] blockingRightLoc = { 51, 0 };
		int[] blockingBelowLoc = { 50, 1 };
		
		// take an otherwise capable Nazgul
		// and block it in against a wall
		// with some other creatures - it should not
		// be able to reproduce
		Nazgul blocked = new Nazgul(blockedLocation, 9, 5, 5);
		blocked.turnsSinceReproduction = 9;
		
		Hobbit blockingLeft   = new Hobbit(blockingLeftLoc, 8, 6, 6);
		Nazgul blockingRight  = new Nazgul(blockingRightLoc, 7, 3, 8);
		Hobbit blockingBottom = new Hobbit(blockingBelowLoc, 8, 2, 3);
		
		world.add(blocked);
		world.add(blockingLeft);
		world.add(blockingRight);
		world.add(blockingBottom);
		
		blocked.refreshDirectSpaces(world);
		blockingLeft.refreshDirectSpaces(world);
		blockingRight.refreshDirectSpaces(world);
		blockingBottom.refreshDirectSpaces(world);
		
		assertFalse(blocked.canReplicate()); // test to see if it cannot replicate
		
	}
	
	// ensure seekEnemy should return null when it does
	// not find a Creature
	@Test
	void testSeekEnemyNoResult() {
		
		int[] lookerLocation = { 10, 10 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul looker = new Nazgul(lookerLocation, 10, 5, 4);
		world.add(looker);
		Creature result = looker.seekEnemy(world);
		
		assertNull(result);
	}
	
	// giving the Nazgul an unfamiliar Hobbit, let it
	// find the Hobbit
	@Test
	void testSeekEnemyOneUnfamiliarResult() {
		
		int[] lookerLocation = { 10, 10 };
		int[] preyLocation   = { 8, 8 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		Nazgul looker = new Nazgul(lookerLocation, 10, 5, 4);
		Hobbit prey   = new Hobbit(preyLocation, 7, 3, 6);
		
		world.add(looker);
		world.add(prey);
		
		Creature result = looker.seekEnemy(world);
		assertTrue(prey.equals(result));
		
	}
	
	// remembering the last time it attacked a Hobbit at
	// a specific location, see if it attacks the Hobbit
	// at that same location
	@Test
	void testSeekEnemyOneFamiliarResult() {
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		int[] lookerLocation   = { 10, 10 };
		int[] familiarLocation = { 8, 8 };
		int[] decoyALocation   = { 9, 9 };
		int[] decoyBLocation   = { 11, 10 };
		
		// let's set the stage and attack the Hobbit that will
		// be used in a relevant test in the next step
		Nazgul looker   = new Nazgul(lookerLocation, 10, 5, 4);
		Hobbit familiar = new Hobbit(familiarLocation, 7, 3, 4);
		world.add(looker);
		world.add(familiar);
		looker.chooseAction(world);
		assertNotEquals(7, familiar.health); // ensure familiar was attacked
		
		// we are adding decoys to see if the program can
		// tell the difference between them and the last
		// Hobbit it attacked
		
		Nazgul decoyA = new Nazgul(decoyALocation, 7, 3, 2);
		Nazgul decoyB = new Nazgul(decoyBLocation, 9, 5, 7);
		world.add(decoyA);
		world.add(decoyB);
		
		// just call seekEnemy to see if it found
		// the familiar Hobbit
		
		Creature result = looker.seekEnemy(world);
		assertEquals(familiar, result);
		
		
	}

}
